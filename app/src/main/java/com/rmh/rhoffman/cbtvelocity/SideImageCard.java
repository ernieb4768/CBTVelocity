package com.rmh.rhoffman.cbtvelocity;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * A side image card class.
 */
public class SideImageCard {

	private String title;
	private String subtitle;
	private String imageURL;
	private Drawable image;
	private String buttonText;
	private OnButtonClickListener listener;
	private View.OnClickListener onClickListener;
	private boolean visibility = true;
	private boolean gone = false;

	public SideImageCard(){

	}

	public void setTitle(String title){
		this.title = title;
	}

	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}

	public void setTitle(int stringResource){
		this.title = App.getContext().getString(stringResource);
	}

	public void setSubtitle(int stringResource){
		this.subtitle = App.getContext().getString(stringResource);
	}

	public void setButtonText(String text){
		this.buttonText = text;
	}

	public void setImageURL(String url){
		this.imageURL = url;
	}

	public void setImage(Drawable image){
		this.image = image;
	}

	public void setImage(int imageResource){
		this.image = ContextCompat.getDrawable(App.getContext(), imageResource);
	}

	public String getTitle(){
		return title;
	}

	public String getSubtitle(){
		return subtitle;
	}

	public String getButtonText(){
		return buttonText;
	}

	public String getImageURL(){
		return imageURL;
	}

	public Drawable getImage(){
		return image;
	}

	public void setDividerVisibility(boolean visible){
		this.visibility = visible;
	}

	public boolean getDividerVisibility(){
		return visibility;
	}

	public void setButtonGone(boolean visible){
		this.gone = visible;
	}

	public boolean getButtonGone(){
		return gone;
	}

	public void setOnButtonClickListener(OnButtonClickListener listener){
		this.listener = listener;
	}

	public OnButtonClickListener getOnButtonClickListener(){
		return listener;
	}

	public void setOnClickListener(View.OnClickListener listener){
		this.onClickListener = listener;
	}

	public View.OnClickListener getOnClickListener(){
		return onClickListener;
	}

	public interface OnButtonClickListener {
		void onButtonClicked(View view, SideImageCard card);
	}

}
