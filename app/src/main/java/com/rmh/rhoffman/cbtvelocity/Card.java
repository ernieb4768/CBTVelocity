package com.rmh.rhoffman.cbtvelocity;

import android.graphics.drawable.Drawable;

/**
 * Card. Creates an object to store all the data needed to create a card.
 */
public class Card{

	private String title;
	private Drawable image;
	private String imageURL;

	public Card(){
		// Empty constructor.
	}

	// Set the title but have a blank image.
	public Card(String title){
		this.title = title;
	}

	// Set the title and image for the card with a drawable.
	public Card(String title, Drawable image){
		this.title = title;
		this.image = image;
	}

	// Set the title and image for the card with a String of a URL
	// that points to an image on the internet.
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
