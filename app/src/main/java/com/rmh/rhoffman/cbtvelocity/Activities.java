package com.rmh.rhoffman.cbtvelocity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Activities extends Fragment{

	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private CardAdapter adapter;
	private View parentView;
	public SwipeRefreshLayout swipe;
	private FragmentManager retainedChildFragmentManager;
	private CardMakerTask task;
	private Activity activity;
	private ArrayList<Card> list;
	private boolean WIFI_CONNECTION = false;
	private boolean MOBILE_CONNECTION = false;
	private SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.getContext());
	private boolean checkBox = sp.getBoolean("sync_frequency", false);
	private PendingIntent pendingIntent;

	public Activities(){
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		// Retrieve a PendingIntent that will perform a broadcast.
		Intent alarmIntent = new Intent(App.getContext(), AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(App.getContext(), 0, alarmIntent, 0);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		// Inflate the layout for this fragment
		parentView = inflater.inflate(R.layout.fragment_activities, container, false);

		setupSwipeToRefresh();
		getNetworkConnectivity();

		recyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerView);
		layoutManager = new LinearLayoutManager(App.getContext());
		recyclerView.setLayoutManager(layoutManager);

		adapter = new CardAdapter(null);
		recyclerView.setAdapter(adapter);

		return parentView;
	}

	@Override
	public void onStart(){
		super.onStart();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

		//setRetainInstance(true);
	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);

		if(retainedChildFragmentManager != null){
			try{
				Field childFMField = Fragment.class.getDeclaredField("mChildFragmentManager");
				childFMField.setAccessible(true);
				childFMField.set(this, retainedChildFragmentManager);
			} catch(NoSuchFieldException e){
				e.printStackTrace();
			} catch(IllegalAccessException e){
				e.printStackTrace();
				//Log.d("string", "string");
			}
		} else {
			retainedChildFragmentManager = getChildFragmentManager();
		}

		this.activity = activity;
		if(task != null){
			task.onAttach(activity);
		}
	}

	@Override
	public void onDetach(){
		super.onDetach();
		if(task != null){
			task.onDetach();
		}
	}

	/**
	 * This will start the swipe to refresh feature for the fragment. It is called when the fragment is created so the user
	 * can immediately refresh the content.
	 */
	private void setupSwipeToRefresh(){
		// Set up the layout by getting the resource id then set a listener.
		swipe = (SwipeRefreshLayout) parentView.findViewById(R.id.swipe);
		swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
			@Override
			public void onRefresh(){
				// Use wifi connection first if available; otherwise, make sure
				// the user allows it and use mobile connection.
				if(WIFI_CONNECTION){
					new RefreshPage().execute(new CardMaker());
				} else if(MOBILE_CONNECTION && !checkBox){
					new RefreshPage().execute(new CardMaker());
				}

			}
		});
		// Set the color as the accent color.
		swipe.setColorSchemeResources(R.color.accent);
	}

	/**
	 * First find out if there is an established network connection, and if there is a connection available find out if
	 * it is a wifi network or if it is a mobile network.
	 *
	 * This is used to save user data. In preferences, the user can choose to not use a mobile connection. This will
	 * save data, but it will mean the content cannot be updated or refreshed until they are back on wifi. When available,
	 * wifi connection is always used first.
	 */
	private void getNetworkConnectivity(){
		// Request connection type from the system.
		ConnectivityManager connectivityManager =
				(ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		// Ensure networkInfo has gotten the active network info and make sure connection is established.
		if(networkInfo != null && networkInfo.isConnected()){
			WIFI_CONNECTION = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
			MOBILE_CONNECTION = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
			// Checks to see if the connection is Wifi, and if so uses it.
			if(WIFI_CONNECTION){
				new CardMakerTask(this.getActivity(), this).execute(new CardMaker());
			// Checks to see if the connections is Mobile, if it is it will also make sure that
			// the user allows us to use their mobile data before we connect.
			} else if(MOBILE_CONNECTION && !checkBox){
				new CardMakerTask(this.getActivity(), this).execute(new CardMaker());
			}
		}
	}

	// Updates the recyclerview with the new adapter that actually contains the data.
	public void addCardsToRecyclerView(ArrayList<Card> list){
		this.list = list;
		adapter = new CardAdapter(list);
		recyclerView.setAdapter(adapter);
	}

	/**
	 * This class takes the JSON data retrieved from the database and extracts it into individual Cards.
	 */
	public static class CardMaker{

		private ArrayList<Card> list = new ArrayList<>();

		public ArrayList<Card> createCards(JSONArray jsonArray){

			// Make sure if nothing is retrieved from the database there is still something displayed other than an error.
			if(jsonArray == null){
				list.add(exceptionCard());
			} else {
				int len = jsonArray.length();

				// Loop through the array and use the data from each loop to create a Card.
				if(len >= 1){
					for(int i = 0; i < len; i++){
						try{
							JSONObject object = jsonArray.getJSONObject(i);

							Card card = new Card();
							card.setTitle(object.getString("DESCRIPTION"));
							card.setImageURL(object.getString("IMAGE"));
							list.add(card);
						} catch(JSONException e){
							e.printStackTrace();
						}
					}
				} else {
					list.add(exceptionCard());
				}
			}
			return list;
		}

		// An exceptionCard is a default case to handle any time an internet connection can't be established.
		// It makes sure something is still displayed to the user so they know what happened.
		private Card exceptionCard(){
			Drawable drawable = ContextCompat.getDrawable(App.getContext(), R.mipmap.ic_frown);
			Card card = new Card();
			card.setTitle("Well this is embarrassing...");
			card.setImageDrawable(drawable);
			return card;
		}

	}

	/**
	 * Async task so that it can get new content from the database on a background thread without freezing the main
	 * UI thread. It also displays the Material styled circular refresh widget.
	 */
	private class RefreshPage extends AsyncTask<CardMaker, Long, ArrayList<Card>>{

		@Override
		protected void onPreExecute(){
			// Done on main thread before the background thread starts.
			swipe.setRefreshing(true);
		}

		@Override
		protected ArrayList<Card> doInBackground(CardMaker... params){
			// Done on the background thread.
			return params[0].createCards(new ApiConnector().getAllActivities());
		}

		@Override
		protected void onPostExecute(ArrayList<Card> cards){
			// Done on the main thread after the background thread has finished executing.
			recyclerView.removeAllViews();
			addCardsToRecyclerView(cards);
			swipe.setRefreshing(false);
		}
	}

}
