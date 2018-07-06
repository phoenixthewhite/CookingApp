package com.vnshine.phoenix.cooking.Firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vnshine.phoenix.cooking.Activity.AdActivity;
import com.vnshine.phoenix.cooking.Activity.SplashScreen;
import com.vnshine.phoenix.cooking.R;

import java.util.Map;


public class NotificationServices extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            handleNow(remoteMessage);
        }
        else {
            sendNotification(remoteMessage.getNotification().getBody(),new Intent(this, SplashScreen.class));
        }
    }
    private void handleNow(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String linkAd="";
        String thongBao="";
        try {
            linkAd=data.get("link");
            thongBao=data.get("thongBao");
            Log.d(TAG, "Message data payload: " + linkAd);
            Intent intent;
            if(linkAd.contains("play.google.com")){
                Uri uri = Uri.parse(linkAd); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
            }else {
                intent = new Intent(this, AdActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("link",linkAd);
                intent.putExtras(bundle);
            }
            sendNotification(thongBao,intent);
        }catch (Exception e){

        }


    }

    private void sendNotification(String messageBody,Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Drawable drawable=getApplicationInfo().loadIcon(getPackageManager());
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.cooking)
                .setLargeIcon(bitmap)
                .setContentTitle("Cooking Daily ")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
