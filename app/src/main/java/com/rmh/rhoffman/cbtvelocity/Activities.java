package com.rmh.rhoffman.cbtvelocity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.view.MaterialListView;


public class Activities extends Fragment{

	private MaterialListView listView;
	private View parentView;

	public Activities(){
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		// Inflate the layout for this fragment
		parentView = inflater.inflate(R.layout.fragment_activities, container, false);
		makeCards();


		return parentView;
	}

	private void makeCards(){
		listView = (MaterialListView) parentView.findViewById(R.id.material_list);


		BigImageCard card1 = new BigImageCard(App.getContext());
		card1.setDescription("Akron RubberDucks game on Wednesday July 1st at 5:30");
		card1.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/11054402_746431758813274_6803565050479255580_n.jpg?oh=9fae9acb3af43fc2630a4e61d79d05de&oe=5617849A");
		listView.add(card1);

		BigImageCard card2 = new BigImageCard(App.getContext());
		card2.setDescription("Join us at Cedar Point June 19 at 7:30am at door H");
		card2.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xfa1/v/t1.0-9/11147109_739692586153858_52468208660866365_n.jpg?oh=455fcac84e1398a84016a3c52971742e&oe=56162007");
		listView.add(card2);

		BigImageCard card3 = new BigImageCard(App.getContext());
		card3.setDescription("The mother of all over-nighters! May 22 at the church.");
		card3.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xat1/v/t1.0-9/11262424_724220394367744_7119878786666733536_n.jpg?oh=b2ed9e5db2d8a83f68ecc95cd891d42f&oe=5623905E");
		listView.add(card3);

		BigImageCard card4 = new BigImageCard(App.getContext());
		card4.setDescription("A Road Rally and Scavenger Hunt. April 25 at 6pm.");
		card4.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/11127478_715537038569413_1513343816801030173_n.jpg?oh=9305c4e92a111cbe4479dda5c6b07f6c&oe=561E5FB3");
		listView.add(card4);

		BigImageCard card5 = new BigImageCard(App.getContext());
		card5.setDescription("A night of bowling for all skill levels on March 28 at 5pm.");
		card5.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xfp1/v/t1.0-9/10981698_695993583857092_2438554694640548017_n.jpg?oh=26ebb861677413f593daeac056101e03&oe=56324241");
		listView.add(card5);

		BigImageCard card6 = new BigImageCard(App.getContext());
		card6.setDescription("Spend a couple days tubing, skiing, or Snowboarding February 15 and 16.");
		card6.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xfa1/v/t1.0-9/10968353_676073152515802_3523744850794322153_n.jpg?oh=c9d6a58b6423b38fa1d9d2ccb9779db9&oe=561CEA76");
		listView.add(card6);

		BigImageCard card7 = new BigImageCard(App.getContext());
		card7.setDescription("Come watch the Superbowl with us!");
		card7.setDrawable("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xta1/v/t1.0-9/10563003_667785456677905_2945233557562009288_n.jpg?oh=51267615c3e05decd1cea6bd6cd65ba9&oe=561C0C41");
		listView.add(card7);

	}

}
