package com.rmh.rhoffman.cbtvelocity;


import android.app.Activity;
import android.os.Bundle;

import com.blunderer.materialdesignlibrary.fragments.ViewPagerFragment;
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
	
	// Create each of the pages in the viewPager and return them.
	@Override
	public ViewPagerHandler getViewPagerHandler(){

		if(activities == null){
			activities = new Activities();
		}
		if(aboutUs == null){
			aboutUs = new AboutUs();
		}

		return new ViewPagerHandler(App.getContext())
				.addPage(R.string.section1, activities)
				.addPage(R.string.section2, aboutUs);
	}

	// Shows the first page on the far left as default.
	@Override
	public int defaultViewPagerPageSelectedPosition(){
		return 0;
	}

	// Hides the indicator to show what page you are on.
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

	// Changes the title on the ActionBar as you scroll through the pages.
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
