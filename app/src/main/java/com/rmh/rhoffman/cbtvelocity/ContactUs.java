package com.rmh.rhoffman.cbtvelocity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.blunderer.materialdesignlibrary.activities.Activity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.dexafree.materialList.cards.BasicButtonsCard;
import com.dexafree.materialList.cards.OnButtonPressListener;
import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.view.MaterialListView;


public class ContactUs extends Activity{

	private MaterialListView cardView;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		cardView = (MaterialListView) findViewById(R.id.card_list);

		setUpAddress();
		setUpPhone();
		setUpEmail();
	}

	@Override
	protected int getContentView(){
		return R.layout.activity_contact_us;
	}

	@Override
	protected ActionBarHandler getActionBarHandler(){
		return new ActionBarDefaultHandler(this);
	}

	private void setUpAddress(){
		SmallImageCard address = new SmallImageCard(this);
		address.setTitle("Mailing Address");
		address.setDescription(R.string.address);
		address.setDrawable(R.mipmap.ic_cbt);
		cardView.add(address);
	}

	// This sets up a card with the church's phone number, and has a button in the bottom left that will open the default dialer app
	// with the church number already entered, so all the user has to do is hit send.
	private void setUpPhone(){
		BasicButtonsCard phone = new BasicButtonsCard(this);
		phone.setTitle("Phone Number");
		phone.setDescription("(330)-477-6267");
		phone.setLeftButtonText("Call");
		phone.setDividerVisible(true);
		phone.setOnLeftButtonPressedListener(new OnButtonPressListener(){
			@Override
			public void onButtonPressedListener(View view, Card card){
				Intent call = new Intent(Intent.ACTION_DIAL);
				call.setData(Uri.parse("tel:3304776267"));
				startActivity(call);
			}
		});
		cardView.add(phone);
	}

	// This sets up a card with Mike Shulze's email address, and has a button in the bottom left that will open a chooser dialog to
	// allow the user to pick their preferred email app, which will start an email to Mike with the subject Requesting Information.
	private void setUpEmail(){
		BasicButtonsCard email = new BasicButtonsCard(this);
		email.setTitle("Email Address");
		email.setDescription("mshulze@cantonbaptist.org");
		email.setLeftButtonText("Email");
		email.setDividerVisible(true);
		email.setOnLeftButtonPressedListener(new OnButtonPressListener(){
			@Override
			public void onButtonPressedListener(View view, Card card){
				Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "mshulze@cantonbaptist.org", null));
				email.putExtra(Intent.EXTRA_SUBJECT, "Requesting Information");
				startActivity(Intent.createChooser(email, "Send Email"));
			}
		});
		cardView.add(email);
	}

}
