package com.rmh.rhoffman.cbtvelocity;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * An AsyncTask.
 */
public class CardMakerTask extends AsyncTask<Activities.CardMaker, Long, ArrayList<Card>>{

	private Activity activity;
	private Activities activities;

	public CardMakerTask(Activity activity, Activities activities){
		this.activities = activities;
		onAttach(activity);
	}

	public void onAttach(Activity activity){
		this.activity = activity;
	}

	public void onDetach(){
		activity = null;
	}

	@Override
	protected void onPreExecute(){
		// Necessary in order to show the circular progress bar before content is loaded. Without this code setRefreshing(true)
		// does not work because this method is called before the fragment has completed onCreateView().
		//if(activity != null && activities != null){
			/*activities.swipe.post(new Runnable(){
				@Override
				public void run(){
					activities.swipe.setRefreshing(true);
				}
			});*/
		//}
	}

	@Override
	protected ArrayList<Card> doInBackground(Activities.CardMaker... params){
		return params[0].createCards(new ApiConnector().getAllActivities());
	}

	@Override
	protected void onPostExecute(ArrayList<Card> bigImageCards){
		activities.addCardsToRecyclerView(bigImageCards);
		activities.swipe.setRefreshing(false);
	}
}
