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
 */
public class SideImageCardAdapter extends RecyclerView.Adapter<SideImageCardAdapter.SideImageCardViewHolder>{

	public ArrayList<SideImageCard> cards;

	public SideImageCardAdapter(ArrayList<SideImageCard> sideImageCards){
		this.cards = sideImageCards;
	}


	public static class SideImageCardViewHolder extends RecyclerView.ViewHolder {
		public ImageView image;
		public TextView title;
		public TextView subTitle;
		public CardView cardView;
		public SideImageCardViewHolder(View view){
			super(view);
			cardView = (CardView) view.findViewById(R.id.sideImageCardLayout);
			title = (TextView) view.findViewById(R.id.titleText);
			subTitle = (TextView) view.findViewById(R.id.subTitleText);
			image = (ImageView) view.findViewById(R.id.sideImage);
		}
	}

	@Override
	public SideImageCardAdapter.SideImageCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.side_image_card_layout, parent, false);
		return new SideImageCardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SideImageCardAdapter.SideImageCardViewHolder holder, int position){
		holder.image.setImageDrawable(cards.get(position).getImage());
		holder.title.setText(cards.get(position).getTitle());
		holder.subTitle.setText(cards.get(position).getSubtitle());
	}

	@Override
	public int getItemCount(){
		return cards.size();
	}
}
