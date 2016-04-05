package com.rmh.rhoffman.cbtvelocity;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * An AsyncTask called on initial startup of the application to query the database and get the
 * JSON data necessary to create the Cards in the main UI.
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
		// Executed on the main thread before the background thread begins.
	}

	@Override
	protected ArrayList<Card> doInBackground(Activities.CardMaker... params){
		// Done on the background thread.
		return params[0].createCards(new ApiConnector().getAllActivities());
	}

	@Override
	protected void onPostExecute(ArrayList<Card> bigImageCards){
		// Done on the main thread after the background thread finishes.
		activities.addCardsToRecyclerView(bigImageCards);
		activities.swipe.setRefreshing(false);
	}
}
