package com.rmh.rhoffman.cbtvelocity;

import android.os.Bundle;
import android.widget.ImageView;

import com.blunderer.materialdesignlibrary.activities.Activity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.squareup.picasso.Picasso;

public class ActivityDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView imageView = (ImageView) findViewById(R.id.detailsImageView);
		String imageURL = getIntent().getStringExtra("IMAGE");

		Picasso.with(this).load(imageURL).into(imageView);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_activity_details;
	}

	@Override
	protected boolean enableActionBarShadow() {
		return true;
	}

	@Override
	protected ActionBarHandler getActionBarHandler() {
		return new ActionBarDefaultHandler(this);
	}
}
