package com.rmh.rhoffman.cbtvelocity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.controller.RecyclerItemClickListener;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.view.MaterialListView;

public class Activities extends Fragment{

	private MaterialListView listView;
	private View parentView;
	private SwipeRefreshLayout swipe;

	//private String fileToOpen = "https://www.dropbox.com/s/nq702ybwsr39x58/images.txt?dl=0";

	private String[] imageURLS = {
			"https://scontent-ord1-1.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/11054402_746431758813274_6803565050479255580_n.jpg?oh=9fae9acb3af43fc2630a4e61d79d05de&oe=5617849A",
			"https://scontent-ord1-1.xx.fbcdn.net/hphotos-xfa1/v/t1.0-9/11147109_739692586153858_52468208660866365_n.jpg?oh=455fcac84e1398a84016a3c52971742e&oe=56162007",
			"https://scontent-ord1-1.xx.fbcdn.net/hphotos-xat1/v/t1.0-9/11262424_724220394367744_7119878786666733536_n.jpg?oh=b2ed9e5db2d8a83f68ecc95cd891d42f&oe=5623905E",
			"https://scontent-ord1-1.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/11127478_715537038569413_1513343816801030173_n.jpg?oh=9305c4e92a111cbe4479dda5c6b07f6c&oe=561E5FB3",
			"https://scontent-ord1-1.xx.fbcdn.net/hphotos-xfp1/v/t1.0-9/10981698_695993583857092_2438554694640548017_n.jpg?oh=26ebb861677413f593daeac056101e03&oe=56324241",
			"https://scontent-ord1-1.xx.fbcdn.net/hphotos-xfa1/v/t1.0-9/10968353_676073152515802_3523744850794322153_n.jpg?oh=c9d6a58b6423b38fa1d9d2ccb9779db9&oe=561CEA76",
			"https://scontent-ord1-1.xx.fbcdn.net/hphotos-xta1/v/t1.0-9/10563003_667785456677905_2945233557562009288_n.jpg?oh=51267615c3e05decd1cea6bd6cd65ba9&oe=561C0C41"};

	public Activities(){
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		// Inflate the layout for this fragment
		parentView = inflater.inflate(R.layout.fragment_activities, container, false);
		swipe = (SwipeRefreshLayout) parentView.findViewById(R.id.swipe);
		swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
			@Override
			public void onRefresh(){
				new RefreshPage().execute(new Activities());
			}
		});
		swipe.setColorSchemeResources(R.color.primary_dark);

		makeCards();

		//new GetAllActivitiesTask().execute(new ReadFile(fileToOpen));

		return parentView;
	}

	private void makeCards(){
		listView = (MaterialListView) parentView.findViewById(R.id.material_list);
		int len = imageURLS.length;

		for(int i = 0; i < len; i++){
			BigImageCard card = new BigImageCard(App.getContext());
			card.setDrawable(imageURLS[i]);
			listView.add(card);
		}

		listView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener(){
			@Override
			public void onItemClick(CardItemView cardItemView, int i){
				// Do nothing for now.

			}

			@Override
			public void onItemLongClick(CardItemView cardItemView, int i){
				// Do nothing...for now.
			}
		});
	}

	private class GetAllActivitiesTask extends AsyncTask<ReadFile, Long, String[]>{

		@Override
		protected String[] doInBackground(ReadFile... params){
			// This is executed on the background thread.
			return params[0].openFile();
		}

		@Override
		protected void onPostExecute(String[] strings){
			// This is executed on the main thread.
			makeCards();
		}

	}

	private class RefreshPage extends AsyncTask<Activities, Long, Activities>{

		@Override
		protected Activities doInBackground(Activities... params){
			try{
				Thread.sleep(3000); // 3000 ms is 3 seconds
			} catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Activities activities){
			swipe.setRefreshing(false);
		}
	}

}
