package com.rmh.rhoffman.cbtvelocity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.blunderer.materialdesignlibrary.fragments.ViewPagerFragment;
import com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;


/**
 * A simple fragment subclass.
 *
 * This sets up the ViewPagerWithTabs in the MainActivity and adds the fragments to
 * each tab in the pager.
 */
public class MyViewPagerFragmentWithTabs extends ViewPagerFragment{

	private Activities activities;
	private AboutUs aboutUs;
	
	public MyViewPagerFragmentWithTabs(){
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

	}
	
	
	@Override
	public ViewPagerHandler getViewPagerHandler(){

		if(activities == null){
			Log.d("ViewPager", "Activities is null");
			activities = new Activities();
		}
		if(aboutUs == null){
			Log.d("ViewPager", "AboutUs is null");
			aboutUs = new AboutUs();
		}

		return new ViewPagerHandler(App.getContext())
				.addPage(R.string.section1, activities)
				.addPage(R.string.section2, aboutUs);
	}

	@Override
	public int defaultViewPagerPageSelectedPosition(){
		return 0;
	}

	@Override
	public boolean showViewPagerIndicator(){
		return false;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

		// Retain state across configuration changes, i.e. screen orientation.
		setRetainInstance(true);
	}

	@Override
	public boolean replaceActionBarTitleByViewPagerPageTitle() {
		return true;
	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);

	}

	@Override
	public void onDetach(){
		super.onDetach();

	}

}
