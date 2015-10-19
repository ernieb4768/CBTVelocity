package com.rmh.rhoffman.cbtvelocity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment{

	private View rootView;
	private FloatingActionButton fabMain;
	private FloatingActionButton fabCall;
	private FloatingActionButton fabMail;
	private int FAB_VISIBILITY = 0;
	
	public AboutUs(){
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){

		rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

		ImageView imageView = (ImageView) rootView.findViewById(R.id.velocity_image);
		Picasso.with(App.getContext())
				.load("http://174.100.202.101/VelocityPics/Velocity.jpg")
				.resize(250, 170)
				.into(imageView);

		fabMain = (FloatingActionButton) rootView.findViewById(R.id.fabMain);
		fabCall = (FloatingActionButton) rootView.findViewById(R.id.fabCall);
		fabMail = (FloatingActionButton) rootView.findViewById(R.id.fabMail);

		fabMain.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				setAdditionalFABs();
			}
		});

		// Inflate the layout for this fragment
		return rootView;
	}

	public void setAdditionalFABs(){
		if(FAB_VISIBILITY == 0){
			fabCall.setVisibility(View.VISIBLE);
			fabMail.setVisibility(View.VISIBLE);
			fabMain.setImageDrawable(ContextCompat.getDrawable(App.getContext(), R.drawable.ic_close));
			FAB_VISIBILITY = 1;
		} else if(FAB_VISIBILITY == 1){
			fabCall.setVisibility(View.INVISIBLE);
			fabMail.setVisibility(View.INVISIBLE);
			fabMain.setImageDrawable(ContextCompat.getDrawable(App.getContext(), R.drawable.ic_plus_sign));
			FAB_VISIBILITY = 0;
		}

		if(fabCall.getVisibility() == View.VISIBLE && fabMail.getVisibility() == View.VISIBLE){
			fabCall.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view){
					Intent call = new Intent(Intent.ACTION_DIAL);
					call.setData(Uri.parse("tel:3304776267"));
					startActivity(call);
				}
			});
			fabMail.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view){
					Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "mshulze@cantonbaptist.org", null));
					email.putExtra(Intent.EXTRA_SUBJECT, "Requesting Information");
					startActivity(Intent.createChooser(email, "Send Email"));
				}
			});
		}

	}

	public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

		public ScrollAwareFABBehavior(Context context, AttributeSet attrs){
			super();
		}

		@Override
		public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target,
		                           int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed){
			super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

			if(dyConsumed > 0 && child.getVisibility() == View.VISIBLE){
				child.hide();
			} else if(dyConsumed < 0 && child.getVisibility() != View.VISIBLE){
				child.show();
			}
		}
	}
	
}
