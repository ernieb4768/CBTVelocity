package com.rmh.rhoffman.cbtvelocity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

	private void setUpPhone(){
		BasicButtonsCard phone = new BasicButtonsCard(this);
		phone.setTitle("Phone Number");
		phone.setDescription("(330)-417-6267");
		phone.setLeftButtonText("Call");
		phone.setDividerVisible(true);
		phone.setOnLeftButtonPressedListener(new OnButtonPressListener(){
			@Override
			public void onButtonPressedListener(View view, Card card){
				Toast.makeText(App.getContext(), "Calling...", Toast.LENGTH_SHORT).show();
			}
		});
		cardView.add(phone);
	}

	private void setUpEmail(){
		BasicButtonsCard email = new BasicButtonsCard(this);
		email.setTitle("Email Address");
		email.setDescription("mshulze@cantonbaptist.org");
		email.setLeftButtonText("Email");
		email.setDividerVisible(true);
		email.setOnLeftButtonPressedListener(new OnButtonPressListener(){
			@Override
			public void onButtonPressedListener(View view, Card card){
				Toast.makeText(App.getContext(), "Sending...", Toast.LENGTH_SHORT).show();
			}
		});
		cardView.add(email);
	}

}
