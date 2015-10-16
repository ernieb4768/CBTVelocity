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

	public SideImageCard(){
		// Blank constructor
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

}
