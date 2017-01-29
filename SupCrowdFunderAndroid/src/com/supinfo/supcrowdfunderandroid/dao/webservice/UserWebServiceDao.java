package com.supinfo.supcrowdfunderandroid.dao.webservice;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfunderandroid.dao.UserDao;
import com.supinfo.supcrowdfunderandroid.model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserWebServiceDao implements UserDao {
	public static final String JSON_ID = "id";
	public static final String JSON_MAIL = "mail";
	public static final String JSON_LASTNAME = "lastName";
	public static final String JSON_FIRSTNAME = "firstName";
	public static final String JSON_ADDRESS = "address";
	public static final String JSON_PASSWORD = "password";
	public static final String JSON_NB_CONTRIB = "nb_contribute";
	public static final String JSON_ADMIN = "admin";

    private final String url = "http://YOUR_URL/SupCrowdFunder/resources/user/";
    private static final String LOG_TAG = "User";

    public User getUserById(int id) {
    	try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url+"search/"+id);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            User users = new User();
           
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject usersObject = new JSONObject(responseStr);
                users = convertJsonIntoString(usersObject);
            }
            return users;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new User();
    }

    public List<User> getAllUsers() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<User> users = new ArrayList<User>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject usersObject = new JSONObject(responseStr);
                if (usersObject.get("user") instanceof JSONArray) {
                    computeJsonArray(users, usersObject);
                } else if (usersObject.get("user") instanceof JSONObject) {
                    computeJsonObject(users, usersObject.getJSONObject("user"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return users;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new ArrayList<User>();
    }
    
    public void insertUser(User user) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url+"add/");
            post.setEntity(new StringEntity(convertUserIntoJson(user).toString()));
            post.setHeader("Content-type", "application/json");
            client.execute(post);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse quote in Json", e);
        }
    }
    
    public void updateUser(User user) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPut put = new HttpPut(url+"update/");
            put.setHeader("Content-type", "application/json");
            put.setEntity(new StringEntity(convertUserIntoJson(user).toString()));
            client.execute(put);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse quote in Json", e);
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (ClientProtocolException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        }
    }

    private void computeJsonObject(List<User> users, JSONObject usersObject) throws JSONException, ParseException {
    	User user = convertJsonIntoString(usersObject);
    	users.add(user);
    }

    private void computeJsonArray(List<User> users, JSONObject usersObject) throws JSONException, ParseException {
        JSONArray usersArray = usersObject.getJSONArray("user");
        for (int i = 0; i < usersArray.length(); i++) {
            computeJsonObject(users, usersArray.getJSONObject(i));
        }
    }

    public static User convertJsonIntoString(JSONObject singleUserJson) throws JSONException, ParseException {
    	User user = new User();
    	user.setId(singleUserJson.getLong(JSON_ID));
    	user.setMail(singleUserJson.getString(JSON_MAIL));
    	user.setLastName(singleUserJson.getString(JSON_LASTNAME));
    	user.setFirstName(singleUserJson.getString(JSON_FIRSTNAME));
    	user.setAddress(singleUserJson.getString(JSON_ADDRESS));
    	user.setPassword(singleUserJson.getString(JSON_PASSWORD));
    	user.setNb_contribute(singleUserJson.getInt(JSON_NB_CONTRIB));
    	user.setAdmin(singleUserJson.getBoolean(JSON_ADMIN));
        return user;
    }
    
	public static JSONObject convertUserIntoJson(User user) throws JSONException {
        JSONObject userJson = new JSONObject();
        userJson.put(JSON_ID,user.getId());
        userJson.put(JSON_MAIL,user.getMail());
        userJson.put(JSON_LASTNAME,user.getLastName());
        userJson.put(JSON_FIRSTNAME,user.getFirstName());
        userJson.put(JSON_ADDRESS,user.getAddress());
        userJson.put(JSON_PASSWORD,user.getPassword());
        userJson.put(JSON_NB_CONTRIB,user.getNb_contribute());
        userJson.put(JSON_ADMIN,user.isAdmin());
        return userJson;
    }
}
