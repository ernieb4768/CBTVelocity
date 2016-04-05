package com.rmh.rhoffman.cbtvelocity;

import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Custom adapter to create the card view and be able to reuse the view.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

	public ArrayList<Card> array;
	private int lastPosition = -1;

	public CardAdapter(ArrayList<Card> cards){
		this.array = cards;

	}

	// Creates the container to hold the view. To expand the view to contain more information
	// you have to add the additional widgets here, in the layout, and in the Card.java file.
	public static class CardViewHolder extends RecyclerView.ViewHolder {
		public ImageView image;
		public TextView text;
		public CardView cardView;
		public CardViewHolder(View view){
			super(view);
			cardView = (CardView) view.findViewById(R.id.cardLayout);
			text = (TextView) view.findViewById(R.id.cardText);
			image = (ImageView) view.findViewById(R.id.cardImage);
		}
	}

	@Override
	public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
		// Inflate the layout and return the view.
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
		return new CardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final CardViewHolder holder, int position){

		// Gets contents from the array from the database and puts in into the ViewHolder.
		// Always check if value returned from db is null before adding to holder.
		if(array.get(position).getImageURL() != null){
			Picasso.with(App.getContext()).load(array.get(position).getImageURL()).into(holder.image);
			holder.text.setText(array.get(position).getTitle());
		} else if(array.get(position).getImageDrawable() != null){
			holder.image.setImageDrawable(array.get(position).getImageDrawable());
			holder.text.setText(array.get(position).getTitle());
		}

		// On Lollipop and above, apply animations to the cards as they are added to the recyclerview.
		// Code will be skipped on pre-lollipop devices.
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			setAnimation(holder.cardView, position);
		}
	}

	@Override
	public void onViewDetachedFromWindow(CardViewHolder holder){
		super.onViewDetachedFromWindow(holder);
		holder.cardView.clearAnimation();
	}

	// Sets an animation on the FAB for Lollipop and above.
	// Code will be skipped for pre-lollipop devices.
	private void setAnimation(View viewToAnimate, int position){
		if(position > lastPosition){
			Animation animation = AnimationUtils.loadAnimation(App.getContext(), R.anim.slide_in_left);
			viewToAnimate.startAnimation(animation);
			lastPosition = position;
		}
	}

	// Required so the recyclerView knows how many times to recycle the view.
	@Override
	public int getItemCount(){
		if(array == null){
			return 0;
		}
		return array.size();
	}

}