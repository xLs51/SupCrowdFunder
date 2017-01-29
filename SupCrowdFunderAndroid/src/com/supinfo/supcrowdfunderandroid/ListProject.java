package com.supinfo.supcrowdfunderandroid;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.supcrowdfunderandroid.adapter.CategoryListAdapter;
import com.supinfo.supcrowdfunderandroid.adapter.ProjectListAdapter;
import com.supinfo.supcrowdfunderandroid.model.Category;
import com.supinfo.supcrowdfunderandroid.model.Project;
import com.supinfo.supcrowdfunderandroid.services.CategoryService;
import com.supinfo.supcrowdfunderandroid.services.ProjectService;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListProject extends Activity {
	
	Intent Intent_ListProjectProjectbyId;
	
	// setVisibility == 8 -> gone => Won't appear on the screen and it weight be void	
	public static final int GONE = 8;
	// setVisibility == 4 -> Won't appear on the screen and it weight will stay unchanged		
	public static final int INVISIBLE = 4;
	// setVisibility == 0 -> visible => Appear with it weight
	public static final int VISIBLE = 0;	
	
	private CategoryListAdapter categoryAdapter;
	private ProjectListAdapter projectAdapter;
	
	private List<Project> projects = new ArrayList<Project>();
	private List<Project> projectsByCategory = new ArrayList<Project>();
	private List<Category> categories = new ArrayList<Category>();
	
	private ProjectService projectService;
	private CategoryService categoryService;
	
	private ListView lvCategories;
	private ListView lvProjects;
	
	private int idUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Bundle extras = getIntent().getExtras();
		
		
		if(extras != null)
			idUser = (int) extras.getLong("idUser");
		
		categoryAdapter = new CategoryListAdapter(this);
		projectAdapter = new ProjectListAdapter(this);
		
		lvCategories = (ListView) findViewById(R.id.lvCategories);
		lvCategories.setAdapter(categoryAdapter);
		lvCategories.setClickable(true);
	
		lvProjects = (ListView) findViewById(R.id.lvProjects);
		lvProjects.setAdapter(projectAdapter);
		lvProjects.setClickable(true);
		
		categoryService = new CategoryService();
		categories = categoryService.getAllCategories();
		
		for(Category c : categories)
			categoryAdapter.add(c);

	 	projectService = new ProjectService();
		projects = projectService.getAllProjects();
		
		for(Project p : projects)
			projectAdapter.add(p);
		
		lvCategories.setOnItemClickListener(new OnItemClickListener() {
			@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				projectAdapter.removeAll();
				projectsByCategory = projectService.getProjectsByCategory(position+1);
				for(Project p : projectsByCategory)
					projectAdapter.add(p);
			}
		});
		
		lvProjects.setOnItemClickListener(new OnItemClickListener() {
		@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent_ListProjectProjectbyId = new Intent(ListProject.this, ProjectbyId.class);
				Intent_ListProjectProjectbyId.putExtra("idProject", position+1);
				Intent_ListProjectProjectbyId.putExtra("idUser", idUser);
				startActivity(Intent_ListProjectProjectbyId);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_login:
	        	Intent login = new Intent(ListProject.this, Login.class);
				startActivity(login);
	            return true;
	        case R.id.action_sign_up:
	        	Intent signup = new Intent(ListProject.this, SignUp.class);
				startActivity(signup);
	            return true;
	        case R.id.action_create_project:
	        	Intent project = new Intent(ListProject.this, createProject.class);
	        	project.putExtra("idUser", idUser);
				startActivity(project);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}