package com.supinfo.supcrowdfunderandroid;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.User;
import com.supinfo.supcrowdfunderandroid.services.UserService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	private UserService userService;
	private List<User> users = new ArrayList<User>();
	private EditText username = null;
	private EditText password = null;
	private int cpt = 0;
   
   @Override
	protected void onCreate(Bundle savedInstanceState) {
	   	super.onCreate(savedInstanceState);
	   	setContentView(R.layout.login);
	   	
	   	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	   	
	   	username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        
        userService = new UserService();
	}

	public void login(View view) {
		users = userService.getAllUsers();
		
		for(User u : users) {
			if(username.getText().toString().equals(u.getMail()) && password.getText().toString().equals(u.getPassword())) {
				Toast.makeText(getApplicationContext(), "Redirecting...", 
				Toast.LENGTH_SHORT).show();
				cpt = 1;
				Intent redirect = new Intent(Login.this, ListProject.class);
				redirect.putExtra("idUser", u.getId());
				startActivity(redirect);
			}	
		}
		
		if(cpt == 0) {
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
			Toast.LENGTH_SHORT).show();
		}
		else
			cpt = 0;
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
	            return true;
	        case R.id.action_sign_up:
	        	Intent signup = new Intent(Login.this, SignUp.class);
				startActivity(signup);
	            return true;
	        case R.id.action_create_project:
	        	Intent project = new Intent(Login.this, createProject.class);
				startActivity(project);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}