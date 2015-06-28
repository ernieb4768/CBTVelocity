package com.rmh.rhoffman.cbtvelocity;


import android.app.Fragment;

import com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyViewPagerFragmentWithTabs extends ViewPagerWithTabsFragment{
	
	
	public MyViewPagerFragmentWithTabs(){
		// Required empty public constructor
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
}
