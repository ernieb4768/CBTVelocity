package com.rmh.rhoffman.cbtvelocity;

import android.app.Application;
import android.content.Context;

/**
 * Used to gain access to Application context with one short line of code from anywhere in any code file.
 *
 * All you need is to call App.getContext() and it will return the context of the app from the very beginning of the lifecycle.
 */
public class App extends Application{

	// The static Context object that is returned.
	private static Context context;

	// The very first method called when the Application is created, and it sets the Context.
	@Override
	public void onCreate(){
		super.onCreate();
		context = this;
	}

	// Returns the Context created and stored at Application creation, and returns it to anywhere in the app.
	public static Context getContext(){
		return context;
	}

}
