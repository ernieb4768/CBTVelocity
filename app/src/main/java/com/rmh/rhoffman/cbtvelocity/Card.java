package com.rmh.rhoffman.cbtvelocity;

import android.graphics.drawable.Drawable;

/**
 * Card
 */
public class Card{

	private String title;
	private Drawable image;
	private String imageURL;

	public Card(){

	}

	public Card(String title){
		this.title = title;
	}

	public Card(String title, Drawable image){
		this.title = title;
		this.image = image;
	}

	public Card(String title, String image){
		this.title = title;
		this.imageURL = image;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public void setImageDrawable(Drawable image){
		this.image = image;
	}

	public void setImageURL(String url){
		this.imageURL = url;
	}

	public String getTitle(){
		return title;
	}

	public Drawable getImageDrawable(){
		return image;
	}

	public String getImageURL(){
		return imageURL;
	}

}
