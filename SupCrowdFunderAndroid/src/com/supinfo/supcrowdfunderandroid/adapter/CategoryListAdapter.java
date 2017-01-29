package com.supinfo.supcrowdfunderandroid.adapter;

import java.util.ArrayList;

import com.supinfo.supcrowdfunderandroid.R;
import com.supinfo.supcrowdfunderandroid.model.Category;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryListAdapter extends BaseAdapter {
	
	private ArrayList<Category> category;
	@SuppressWarnings("unused")
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<DataSetObserver> observer;
	
	public CategoryListAdapter(Context context)
	{
		super();
		this.category = new ArrayList<Category>();
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.observer = new ArrayList<DataSetObserver>();
	}
	
	public void add(Category category)
	{
		this.category.add(category);
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
		return this.category.size();
	}

	@Override
	public Object getItem(int position) {
		return this.category.get(position);
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
		LinearLayout layoutItem;
		if (convertView == null)
		    layoutItem = (LinearLayout) inflater.inflate(R.layout.category_design, parent, false);
		else
		  	layoutItem = (LinearLayout) convertView;

		TextView tv_quote = (TextView)layoutItem.findViewById(R.id.tv_category);
		             
		tv_quote.setText(category.get(position).getName());

		return layoutItem;
	}
}