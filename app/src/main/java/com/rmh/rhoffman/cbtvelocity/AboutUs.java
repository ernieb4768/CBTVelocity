package com.rmh.rhoffman.cbtvelocity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
	
	public AboutUs(){
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){

		rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

		ImageView imageView = (ImageView) rootView.findViewById(R.id.velocity_image);
		Picasso.with(App.getContext())
				.load("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/561991_162080323915090_640379256_n.jpg?oh=437c0a40f5728cf21d5b5a4a7d88c85f&oe=55E6B8EB")
				.into(imageView);

		// Inflate the layout for this fragment
		return rootView;
	}
	
	
}
