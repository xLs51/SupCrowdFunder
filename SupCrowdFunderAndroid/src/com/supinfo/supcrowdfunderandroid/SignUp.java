package com.supinfo.supcrowdfunderandroid;

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

public class SignUp extends Activity {

	private UserService userService;
	private EditText mail;
	private EditText firstName;
	private EditText lastName;
	private EditText address;
	private EditText password;
   
   @Override
	protected void onCreate(Bundle savedInstanceState) {
	   	super.onCreate(savedInstanceState);
	   	setContentView(R.layout.signup);
	   	
	   	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		mail = (EditText) findViewById(R.id.etMail);
		firstName = (EditText) findViewById(R.id.etFirstName);
		lastName = (EditText) findViewById(R.id.etLastName);
		address = (EditText) findViewById(R.id.etAddress);
		password = (EditText) findViewById(R.id.etPassword);
        
        userService = new UserService();
	}

	public void login(View view) {
		User u = new User();
		u.setMail(mail.getText().toString());
		u.setFirstName(firstName.getText().toString());
		u.setLastName(lastName.getText().toString());
		u.setAddress(address.getText().toString());
		u.setPassword(password.getText().toString());
		u.setNb_contribute(0);
		u.setAdmin(false);
		userService.insertUser(u);
		
		Toast.makeText(getApplicationContext(), "User created, redirecting...", 
		Toast.LENGTH_SHORT).show();
		
		Intent redirect = new Intent(SignUp.this, Login.class);
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
	        	Intent login = new Intent(SignUp.this, Login.class);
				startActivity(login);
	            return true;
	        case R.id.action_sign_up:
	            return true;
	        case R.id.action_create_project:
	        	Intent project = new Intent(SignUp.this, createProject.class);
				startActivity(project);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}