package com.rmh.rhoffman.cbtvelocity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.controller.RecyclerItemClickListener;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.view.MaterialListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.goncalves.pugnotification.notification.PugNotification;

public class Activities extends Fragment{

	private MaterialListView listView;
	private View parentView;
	private SwipeRefreshLayout swipe;

	public Activities(){
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		// Inflate the layout for this fragment
		parentView = inflater.inflate(R.layout.fragment_activities, container, false);

		setupSwipeToRefresh();
		new GetAllActivitiesTask().execute(new ApiConnector());

		return parentView;
	}

	private void setupSwipeToRefresh(){
		swipe = (SwipeRefreshLayout) parentView.findViewById(R.id.swipe);
		swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
			@Override
			public void onRefresh(){
				new RefreshPage().execute(new ApiConnector());
			}
		});
		swipe.setColorSchemeResources(R.color.primary_dark);
	}

	private void makeCards(JSONArray jsonArray){
		listView = (MaterialListView) parentView.findViewById(R.id.material_list);
		int len = jsonArray.length();
		String s = "";

		for(int i = 0; i < len; i++){
			JSONObject object = null;
			try{
				object = jsonArray.getJSONObject(i);
				BigImageCard card = new BigImageCard(App.getContext());
				card.setDescription(s + object.getString("DESCRIPTION"));
				card.setDrawable(s + object.getString("IMAGE"));
				listView.add(card);
			} catch(JSONException e){
				e.printStackTrace();
			}
		}

		listView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener(){
			@Override
			public void onItemClick(CardItemView cardItemView, int i){
				// Add notification, for now.
				PugNotification.with(App.getContext())
						.load()
						.smallIcon(R.mipmap.ic_v_notification)
						.largeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_v_notification))
						.autoCancel(true)
						.title("Velocity")
						.message("Upcoming activity!")
						.click(Activities.class)
						.wear()
						.background(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_cbt))
						.build();
			}

			@Override
			public void onItemLongClick(CardItemView cardItemView, int i){
				// Do nothing...for now.
			}
		});
	}

	private class GetAllActivitiesTask extends AsyncTask<ApiConnector, Long, JSONArray>{

		@Override
		protected JSONArray doInBackground(ApiConnector... params){
			// This is executed on the background thread.
			return params[0].getAllActivities();
		}

		@Override
		protected void onPostExecute(JSONArray jsonArray){
			// This is executed on the main thread.
			makeCards(jsonArray);
		}

	}

	private class RefreshPage extends AsyncTask<ApiConnector, Long, JSONArray>{

		@Override
		protected void onPreExecute(){
			swipe.setRefreshing(true);
		}

		@Override
		protected JSONArray doInBackground(ApiConnector... params){
			return params[0].getAllActivities();
		}

		@Override
		protected void onPostExecute(JSONArray jsonArray){
			listView.clear();
			makeCards(jsonArray);
			swipe.setRefreshing(false);
		}
	}

}
