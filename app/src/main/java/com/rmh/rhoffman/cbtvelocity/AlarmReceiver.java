package com.rmh.rhoffman.cbtvelocity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 * A BroadcastReceiver extension to set an alarm that fires every 24 hours and triggers the app to check for new
 * notifications. If there are new ones received it will send it to the user.
 */
public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Placeholder code for testing
		// FIXME: 3/21/16
		Toast.makeText(App.getContext(), "I'm working", Toast.LENGTH_LONG).show();
	}
}
