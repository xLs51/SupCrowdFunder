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

import com.supinfo.supcrowdfunderandroid.dao.ProjectDao;
import com.supinfo.supcrowdfunderandroid.model.Category;
import com.supinfo.supcrowdfunderandroid.model.Project;
import com.supinfo.supcrowdfunderandroid.model.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjectWebServiceDao implements ProjectDao {
	public static final String JSON_ID = "id";
	public static final String JSON_NAME = "name";
	public static final String JSON_DESC = "description";
	public static final String JSON_START_DATE = "start_date";
	public static final String JSON_END_DATE = "end_date";
	public static final String JSON_NB_CONTRIB = "nb_contribution";
	public static final String JSON_GOAL = "goal";
	public static final String JSON_FUND = "currentFund";
	public static final String JSON_CATEGORY_FK = "category_fk";
	public static final String JSON_USER_FK = "user_fk";

    private final String url = "http://YOUR_URL/SupCrowdFunder/resources/project/";
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String LOG_TAG = "Project";
    private static DateFormat dateFormat;

    public ProjectWebServiceDao() {
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
    }
    
    public Project getProjectById(int id) {
    	try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url+"search/"+id);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            Project projects = new Project();
           
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject projectsObject = new JSONObject(responseStr);
                projects = convertJsonIntoString(projectsObject);
            }
            return projects;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Unable to parse Date", e);
        }
        return new Project();
    }
    
    public List<Project> getProjectsByCategory(int id) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url+"category/"+id);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<Project> projects = new ArrayList<Project>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject projectsObject = new JSONObject(responseStr);
                if (projectsObject.get("project") instanceof JSONArray) {
                    computeJsonArray(projects, projectsObject);
                } else if (projectsObject.get("project") instanceof JSONObject) {
                    computeJsonObject(projects, projectsObject.getJSONObject("project"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return projects;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Unable to parse Date", e);
        }
        return new ArrayList<Project>();
    }

    public List<Project> getAllProjects() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<Project> projects = new ArrayList<Project>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject projectsObject = new JSONObject(responseStr);
                if (projectsObject.get("project") instanceof JSONArray) {
                    computeJsonArray(projects, projectsObject);
                } else if (projectsObject.get("project") instanceof JSONObject) {
                    computeJsonObject(projects, projectsObject.getJSONObject("project"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return projects;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Unable to parse Date", e);
        }
        return new ArrayList<Project>();
    }
    
    public void insertProject(Project project) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url+"add/");
            post.setEntity(new StringEntity(convertProjectIntoJson(project).toString()));
            post.setHeader("Content-type", "application/json");
            client.execute(post);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse quote in Json", e);
        }
    }

    private void computeJsonObject(List<Project> projects, JSONObject projectsObject) throws JSONException, ParseException {
    	Project project = convertJsonIntoString(projectsObject);
        projects.add(project);
    }

    private void computeJsonArray(List<Project> projects, JSONObject projectsObject) throws JSONException, ParseException {
        JSONArray projectsArray = projectsObject.getJSONArray("project");
        for (int i = 0; i < projectsArray.length(); i++) {
            computeJsonObject(projects, projectsArray.getJSONObject(i));
        }
    }

    public static Project convertJsonIntoString(JSONObject singleProjectJson) throws JSONException, ParseException {
    	Category c = CategoryWebServiceDao.convertJsonIntoString((JSONObject) singleProjectJson.get(JSON_CATEGORY_FK));
    	User u = UserWebServiceDao.convertJsonIntoString((JSONObject) singleProjectJson.get(JSON_USER_FK));
    	
    	Project project = new Project();
    	project.setId(singleProjectJson.getLong(JSON_ID));
		project.setName(singleProjectJson.getString(JSON_NAME));
		project.setDescription(singleProjectJson.getString(JSON_DESC));
		project.setStart_date(dateFormat.parse(singleProjectJson.getString(JSON_START_DATE)));
		project.setEnd_date(dateFormat.parse(singleProjectJson.getString(JSON_END_DATE)));
		project.setNb_contribution(singleProjectJson.getInt(JSON_NB_CONTRIB));
	    project.setGoal(singleProjectJson.getInt(JSON_GOAL));
	    project.setCurrentFund(singleProjectJson.getInt(JSON_FUND));
	    project.setCategory_fk(c);
	    project.setUser_fk(u);
        return project;
    }

	public static JSONObject convertProjectIntoJson(Project project) throws JSONException {
        JSONObject projectJson = new JSONObject();
        JSONObject categoryJson = CategoryWebServiceDao.convertCategoryIntoJson(project.getCategory_fk());
        JSONObject userJson = UserWebServiceDao.convertUserIntoJson(project.getUser_fk());
    	
        projectJson.put(JSON_CATEGORY_FK, categoryJson);
        projectJson.put(JSON_ID, project.getId());
        projectJson.put(JSON_NAME, project.getName());
        projectJson.put(JSON_DESC, project.getDescription());
        projectJson.put(JSON_START_DATE, dateFormat.format(project.getStart_date()));
        projectJson.put(JSON_END_DATE, dateFormat.format(project.getEnd_date()));
        projectJson.put(JSON_NB_CONTRIB, project.getId());
        projectJson.put(JSON_GOAL, project.getGoal());
        projectJson.put(JSON_FUND, project.getCurrentFund());
        projectJson.put(JSON_USER_FK, userJson);
        return projectJson;
    }
}
