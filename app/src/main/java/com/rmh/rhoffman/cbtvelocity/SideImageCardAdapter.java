package com.rmh.rhoffman.cbtvelocity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom recycler view adapter for a side image card.
 *
 * Now that I'm thinking about it, I'm not sure why I used a RecyclerView for non-scrolling content.
 * I could have just used the layout editor and made it all static content...
 */

// TODO: Replace with static content
public class SideImageCardAdapter extends RecyclerView.Adapter<SideImageCardAdapter.SideImageCardViewHolder>{

	public ArrayList<SideImageCard> cards;
	public View.OnClickListener listener;

	public SideImageCardAdapter(ArrayList<SideImageCard> sideImageCards){
		this.cards = sideImageCards;
	}

	// Set up all of the views necessary for a SideImageCard.
	public static class SideImageCardViewHolder extends RecyclerView.ViewHolder {
		public ImageView image;
		public TextView title;
		public TextView subTitle;
		public TextView buttonText;
		public View divider;
		public CardView cardView;
		public SideImageCardViewHolder(View view){
			super(view);
			cardView = (CardView) view.findViewById(R.id.sideImageCardLayout);
			title = (TextView) view.findViewById(R.id.titleText);
			subTitle = (TextView) view.findViewById(R.id.subTitleText);
			image = (ImageView) view.findViewById(R.id.sideImage);
			buttonText = (TextView) view.findViewById(R.id.cardButtonTextView);
			divider = view.findViewById(R.id.divider);
		}
	}

	// Inflate the view and return it.
	@Override
	public SideImageCardAdapter.SideImageCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.side_image_card_layout, parent, false);
		return new SideImageCardViewHolder(view);
	}

	// Set all of the information necessary for each individual card.
	@Override
	public void onBindViewHolder(SideImageCardAdapter.SideImageCardViewHolder holder, int position){
		holder.image.setImageDrawable(cards.get(position).getImage());
		holder.title.setText(cards.get(position).getTitle());
		holder.subTitle.setText(cards.get(position).getSubtitle());
		holder.buttonText.setText(cards.get(position).getButtonText());
		if(cards.get(position).getDividerVisibility()){
			holder.divider.setVisibility(View.VISIBLE);
		} else {
			holder.divider.setVisibility(View.INVISIBLE);
		}
		if(cards.get(position).getButtonGone()){
			holder.buttonText.setVisibility(View.GONE);
		}

		listener = cards.get(position).getOnClickListener();
		holder.buttonText.setOnClickListener(listener);
	}

	// Required so the recyclerView knows how many times to recycle the view.
	@Override
	public int getItemCount(){
		return cards.size();
	}
}
