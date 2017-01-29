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

import com.supinfo.supcrowdfunderandroid.dao.RewardsDao;
import com.supinfo.supcrowdfunderandroid.model.Project;
import com.supinfo.supcrowdfunderandroid.model.Rewards;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RewardsWebServiceDao implements RewardsDao {
	public static final String JSON_ID = "id";
	public static final String JSON_NAME = "name";
	public static final String JSON_PRICE_MIN = "price";
	public static final String JSON_DESCRIPTION = "description";
	public static final String JSON_PROJECT = "project";

    private final String url = "http://YOUR_URL/SupCrowdFunder/resources/rewards/";
    private static final String LOG_TAG = "Rewards";

    public Rewards getRewardsById(int id) {
    	try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url+"search/"+id);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            Rewards rewards = new Rewards();
           
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject rewardsObject = new JSONObject(responseStr);
                rewards = convertJsonIntoString(rewardsObject);
            }
            return rewards;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new Rewards();
    }

    public List<Rewards> getAllRewards() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<Rewards> rewards = new ArrayList<Rewards>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject rewardsObject = new JSONObject(responseStr);
                if (rewardsObject.get("rewards") instanceof JSONArray) {
                    computeJsonArray(rewards, rewardsObject);
                } else if (rewardsObject.get("rewards") instanceof JSONObject) {
                    computeJsonObject(rewards, rewardsObject.getJSONObject("rewards"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return rewards;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new ArrayList<Rewards>();
    }
    
    public void insertRewards(Rewards rewards) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url+"add/");
            post.setEntity(new StringEntity(convertRewardsIntoJson(rewards).toString()));
            post.setHeader("Content-type", "application/json");
            client.execute(post);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse quote in Json", e);
        }
    }

    private void computeJsonObject(List<Rewards> rewards, JSONObject rewardsObject) throws JSONException, ParseException {
    	Rewards reward = convertJsonIntoString(rewardsObject);
    	rewards.add(reward);
    }

    private void computeJsonArray(List<Rewards> rewards, JSONObject rewardsObject) throws JSONException, ParseException {
        JSONArray rewardsArray = rewardsObject.getJSONArray("rewards");
        for (int i = 0; i < rewardsArray.length(); i++) {
            computeJsonObject(rewards, rewardsArray.getJSONObject(i));
        }
    }

    public static Rewards convertJsonIntoString(JSONObject singleRewardsJson) throws JSONException, ParseException {
    	Project p = ProjectWebServiceDao.convertJsonIntoString((JSONObject) singleRewardsJson.get(JSON_PROJECT));
    	
    	Rewards rewards = new Rewards();
    	rewards.setId(singleRewardsJson.getLong(JSON_ID));
    	rewards.setName(singleRewardsJson.getString(JSON_NAME));
    	rewards.setPrice_min(singleRewardsJson.getInt(JSON_PRICE_MIN));
    	rewards.setDescription(singleRewardsJson.getString(JSON_DESCRIPTION));
    	rewards.setProject(p);
        return rewards;
    }
    
    public static JSONObject convertRewardsIntoJson(Rewards rewards) throws JSONException {
        JSONObject rewardsJson = new JSONObject();
        JSONObject projectJson = ProjectWebServiceDao.convertProjectIntoJson(rewards.getProject());
    	
        rewardsJson.put(JSON_PROJECT, projectJson);
        rewardsJson.put(JSON_ID, rewards.getId());
        rewardsJson.put(JSON_NAME, rewards.getName());
        rewardsJson.put(JSON_PRICE_MIN, rewards.getPrice_min());
        rewardsJson.put(JSON_DESCRIPTION, rewards.getDescription());
        return rewardsJson;
    }
    
    
    public List<Rewards> getRewardsbyProjectId(int projectId) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url+"project/"+ projectId);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<Rewards> rewards = new ArrayList<Rewards>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject rewardsObject = new JSONObject(responseStr);
                if (rewardsObject.get("rewards") instanceof JSONArray) {
                    computeJsonArray(rewards, rewardsObject);
                } else if (rewardsObject.get("rewards") instanceof JSONObject) {
                    computeJsonObject(rewards, rewardsObject.getJSONObject("rewards"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return rewards;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new ArrayList<Rewards>();
    }
}
