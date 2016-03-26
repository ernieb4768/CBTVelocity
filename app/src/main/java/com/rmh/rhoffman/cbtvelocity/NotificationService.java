package com.rmh.rhoffman.cbtvelocity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

/**
 * Adds notifications to the app.
 */
public class NotificationService {

	private static String TITLE;
	private static String CONTENT;

	// If this constructor is called there will be additional setup needed to create the notification.
	// The setNotificationTitle() and setNotificationContent() methods will be needed then you will need to call createNotification().
	public NotificationService(){
		// Empty constructor.
	}

	/**
	 * Create a notification and set it to go off at the appropriate time.
	 */
	public static void createNotification(){

		// Set up the notification manager and create the intents to handle the action when clicked.
		NotificationManager notification = (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent1 = new Intent(App.getContext(), MainActivity.class);
		PendingIntent pendingIntent1 = PendingIntent.getActivity(App.getContext(), 0, intent1, 0);

		// Build the notification and set it to go off.
		Notification notify = new Notification.Builder(App.getContext())
				.setContentTitle(TITLE)
				.setContentText(CONTENT)
				.setSmallIcon(R.mipmap.ic_v_notification)
				.setContentIntent(pendingIntent1)
				.setSound(ringTone())
				.build();

		notification.notify(1, notify);
	}

	/**
	 * Get the default notification tone to be played with the notification goes off.
	 *
	 * @return Uri
	 */
	private static Uri ringTone(){
		Uri tone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		return tone;
	}

	public static void setNotificationTitle(String title){
		TITLE = title;
	}

	public static void setNotificationContent(String content){
		CONTENT = content;
	}
}
