package com.supinfo.supcrowdfunderandroid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.Category;
import com.supinfo.supcrowdfunderandroid.model.Project;
import com.supinfo.supcrowdfunderandroid.model.User;
import com.supinfo.supcrowdfunderandroid.services.CategoryService;
import com.supinfo.supcrowdfunderandroid.services.ProjectService;
import com.supinfo.supcrowdfunderandroid.services.UserService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class createProject extends Activity {
	
	private UserService userService;
	private User u;
	private CategoryService categoryService;
	private List<Category> c = new ArrayList<Category>();
	private List<String> categoryName = new ArrayList<String>();
	private ProjectService projectService;
	private EditText name;
	private EditText description;
	private EditText startDate;
	private EditText endDate;
	private EditText goal;
	private TextView textViewError;
	private Date start;
	private Date end;
	private Spinner spinner;
	private int idUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	   	super.onCreate(savedInstanceState);
	   	setContentView(R.layout.create_project);
	   	
	   	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Bundle extras = getIntent().getExtras();
		
		if(extras != null) {
			idUser = extras.getInt("idUser");
		
	        categoryService = new CategoryService();
	        c = categoryService.getAllCategories();
			for(Category cat : c)
				categoryName.add(cat.getName());
		
			spinner = (Spinner) findViewById(R.id.spinnerCategory);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryName);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
	
			
			name = (EditText) findViewById(R.id.etNameProject);
			description = (EditText) findViewById(R.id.etDescriptionProject);
			startDate = (EditText) findViewById(R.id.etStartDate);
			endDate = (EditText) findViewById(R.id.etEndDate);
			goal = (EditText) findViewById(R.id.etGoal);
	        
	        userService = new UserService();
	        u = userService.getUserById(idUser);
	        projectService = new ProjectService(); 
		}
		else {
			textViewError = (TextView) findViewById(R.id.textViewError);
			textViewError.setText("You can't create a project." + '\n' + "You are not logged.");
		}
	}

	public void login(View view) {		
		Project p = new Project();
		Category category = categoryService.getCategoryById(spinner.getSelectedItemPosition()+1);
		
		p.setCategory_fk(category);
		p.setCurrentFund(0);
		p.setDescription(description.getText().toString());

		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.getText().toString());
			end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate.getText().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		p.setStart_date(start);
		p.setEnd_date(end);
		
		p.setGoal(Integer.valueOf(goal.getText().toString()));
		p.setName(name.getText().toString());
		p.setNb_contribution(0);
		p.setUser_fk(u);
		projectService.insertProject(p);
		
		Toast.makeText(getApplicationContext(), "Project created, redirecting...", 
		Toast.LENGTH_SHORT).show();
		
		Intent redirect = new Intent(createProject.this, ListProject.class);
		redirect.putExtra("idUser", idUser);
		startActivity(redirect);
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
	        	Intent login = new Intent(createProject.this, Login.class);
				startActivity(login);
	            return true;
	        case R.id.action_sign_up:
	        	Intent signup = new Intent(createProject.this, SignUp.class);
				startActivity(signup);
	            return true;
	        case R.id.action_create_project:
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
