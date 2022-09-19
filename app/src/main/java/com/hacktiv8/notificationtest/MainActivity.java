package com.hacktiv8.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CHANNEL_ID = "Important_mail_channel" ;
    NotificationManagerCompat mNotificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simpleNotif = (Button) findViewById(R.id.simple_notif);
        Button bigTextNotif = (Button) findViewById(R.id.bigtetxt_notif);
        Button bigPicNotif = (Button) findViewById(R.id.bigpicture_notif);
        Button inboxNotif = (Button) findViewById(R.id.inbox_notif);
        Button actionNotif = (Button) findViewById(R.id.action_notif);

        simpleNotif.setOnClickListener(this);
        bigPicNotif.setOnClickListener(this);
        bigTextNotif.setOnClickListener(this);
        inboxNotif.setOnClickListener(this);
        actionNotif.setOnClickListener(this);

        createNotifChannel();
        mNotificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.simple_notif:
                createSimpleNotif("Simple Notifikasi", "Deskrips Simple Notifikasi", 1);
                break;
            case R.id.bigtetxt_notif:
                createBigTextNotif("BigText Style Notifikasi", "Deskripsi BigText Style Notifikasi, Deskripsi BigText Style Notifikasi, Deskripsi BigText Style Notifikasi, Deskripsi BigText Style Notifikasi", 2);
                break;
            case R.id.bigpicture_notif:
                createBigPictureNotif("Big Picture Style","Big Picture Style Deskripsi",3);
                break;
            case R.id.inbox_notif:
                createInboxStyleNotif("Inbox Style","Inbox Style Deskripsi",4);
                break;
            case R.id.action_notif:
                createActionStyleNotif("Action Style","Action Style Deskripsi",5);
                break;
        }

    }

    private void createSimpleNotif(String title, String text, int notificationId) {
        mNotificationManagerCompat.cancelAll();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hacktiv8.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        mNotificationManagerCompat.notify(notificationId, notification);
    }

    private void createBigTextNotif(String title, String text, int notificationId) {
        mNotificationManagerCompat.cancelAll();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hacktiv8.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_notif);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle().bigText(text)
                .setBigContentTitle(null)
                .setSummaryText("BigTextStyle");

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(style)
                .setContentIntent(pendingIntent)
                .setLargeIcon(bitmap)
                .setAutoCancel(true)
                .build();

        mNotificationManagerCompat.notify(notificationId, notification);
    }

    private void createBigPictureNotif(String title, String text, int notificationId) {
        mNotificationManagerCompat.cancelAll();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hacktiv8.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_notif);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap)
                .setSummaryText("Big Picture Style is used to show large image")
                .setBigContentTitle(null)
                .bigLargeIcon(null);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(style)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        mNotificationManagerCompat.notify(notificationId, notification);
    }

    private void createInboxStyleNotif(String title, String text, int notificationId) {
        mNotificationManagerCompat.cancelAll();

        String line1 = "Hallo Apakabar?";
        String line2 = "Lagi dimana?";
        String line3 = "Kapan Pulang?";
        String line4 = "Sedang Apa?";
        String line5 = "Lagi dimana?";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hacktiv8.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                .addLine(line1)
                .addLine(line2)
                .addLine(line3)
                .addLine(line4)
                .addLine(line5)
                .setSummaryText("InboxStyle")
                .setBigContentTitle(null);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(style)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        mNotificationManagerCompat.notify(notificationId, notification);
    }


    private void createActionStyleNotif(String title, String text, int notificationId) {
        mNotificationManagerCompat.cancelAll();

        Intent intent = new Intent(getApplicationContext(), Receiver.class);
        intent.setAction("ACTIONN_CANCEL");
        intent.putExtra("id", notificationId);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(getApplicationContext(),
                        0,intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action actionDismiss =
                new NotificationCompat.Action.Builder(0, "Dismiss", pendingIntent).build();


        NotificationCompat.Action actionDelete=
                new NotificationCompat.Action.Builder(0, "Delete", pendingIntent).build();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is example BigText Style notif with action"))
                .addAction(actionDismiss)
                .addAction(actionDelete)
                .build();

        mNotificationManagerCompat.notify(notificationId, notification);
    }

    private void createNotifChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Important_mail_channel";
            String description = "This Chanel will show notif only to important people";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}