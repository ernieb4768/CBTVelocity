package com.rmh.rhoffman.cbtvelocity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Custom ListViewAdapter
 */
public class CreditsListViewAdapter extends BaseAdapter{

	private String[] allCredits;
	private Activity activity;
	private static LayoutInflater layoutInflater = null;

	public CreditsListViewAdapter(String[] credits, Activity activity){
		allCredits = credits;
		this.activity = activity;

		layoutInflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount(){
		return allCredits.length;
	}

	@Override
	public Object getItem(int position){
		return position;
	}

	@Override
	public long getItemId(int position){
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){

		// Set the views inside convertView if not done already.
		ListCell cell;
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.activity_credits, null);
			cell = new ListCell();
			cell.github = (ImageView) convertView.findViewById(R.id.credits_image_view);
			cell.textView = (TextView) convertView.findViewById(R.id.credits_text_view);

			convertView.setTag(cell);
		} else {
			cell = (ListCell) convertView.getTag();
		}

		cell.github.setImageResource(R.mipmap.ic_credits_github);
		cell.textView.setText(allCredits[position]);

		return convertView;
	}

	// A simple cell so an image can be
	// displayed next to the text.
	private class ListCell{
		private ImageView github;
		private TextView textView;
	}
}
