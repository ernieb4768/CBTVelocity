package com.rmh.rhoffman.cbtvelocity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.models.Account;


public class MainActivity extends NavigationDrawerActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fragment_container, new MyViewPagerFragmentWithTabs());
		ft.commit();

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
			case R.id.action_settings:
				return true;
			case R.id.action_learn_more:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected ActionBarHandler getActionBarHandler(){
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

	@Override
	public NavigationDrawerTopHandler getNavigationDrawerTopHandler(){
		String cbtURL = "http://www.cantonbaptist.org";
		String facebookURL = "https://www.facebook.com/cbt.velocity";
		String velocityURL = "http://www.cantonbaptist.org/ministries/high-school";

		return new NavigationDrawerTopHandler(App.getContext())
				.addItem("Learn More", R.mipmap.ic_launcher_crop, new Intent(Intent.ACTION_VIEW).setData(Uri.parse(velocityURL)))
				.addItem("Facebook", R.mipmap.ic_facebook, new Intent(Intent.ACTION_VIEW).setData(Uri.parse(facebookURL)))
				.addItem("CBT", R.mipmap.ic_cbt, new Intent(Intent.ACTION_VIEW).setData(Uri.parse(cbtURL)));
	}

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
						Toast.makeText(App.getContext(), "Contact Us", Toast.LENGTH_SHORT).show();
					}
				});
	}

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
