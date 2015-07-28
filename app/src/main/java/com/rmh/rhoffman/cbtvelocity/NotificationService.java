package com.rmh.rhoffman.cbtvelocity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

/**
 * Adds notifications to the app.
 */
public class NotificationService extends Service{

	@Override
	public IBinder onBind(Intent intent){
		return null;
	}

	@Override
	public void onCreate(){
		Uri tone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		NotificationManager notification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent intent1 = new Intent(App.getContext(), MainActivity.class);
		PendingIntent pendingIntent1 = PendingIntent.getActivity(App.getContext(), 0, intent1, 0);

		Notification mNotify = new Notification.Builder(App.getContext())
				.setContentTitle("Velocity Event")
				.setContentText("There is an upcoming activity that you are invited to attend!")
				.setSmallIcon(R.mipmap.ic_v_notification)
				.setContentIntent(pendingIntent1)
				.setSound(tone)
				.build();

		notification.notify(1, mNotify);
	}
}
