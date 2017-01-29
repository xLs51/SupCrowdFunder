package com.supinfo.supcrowdfunderandroid.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.supinfo.supcrowdfunderandroid.R;
import com.supinfo.supcrowdfunderandroid.model.Project;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProjectListAdapter extends BaseAdapter {
	
	private ArrayList<Project> project;
	@SuppressWarnings("unused")
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<DataSetObserver> observer;
	
	public ProjectListAdapter(Context context)
	{
		super();
		this.project = new ArrayList<Project>();
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.observer = new ArrayList<DataSetObserver>();
	}
	
	public void add(Project project)
	{
		this.project.add(project);
		this.notifyDataSetChanged();
	}
	
	public void removeAll()
	{
		this.project.clear();
		this.notifyDataSetChanged();
	}
	
	public void notifyDataSetChanged()
	{
		for(DataSetObserver observers : this.observer)
		{
			observers.onChanged();
		}
	}

	@Override
	public int getCount() {
		return this.project.size();
	}

	@Override
	public Object getItem(int position) {
		return this.project.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public void registerDataSetObserver(DataSetObserver observers)
	{
		this.observer.add(observers);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout layoutItem;
		if (convertView == null)
		    layoutItem = (RelativeLayout) inflater.inflate(R.layout.project_design, parent, false);
		else
		  	layoutItem = (RelativeLayout) convertView;

		TextView tvProjectDescription = (TextView)layoutItem.findViewById(R.id.tvProjectDescription);
		TextView tvProjectTitle = (TextView)layoutItem.findViewById(R.id.tvProjectTitle);
		TextView tvProjectPercentage = (TextView)layoutItem.findViewById(R.id.tvProjectPercentage);
		
		String description = project.get(position).getDescription();
				
		if (description != null && description.length() > 100)
			description = description.substring(0, 100)+"...";
			             
		tvProjectDescription.setText(description);
		tvProjectTitle.setText(project.get(position).getName());
		
		double goal = project.get(position).getGoal();
		double fund = project.get(position).getCurrentFund();
		double percentage = ((fund/goal)*100);
		DecimalFormat df = new DecimalFormat("#.00");
		String percent = df.format(percentage);
		tvProjectPercentage.setText(percent + " %");

		return layoutItem;
	}
}