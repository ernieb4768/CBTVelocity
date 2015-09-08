package com.rmh.rhoffman.cbtvelocity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.controller.RecyclerItemClickListener;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.view.MaterialListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class Activities extends Fragment{

	private MaterialListView listView;
	private View parentView;
	public SwipeRefreshLayout swipe;

	public Activities(){
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		//setRetainInstance(true);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		// Inflate the layout for this fragment
		parentView = inflater.inflate(R.layout.fragment_activities, container, false);

		Log.d("Fragment", "Activities initialized...");
		setupSwipeToRefresh();

		if(listView == null){
			new CardMakerTask(this).execute(new CardMaker());
		}

		return parentView;
	}

	@Override
	public void onStart(){
		super.onStart();

		/*Activity activity = getActivity();
		if(activity instanceof MainActivity){
			Log.d("Activity", "Activity is MainActivity");
			MainActivity mainActivity = (MainActivity) activity;
			//mainActivity.startSavingFragmentState();
		} else {
			Log.d("Activity", "Activity is not MainActivity");
		}*/
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

	public void addCardsToListView(Collection<Card> list){
		listView = (MaterialListView) parentView.findViewById(R.id.material_list);

		listView.addAll(list);

		listView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener(){
			@Override
			public void onItemClick(CardItemView cardItemView, int i){
				// Do nothing...for now. I think eventually I'm going to add another activity that will open when an an item is
				// clicked. The activity will be a details screen giving more info than what is on the card.
				Intent intent = new Intent(App.getContext(), CardDetails.class);
				//startActivity(intent);
			}

			@Override
			public void onItemLongClick(CardItemView cardItemView, int i){
				// Do nothing... there will probably not be a long click feature, but the method will stay so that adding that
				// functionality later on if necessary is easy.
			}
		});
	}

	public static class CardMaker{

		private Collection<Card> list = new ArrayList<>();

		public Collection<Card> createCards(JSONArray jsonArray){

			if(jsonArray == null){
				list.add(exceptionCard());
			} else {
				int len = jsonArray.length();

				String s = "";

				if(len >= 1){
					for(int i = 0; i < len; i++){
						try{
							JSONObject object = jsonArray.getJSONObject(i);
							InputStream inputStream = (InputStream) new URL(object.getString("IMAGE")).getContent();
							Drawable image = Drawable.createFromStream(inputStream, "IMAGE");

							BigImageCard card = new BigImageCard(App.getContext());
							card.setDescription(s + object.getString("DESCRIPTION"));
							card.setDrawable(image);
							list.add(card);
						} catch(JSONException | IOException e){
							e.printStackTrace();
						}
					}
				} else {
					list.add(exceptionCard());
				}
			}
			return list;
		}

		private BigImageCard exceptionCard(){
			BigImageCard card = new BigImageCard(App.getContext());
			card.setDescription("We're unable to connect to the server...");
			card.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/561991_162080323915090_640379256_n.jpg?oh=437c0a40f5728cf21d5b5a4a7d88c85f&oe=55E6B8EB");
			return card;
		}

	}

	private class CardMakerTaskA extends AsyncTask<CardMaker, Long, Collection<Card>>{
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
		protected Collection<Card> doInBackground(CardMaker... params){
			return params[0].createCards(new ApiConnector().getAllActivities());
		}

		@Override
		protected void onPostExecute(Collection<Card> bigImageCards){
			addCardsToListView(bigImageCards);
			swipe.setRefreshing(false);
		}
	}

	private class RefreshPage extends AsyncTask<CardMaker, Long, Collection<Card>>{

		@Override
		protected void onPreExecute(){
			swipe.setRefreshing(true);
		}

		@Override
		protected Collection<Card> doInBackground(CardMaker... params){
			return params[0].createCards(new ApiConnector().getAllActivities());
		}

		@Override
		protected void onPostExecute(Collection<Card> bigImageCards){
			listView.clear();
			addCardsToListView(bigImageCards);
			swipe.setRefreshing(false);
		}
	}

}
