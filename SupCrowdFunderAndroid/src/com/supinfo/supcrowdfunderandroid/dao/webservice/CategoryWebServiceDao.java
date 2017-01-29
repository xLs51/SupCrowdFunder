package com.supinfo.supcrowdfunderandroid.dao.webservice;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfunderandroid.dao.CategoryDao;
import com.supinfo.supcrowdfunderandroid.model.Category;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategoryWebServiceDao implements CategoryDao {
	public static final String JSON_ID = "id";
	public static final String JSON_NAME = "name";
	public static final String JSON_DESC = "description";

    private final String url = "http://YOUR_URL/SupCrowdFunder/resources/category/";
    private static final String LOG_TAG = "Category";

    public Category getCategoryById(int id) {
    	try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url+"search/"+id);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            Category categories = new Category();
           
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject categoriesObject = new JSONObject(responseStr);
                categories = convertJsonIntoString(categoriesObject);
            }
            return categories;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new Category();
    }

    public List<Category> getAllCategories() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<Category> categories = new ArrayList<Category>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject categoriesObject = new JSONObject(responseStr);
                if (categoriesObject.get("category") instanceof JSONArray) {
                    computeJsonArray(categories, categoriesObject);
                } else if (categoriesObject.get("category") instanceof JSONObject) {
                    computeJsonObject(categories, categoriesObject.getJSONObject("category"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return categories;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
	        Log.e(LOG_TAG, "Unable to parse Json", e);
	    }
            
        return new ArrayList<Category>();
    }

    private void computeJsonObject(List<Category> categories, JSONObject categoriesObject) throws JSONException, ParseException {
    	Category category = convertJsonIntoString(categoriesObject);
    	categories.add(category);
    }

    private void computeJsonArray(List<Category> categories, JSONObject categoriesObject) throws JSONException, ParseException {
        JSONArray categoriesArray = categoriesObject.getJSONArray("category");
        for (int i = 0; i < categoriesArray.length(); i++) {
            computeJsonObject(categories, categoriesArray.getJSONObject(i));
        }
    }

    public static Category convertJsonIntoString(JSONObject singleCategoryJson) throws JSONException, ParseException {
    	Category category = new Category();
    	category.setId(singleCategoryJson.getLong(JSON_ID));
    	category.setName(singleCategoryJson.getString(JSON_NAME));
    	category.setDescription(singleCategoryJson.getString(JSON_DESC));
        return category;
    }
    
	public static JSONObject convertCategoryIntoJson(Category category) throws JSONException {
        JSONObject categoryJson = new JSONObject();
        categoryJson.put(JSON_ID,category.getId());
        categoryJson.put(JSON_NAME,category.getName());
        categoryJson.put(JSON_DESC,category.getDescription());
        return categoryJson;
    }
}
