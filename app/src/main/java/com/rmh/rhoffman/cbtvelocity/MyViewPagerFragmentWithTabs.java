package com.rmh.rhoffman.cbtvelocity;


import android.app.Activity;
import android.os.Bundle;

import com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;


/**
 * A simple fragment subclass.
 *
 * This sets up the ViewPagerWithTabs in the MainActivity and adds the fragments to
 * each tab in the pager.
 */
public class MyViewPagerFragmentWithTabs extends ViewPagerWithTabsFragment{

	private CardMakerTask task;
	private Activities activities;
	private AboutUs aboutUs;
	
	public MyViewPagerFragmentWithTabs(){
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		activities = new Activities();
		aboutUs = new AboutUs();
	}

	@Override
	protected boolean expandTabs(){
		return true;
	}
	
	
	@Override
	public ViewPagerHandler getViewPagerHandler(){
		return new ViewPagerHandler(App.getContext())
				.addPage(R.string.section1, new Activities())
				.addPage(R.string.section2, new AboutUs());
	}

	@Override
	public int defaultViewPagerPageSelectedPosition(){
		return 0;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

		// Retain state across configuration changes, i.e. screen orientation.
		setRetainInstance(true);
	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		if(task != null){
			task.onAttach(activities);
		}
	}

	@Override
	public void onDetach(){
		super.onDetach();
		if(task != null){
			task.onDetach();
		}
	}

	public void beginTask(Activities.CardMaker cardMaker){
		task = new CardMakerTask(activities);
		task.execute(cardMaker);
	}

}
