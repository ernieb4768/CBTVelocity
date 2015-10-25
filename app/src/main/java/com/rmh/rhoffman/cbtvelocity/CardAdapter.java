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
 * Custom adapter to create the card view.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

	public ArrayList<Card> array;
	private int lastPosition = -1;

	public CardAdapter(ArrayList<Card> cards){
		this.array = cards;

	}

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
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
		return new CardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final CardViewHolder holder, int position){

		if(array.get(position).getImageURL() != null){
			Picasso.with(App.getContext()).load(array.get(position).getImageURL()).into(holder.image);
			holder.text.setText(array.get(position).getTitle());
		} else if(array.get(position).getImageDrawable() != null){
			holder.image.setImageDrawable(array.get(position).getImageDrawable());
			holder.text.setText(array.get(position).getTitle());
		}


		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			setAnimation(holder.cardView, position);
		}
	}

	@Override
	public void onViewDetachedFromWindow(CardViewHolder holder){
		super.onViewDetachedFromWindow(holder);
		holder.cardView.clearAnimation();
	}

	private void setAnimation(View viewToAnimate, int position){
		if(position > lastPosition){
			Animation animation = AnimationUtils.loadAnimation(App.getContext(), R.anim.slide_up);
			viewToAnimate.startAnimation(animation);
			lastPosition = position;
		}
	}

	@Override
	public int getItemCount(){
		if(array == null){
			return 0;
		}
		return array.size();
	}

}