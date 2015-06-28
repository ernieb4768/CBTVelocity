package com.rmh.rhoffman.cbtvelocity;

import android.app.Application;
import android.content.Context;

/**
 * A hack activity to get context whenever needed.
 */
public class App extends Application{

	private static Context context;

	@Override
	public void onCreate(){
		super.onCreate();
		context = this;
	}

	public static Context getContext(){
		return context;
	}

}
