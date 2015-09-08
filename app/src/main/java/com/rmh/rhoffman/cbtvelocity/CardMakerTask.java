package com.rmh.rhoffman.cbtvelocity;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.dexafree.materialList.model.Card;

import java.util.Collection;

/**
 * An AsyncTask.
 */
public class CardMakerTask extends AsyncTask<Activities.CardMaker, Long, Collection<Card>>{

	private Fragment fragment;

	public CardMakerTask(Fragment fragment){
		onAttach(fragment);
	}

	public void onAttach(Fragment fragment){
		this.fragment = fragment;
	}

	public void onDetach(){
		fragment = null;
	}

	@Override
	protected void onPreExecute(){
		// Necessary in order to show the circular progress bar before content is loaded. Without this code setRefreshing(true)
		// does not work because this method is called before the fragment has completed onCreateView().
		if(fragment != null){
			((Activities) fragment).swipe.post(new Runnable(){
				@Override
				public void run(){
					((Activities) fragment).swipe.setRefreshing(true);
				}
			});
		}
	}

	@Override
	protected Collection<Card> doInBackground(Activities.CardMaker... params){
		return params[0].createCards(new ApiConnector().getAllActivities());
	}

	@Override
	protected void onPostExecute(Collection<Card> bigImageCards){
		((Activities)fragment).addCardsToListView(bigImageCards);
		((Activities)fragment).swipe.setRefreshing(false);
	}
}
