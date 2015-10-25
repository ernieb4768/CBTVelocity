package com.rmh.rhoffman.cbtvelocity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.blunderer.materialdesignlibrary.activities.ListViewActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;


public class Credits extends ListViewActivity{

	private String[] creditsArray = {"Denis Mondon: material-design-library", "Squareup: picasso image loading library"};
	private String[] urlArray = {
			"https://github.com/DenisMondon/material-design-library/blob/master/LICENSE",
			"https://github.com/square/picasso/blob/master/LICENSE.txt"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	protected ActionBarHandler getActionBarHandler(){
		return null;
	}

	@Override
	public ListAdapter getListAdapter(){

		return new CreditsListViewAdapter(creditsArray, this);
	}

	@Override
	public boolean useCustomContentView(){
		return false;
	}

	@Override
	public int getCustomContentView(){
		return 0;
	}

	@Override
	public boolean pullToRefreshEnabled(){
		return false;
	}

	@Override
	public int[] getPullToRefreshColorResources(){
		return new int[0];
	}

	@Override
	public void onRefresh(){

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
		Intent intent =	new Intent(Intent.ACTION_VIEW).setData(Uri.parse(urlArray[i]));
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
		return false;
	}
}
