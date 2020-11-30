package com.omar.carrentaljo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by DELL on 27/02/2018.
 */

public class myFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent mIntent=new Intent(this,HomeActivity.class);
        mIntent.setFlags(mIntent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent mPendingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("FCM Noti");
        mBuilder.setContentText(remoteMessage.getNotification().getBody());
        mBuilder.setAutoCancel(true);
        mBuilder.setSmallIcon(R.drawable.lock);
        mBuilder.setContentIntent(mPendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
    }
}
