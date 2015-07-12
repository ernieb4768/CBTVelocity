package com.rmh.rhoffman.cbtvelocity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.blunderer.materialdesignlibrary.activities.Activity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;


public class ContactUs extends Activity{

	private SwipeRefreshLayout swipe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// Make the layout refreshable... why? Contact information doesn't need refreshed.
		setSwipeToRefreshLayout();
	}

	@Override
	protected int getContentView(){
		return R.layout.activity_contact_us;
	}

	@Override
	protected ActionBarHandler getActionBarHandler(){
		return new ActionBarDefaultHandler(this);
	}

	private void setSwipeToRefreshLayout(){
		swipe = (SwipeRefreshLayout) findViewById(R.id.contact_swipe);
		swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
			@Override
			public void onRefresh(){
				new DelayThread().execute(new ContactUs());
			}
		});
		swipe.setColorSchemeResources(R.color.primary_dark);
	}

	private class DelayThread extends AsyncTask<ContactUs, Long, ContactUs>{

		@Override
		protected ContactUs doInBackground(ContactUs... params){
			try{
				Thread.sleep(3000); // 3000 ms is 3 seconds
			} catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ContactUs contactUs){
			swipe.setRefreshing(false);
		}
	}
}
