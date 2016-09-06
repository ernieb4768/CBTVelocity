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
import android.view.ViewTreeObserver;
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
	private boolean FAB_VISIBILITY = false;
	
	public AboutUs(){
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){

		// Inflate the main view.
		rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

		// Get the scrollView.
		scrollView = (ScrollView) rootView.findViewById(R.id.scrollViewAbout);

		// Set a listener on the ScrollView. Since there isn't a default onScrollListener for a scrollView
		// I had to add a globalLayoutListener to the ViewTreeObserver, then remove the globalLayoutListener
		// and add a onScrollChangedListener to the observer.
		// It's a little bit hacky, but it works.
		scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				ViewTreeObserver observer = scrollView.getViewTreeObserver();
				observer.removeOnGlobalLayoutListener(this);

				observer.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
					@Override
					public void onScrollChanged() {
						if(FAB_VISIBILITY){
							hideFABs();
						}
					}
				});
			}
		});

		// Load the Velocity image into the imageView.
		ImageView imageView = (ImageView) rootView.findViewById(R.id.velocity_image);
		Picasso.with(App.getContext())
				.load(MainActivity.DATABASE_ADDRESS + "VelocityPics/Velocity.jpg")
				.resize(250, 170)
				.into(imageView);

		// Get the FABs.
		fabMain = (FloatingActionButton) rootView.findViewById(R.id.fabMain);
		fabCall = (FloatingActionButton) rootView.findViewById(R.id.fabCall);
		fabMail = (FloatingActionButton) rootView.findViewById(R.id.fabMail);

		// Set the listener to expand the main FAB to show the other FABs.
		fabMain.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				setAdditionalFABs();
			}
		});

		// Inflate the layout for this fragment
		return rootView;
	}

	// Necessary for the ViewTreeObserver listener.
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if(!isVisibleToUser){
			hideFABs();
		}
	}

	// If all FABs are visible when the Activity or Fragment is paused the FABs should hide.
	@Override
	public void onPause() {
		super.onPause();
		if(FAB_VISIBILITY) {
			hideFABs();
		}
	}

	// Called on any scroll event in any direction.
	// If the additional FABs are shown this will hide them as soon as there is a scroll event.
	private void hideFABs(){
		if(FAB_VISIBILITY) {
			FAB_VISIBILITY = false;

			animateAdditionalFABsOut();
		}
	}

	public void setAdditionalFABs(){

		// If FABs are not visible, make them visible with animation and update their visibility code to true.
		if(!FAB_VISIBILITY){
			animateAdditionalFABsIn();
			FAB_VISIBILITY = true;
		// If FABs are visible, make them not visible with animation and update their visibility code to false.
		} else {
			animateAdditionalFABsOut();
			FAB_VISIBILITY = false;
		}

		// Set onClick listeners only if they are visible. This is important because making them invisible means the user
		// can no longer see them, but they still exist on the screen so if the listener is still on and they touch that
		// part of the screen it will register the click.
		if(FAB_VISIBILITY){
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

	// Animates the additional FABs into view with an upward motion and a fade in at the same time.
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

		// Changes the main FAB image action to let user know that clicking again will hide the FABs.
		fabMain.setImageDrawable(ContextCompat.getDrawable(App.getContext(), R.drawable.ic_action_close));
	}

	// Resets the FABs and animates them out with a downward motion and a fade out.
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

		// Changes the main FAB image action to let the user know it can re-expand.
		fabMain.setImageDrawable(ContextCompat.getDrawable(App.getContext(), R.drawable.ic_action_add));
	}
	
}
