package com.rmh.rhoffman.cbtvelocity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
	private int NOTIFICATION_ID = 1;
	private String NOTIFICATION_TITLE;
	private String NOTIFICATION_CONTENT;
	private boolean WIFI_CONNECTION = false;
	private boolean MOBILE_CONNECTION = false;

	public Activities(){
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

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
				Log.d("Velocity", "error");
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
		swipe = (SwipeRefreshLayout) parentView.findViewById(R.id.swipe);
		swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
			@Override
			public void onRefresh(){
				new RefreshPage().execute(new CardMaker());
			}
		});
		swipe.setColorSchemeResources(R.color.accent);
	}

	private void getNetworkConnectivity(){
		ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isConnected()){
			WIFI_CONNECTION = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
			MOBILE_CONNECTION = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
			if(WIFI_CONNECTION){
				Log.d("NETWORK: ", "Wifi Connection");
				new CardMakerTask(this.getActivity(), this).execute(new CardMaker());
			} else if(MOBILE_CONNECTION){
				Log.d("NEWTORK: ", "Mobile Connection");
				new CardMakerTask(this.getActivity(), this).execute(new CardMaker());
			}
		}
	}

	private void sendNotification(){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(App.getContext());
		builder.setSmallIcon(R.mipmap.ic_v_notification);
		builder.setContentTitle(NOTIFICATION_TITLE);
		builder.setContentText(NOTIFICATION_CONTENT);

		Intent intent = new Intent(App.getContext(), Activities.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(App.getContext(), 0, intent, 0);

		//builder.setContentIntent(pendingIntent);

		NotificationManager manager = (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(NOTIFICATION_ID, builder.build());
		NOTIFICATION_ID++;
	}

	public void addCardsToRecyclerView(ArrayList<Card> list){
		this.list = list;
		adapter = new CardAdapter(list);
		recyclerView.setAdapter(adapter);
	}

	public static class CardMaker{

		private ArrayList<Card> list = new ArrayList<>();

		public ArrayList<Card> createCards(JSONArray jsonArray){

			if(jsonArray == null){
				list.add(exceptionCard());
			} else {
				int len = jsonArray.length();

				String s = "";
				String t = "";

				if(len >= 1){
					for(int i = 0; i < len; i++){
						try{
							JSONObject object = jsonArray.getJSONObject(i);

							Card card = new Card();
							card.setTitle(s + object.getString("DESCRIPTION"));
							card.setImageURL(t + object.getString("IMAGE"));
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

		private Card exceptionCard(){
			Drawable drawable = ContextCompat.getDrawable(App.getContext(), R.mipmap.ic_frown);
			Card card = new Card();
			card.setTitle("We're unable to connect to the server...");
			card.setImageDrawable(drawable);
			return card;
		}

	}

	private class CardMakerTaskA extends AsyncTask<CardMaker, Long, ArrayList<Card>>{
		@Override
		protected void onPreExecute(){
			swipe.post(new Runnable(){
				@Override
				public void run(){
					swipe.setRefreshing(true);
				}
			});
		}

		@Override
		protected ArrayList<Card> doInBackground(CardMaker... params){
			return params[0].createCards(new ApiConnector().getAllActivities());
		}

		@Override
		protected void onPostExecute(ArrayList<Card> cards){
			addCardsToRecyclerView(cards);
			swipe.setRefreshing(false);
		}
	}

	private class RefreshPage extends AsyncTask<CardMaker, Long, ArrayList<Card>>{

		@Override
		protected void onPreExecute(){
			swipe.setRefreshing(true);
		}

		@Override
		protected ArrayList<Card> doInBackground(CardMaker... params){
			return params[0].createCards(new ApiConnector().getAllActivities());
		}

		@Override
		protected void onPostExecute(ArrayList<Card> cards){
			recyclerView.removeAllViews();
			addCardsToRecyclerView(cards);
			swipe.setRefreshing(false);
		}
	}

}
