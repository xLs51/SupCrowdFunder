package com.supinfo.supcrowdfunderandroid;

import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.Project;
import com.supinfo.supcrowdfunderandroid.model.Rewards;
import com.supinfo.supcrowdfunderandroid.services.ProjectService;
import com.supinfo.supcrowdfunderandroid.services.RewardsService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;

public class ProjectbyId extends Activity {
	
	private int idProject;
	private int idUser;
	private Project project;
	private ProjectService projectService;
	private List<Rewards> rewards;
	private RewardsService rewardService;
	
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdDescription;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdName;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdStart;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdEnd;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdGoal;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdCurrentFund;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdNbContrib;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdCat;
	@SuppressWarnings("unused")
	private TextView tvProjectbyIdCreator;
	
	private RadioButton rbtnReward;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projectby_id);
		
		rbtnReward = (RadioButton) findViewById(R.id.rbtnReward);

		Bundle extras = getIntent().getExtras();

		if(extras != null) {
			idProject = extras.getInt("idProject");
			idUser = extras.getInt("idUser");
		}
		
		projectService = new ProjectService();
		project = projectService.getProjectById(idProject);
		
		TextView tvProjectbyIdName = (TextView) findViewById(R.id.tvProjectbyIdName);
		TextView tvProjectbyIdDescription = (TextView) findViewById(R.id.tvProjectbyIdDescription);
		TextView tvProjectbyIdGoal = (TextView) findViewById(R.id.tvProjectbyIdGoal);
		TextView tvProjectbyIdCurrentFund = (TextView) findViewById(R.id.tvProjectbyIdCurrentFund);
		TextView tvProjectbyIdNbContrib = (TextView) findViewById(R.id.tvProjectbyIdNbContrib);
		TextView tvProjectbyIdCat = (TextView) findViewById(R.id.tvProjectbyIdCat);
		TextView tvProjectbyIdCreator = (TextView) findViewById(R.id.tvProjectbyIdCreator);
		
		
		
		tvProjectbyIdName.setText(project.getName());
		tvProjectbyIdDescription.setText(project.getDescription());
		tvProjectbyIdGoal.setText(String.valueOf(project.getGoal()));
		tvProjectbyIdCurrentFund.setText(String.valueOf(project.getCurrentFund()));
		tvProjectbyIdNbContrib.setText(String.valueOf(project.getNb_contribution()));
		tvProjectbyIdCat.setText(project.getCategory_fk().getName());
		tvProjectbyIdCreator.setText(project.getUser_fk().getFirstName() + " " + project.getUser_fk().getLastName());
		
		rewardService = new RewardsService();
		
		rewards= rewardService.getRewardsbyProjectId(idProject);

		rbtnReward.setText(rewards.get(0).getName() + rewards.get(0).getPrice_min());
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
	        	Intent login = new Intent(ProjectbyId.this, Login.class);
				startActivity(login);
	            return true;
	        case R.id.action_sign_up:
	        	Intent signup = new Intent(ProjectbyId.this, SignUp.class);
				startActivity(signup);
	            return true;
	        case R.id.action_create_project:
	        	Intent project = new Intent(ProjectbyId.this, createProject.class);
	        	project.putExtra("idUser", idUser);
				startActivity(project);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
