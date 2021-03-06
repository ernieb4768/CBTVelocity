package com.rmh.rhoffman.cbtvelocity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerStyleHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.models.Account;


public class MainActivity extends NavigationDrawerActivity{

	public MyViewPagerFragmentWithTabs fragment;
	private FragmentTransaction ft;

	public static final String DATABASE_ADDRESS = "http://76.188.89.113/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		// Stores orientation, ie Portrait or Landscape.
		int orientation = getResources().getConfiguration().orientation;

		// Fragment Transaction is completed with commit() in the if statement below.
		ft = getSupportFragmentManager().beginTransaction();

		// Attempts to restore the state on orientation change, so the fragment isn't
		// destroyed and recreated every time the device is rotated.
		if(getSupportFragmentManager().findFragmentByTag("TaskFragment") == null){
			ft.add(R.id.fragment_container, new MyViewPagerFragmentWithTabs(), "TaskFragment");
			ft.commit();
		} else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
			fragment = (MyViewPagerFragmentWithTabs) getSupportFragmentManager().findFragmentByTag("TaskFragment");
			ft.replace(R.id.fragment_container, fragment, "TaskFragment");
			ft.commit();
		} else if(orientation == Configuration.ORIENTATION_PORTRAIT){
			ft.replace(R.id.fragment_container, new MyViewPagerFragmentWithTabs(), "TaskFragment");
			ft.commit();
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStart(){
		super.onStart();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch(id){
			// Open the Credits activity if selected. Currently that's the only option.
			case R.id.action_credits:
				Intent credits = new Intent(App.getContext(), Credits.class);
				startActivity(credits);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected boolean enableActionBarShadow() {
		return true;
	}

	@Override
	protected ActionBarHandler getActionBarHandler(){
		return null;
	}

	@Override
	public NavigationDrawerStyleHandler getNavigationDrawerStyleHandler() {
		return null;
	}

	@Override
	public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler(){
		return null;
	}

	@Override
	public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler(){
		return null;
	}

	@Override
	public void onNavigationDrawerAccountChange(Account account){

	}

	// The top part of the Navigation Drawer. This is where the CBT, Velocity, and social media website links are.
	@Override
	public NavigationDrawerTopHandler getNavigationDrawerTopHandler(){
		String cbtURL = "http://www.cantonbaptist.org";
		String facebookURL = "https://www.facebook.com/cbt.velocity";
		String instagramURL = "https://www.instagram.com/velocity_cbt";
		String velocityURL = "http://www.cantonbaptist.org/ministries/high-school";

		return new NavigationDrawerTopHandler(App.getContext())
				.addItem("Learn More", R.mipmap.ic_velocity_circle, new Intent(Intent.ACTION_VIEW).setData(Uri.parse(velocityURL)))
				.addItem("Facebook", R.mipmap.ic_facebook_circle, new Intent(Intent.ACTION_VIEW).setData(Uri.parse(facebookURL)))
				.addItem("Instagram", R.mipmap.ic_instagram, new Intent(Intent.ACTION_VIEW).setData(Uri.parse(instagramURL)))
				.addItem("CBT", R.mipmap.ic_cbt_circle, new Intent(Intent.ACTION_VIEW).setData(Uri.parse(cbtURL)));
	}

	// The bottom part of the Navigation Drawer. This is where the settings and contact parts are.
	@Override
	public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler(){
		return new NavigationDrawerBottomHandler(App.getContext())
				.addSettings(new View.OnClickListener(){
					@Override
					public void onClick(View v){
						Intent intent = new Intent(App.getContext(), SettingsActivity.class);
						startActivity(intent);
					}
				})
				.addItem("Contact Us", R.drawable.ic_contact_us_image, new View.OnClickListener(){
					@Override
					public void onClick(View v){
						Intent intent = new Intent(App.getContext(), ContactUs.class);
						startActivity(intent);
					}
				});
	}

	// This is so the ActionBar stays visible when the drawer opens and so the hamburger menu
	// animation is visible the whole time.
	@Override
	public boolean overlayActionBar(){
		return false;
	}

	@Override
	public boolean replaceActionBarTitleByNavigationDrawerItemTitle(){
		return false;
	}

	@Override
	public int defaultNavigationDrawerItemSelectedPosition(){
		return 0;
	}

}
