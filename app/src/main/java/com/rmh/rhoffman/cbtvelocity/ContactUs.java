package com.rmh.rhoffman.cbtvelocity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blunderer.materialdesignlibrary.activities.Activity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;

import java.util.ArrayList;


public class ContactUs extends Activity{

	private RecyclerView cardView;
	private RecyclerView.LayoutManager layoutManager;
	private SideImageCardAdapter adapter;
	private ArrayList<SideImageCard> cards = new ArrayList<>();

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setUpCards();

		cardView = (RecyclerView) findViewById(R.id.card_list);
		layoutManager = new LinearLayoutManager(App.getContext());
		cardView.setLayoutManager(layoutManager);
		adapter = new SideImageCardAdapter(cards);
		cardView.setAdapter(adapter);
	}

	@Override
	protected int getContentView(){
		return R.layout.activity_contact_us;
	}

	@Override
	protected boolean enableActionBarShadow() {
		return true;
	}

	@Override
	protected ActionBarHandler getActionBarHandler(){
		return new ActionBarDefaultHandler(this);
	}

	// Called during creation.
	private void setUpCards(){
		setUpAddress();
		setUpPhone();
		setUpEmail();
	}

	// Sets up a card with the church logo and the mailing address. It sets the button to GONE so that not only is it not
	// visible, but it is actually destroyed.
	private void setUpAddress(){
		SideImageCard address = new SideImageCard();
		address.setTitle(R.string.address_title);
		address.setSubtitle(R.string.address);
		address.setImage(R.mipmap.ic_cbt_circle);
		address.setDividerVisibility(false);
		address.setButtonGone(true);
		cards.add(address);
	}

	// This sets up a card with the church's phone number, and has a button in the bottom left that will open the default dialer app
	// with the church number already entered, so all the user has to do is hit send.
	private void setUpPhone(){
		SideImageCard phone = new SideImageCard();
		phone.setTitle("Phone Number");
		phone.setSubtitle("(330)-477-6267");
		phone.setImage(R.mipmap.ic_cbt_circle);
		phone.setButtonText("CALL");
		phone.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Intent call = new Intent(Intent.ACTION_DIAL);
				call.setData(Uri.parse("tel:3304776267"));
				startActivity(call);
			}
		});
		cards.add(phone);
	}

	// This sets up a card with Ben Jennings' email address, and has a button in the bottom left that will open a chooser dialog to
	// allow the user to pick their preferred email app, which will start an email to Ben with the subject Requesting Information.
	private void setUpEmail(){
		SideImageCard email = new SideImageCard();
		email.setTitle("Email Address");
		email.setSubtitle("zanderson@cantonbaptist.org");
		email.setImage(R.mipmap.ic_cbt_circle);
		email.setButtonText("EMAIL");
		email.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "zanderson@cantonbaptist.org", null));
				email.putExtra(Intent.EXTRA_SUBJECT, "Requesting Information about Velocity");
				startActivity(Intent.createChooser(email, "Send Email"));
			}
		});
		cards.add(email);
	}

}
