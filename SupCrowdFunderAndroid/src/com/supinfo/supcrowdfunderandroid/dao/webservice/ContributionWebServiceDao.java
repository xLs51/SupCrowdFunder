package com.supinfo.supcrowdfunderandroid.dao.webservice;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfunderandroid.dao.ContributionDao;
import com.supinfo.supcrowdfunderandroid.model.Contribution;
import com.supinfo.supcrowdfunderandroid.model.Rewards;
import com.supinfo.supcrowdfunderandroid.model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ContributionWebServiceDao implements ContributionDao {
	public static final String JSON_ID = "id";
	public static final String JSON_PRICE = "price";
	public static final String JSON_PROJECT = "project_fk";
	public static final String JSON_USER_FK = "user_fk";
	public static final String JSON_REWARDS_FK = "rewards_fk";

    private final String url = "http://YOUR_URL/SupCrowdFunder/resources/contribution/";
    private static final String LOG_TAG = "Contribution";

    public Contribution getContributionById(int id) {
    	try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url+"search/"+id);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            Contribution contributions = new Contribution();
           
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject contributionsObject = new JSONObject(responseStr);
                contributions = convertJsonIntoString(contributionsObject);
            }
            return contributions;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new Contribution();
    }

    public List<Contribution> getAllContributions() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<Contribution> contributions = new ArrayList<Contribution>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject contributionsObject = new JSONObject(responseStr);
                if (contributionsObject.get("contribution") instanceof JSONArray) {
                    computeJsonArray(contributions, contributionsObject);
                } else if (contributionsObject.get("contribution") instanceof JSONObject) {
                    computeJsonObject(contributions, contributionsObject.getJSONObject("contribution"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return contributions;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new ArrayList<Contribution>();
    }

    public void insertContribution(Contribution contribution) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url+"add/");
            post.setEntity(new StringEntity(convertContributionIntoJson(contribution).toString()));
            post.setHeader("Content-type", "application/json");
            client.execute(post);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse quote in Json", e);
        }
    }
    
    private void computeJsonObject(List<Contribution> contributions, JSONObject contributionsObject) throws JSONException, ParseException {
    	Contribution contribution = convertJsonIntoString(contributionsObject);
    	contributions.add(contribution);
    }

    private void computeJsonArray(List<Contribution> contributions, JSONObject contributionsObject) throws JSONException, ParseException {
        JSONArray contributionsArray = contributionsObject.getJSONArray("contribution");
        for (int i = 0; i < contributionsArray.length(); i++) {
            computeJsonObject(contributions, contributionsArray.getJSONObject(i));
        }
    }

    public static Contribution convertJsonIntoString(JSONObject singleContributionJson) throws JSONException, ParseException {
    	Rewards r = RewardsWebServiceDao.convertJsonIntoString((JSONObject) singleContributionJson.get(JSON_REWARDS_FK));
    	User u = UserWebServiceDao.convertJsonIntoString((JSONObject) singleContributionJson.get(JSON_USER_FK));
    	
    	Contribution contribution = new Contribution();
    	contribution.setId(singleContributionJson.getLong(JSON_ID));
    	contribution.setPrice(singleContributionJson.getInt(JSON_PRICE));
    	contribution.setProject_fk(singleContributionJson.getLong(JSON_PROJECT));
    	contribution.setRewards_fk(r);
    	contribution.setUser_fk(u);
        return contribution;
    }
    
    public static JSONObject convertContributionIntoJson(Contribution contribution) throws JSONException {
        JSONObject contributionJson = new JSONObject();
        JSONObject userJson = UserWebServiceDao.convertUserIntoJson(contribution.getUser_fk());
        JSONObject rewardsJson = RewardsWebServiceDao.convertRewardsIntoJson(contribution.getRewards_fk());
    	
        contributionJson.put(JSON_USER_FK, userJson);
        contributionJson.put(JSON_REWARDS_FK, rewardsJson);
        contributionJson.put(JSON_ID, contribution.getId());
        contributionJson.put(JSON_PRICE, contribution.getPrice());
        contributionJson.put(JSON_PROJECT, contribution.getProject_fk());
        return rewardsJson;
    }
}
