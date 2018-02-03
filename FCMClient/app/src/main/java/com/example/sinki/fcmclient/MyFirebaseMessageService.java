package com.example.sinki.fcmclient;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Sinki on 9/14/2017.
 */

public class MyFirebaseMessageService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String body;
        String title;
        if(remoteMessage.getNotification()!=null)
        {
            body = remoteMessage.getNotification().getBody();
            title = "google";
            //hienThiThongBao(remoteMessage.getNotification().getBody());
        }
        else {
            body = remoteMessage.getData().get("body");
            title = remoteMessage.getData().get("title");
            //hienThiThongBao(remoteMessage.getData().get("body"),remoteMessage.getData().get("title"));
        }
        hienThiThongBao(body,title);
    }

    private void hienThiThongBao(String body, String title) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pending = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder buider = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(sound)
                .setContentIntent(pending);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,buider.build());
    }

    private void hienThiThongBao(String body) {
        hienThiThongBao(body,"google");
    }
}
