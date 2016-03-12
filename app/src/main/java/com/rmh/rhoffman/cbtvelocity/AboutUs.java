package com.rmh.rhoffman.cbtvelocity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment{

	private View rootView;
	private ScrollView scrollView;
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

		scrollView = (ScrollView) rootView.findViewById(R.id.scrollViewAbout);

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
			animateAdditionalFABsIn();
			FAB_VISIBILITY = 1;
		} else {
			animateAdditionalFABsOut();
			FAB_VISIBILITY = 0;
		}

		//fabCall.getVisibility() == View.VISIBLE && fabMail.getVisibility() == View.VISIBLE
		if(FAB_VISIBILITY == 1){
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
					Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "bjennings@cantonbaptist.org", null));
					email.putExtra(Intent.EXTRA_SUBJECT, "Requesting Information about Velocity");
					startActivity(Intent.createChooser(email, "Send Email"));
				}
			});
		}

	}

	public void animateAdditionalFABsIn(){
		Animation animation = AnimationUtils.loadAnimation(App.getContext(), R.anim.slide_up);
		animation.setAnimationListener(new Animation.AnimationListener(){
			@Override
			public void onAnimationStart(Animation animation){

			}

			@Override
			public void onAnimationEnd(Animation animation){
				fabCall.setVisibility(View.VISIBLE);
				fabMail.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation){

			}
		});

		fabCall.startAnimation(animation);
		fabMail.startAnimation(animation);

		fabMain.setImageDrawable(ContextCompat.getDrawable(App.getContext(), R.drawable.ic_action_close));
	}

	public void animateAdditionalFABsOut(){
		Animation animation = AnimationUtils.loadAnimation(App.getContext(), R.anim.slide_down);
		animation.setAnimationListener(new Animation.AnimationListener(){
			@Override
			public void onAnimationStart(Animation animation){

			}

			@Override
			public void onAnimationEnd(Animation animation){
				fabCall.setVisibility(View.INVISIBLE);
				fabMail.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation){

			}
		});

		fabCall.startAnimation(animation);
		fabMail.startAnimation(animation);

		fabMain.setImageDrawable(ContextCompat.getDrawable(App.getContext(), R.drawable.ic_action_add));
	}
	
}
