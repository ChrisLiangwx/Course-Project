package com.wl.exercise4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Locale;

public class TimerService extends Service {
    private int seconds = 0;
    private int timeLimit = Integer.MAX_VALUE;
    private boolean isRunning = false; // 控制计时器的状态
    private boolean isDisplayed = false;

    public static final int NOTIFICATION_ID = 1234;
    public static final String CHANNEL_ID = "com.wl.exercise4.channel";

    public class TimerBinder extends Binder {
        TimerService getTimer() {
            return TimerService.this;
        }
    }

    private final IBinder binder = new TimerBinder();

    @Override
    public IBinder onBind(Intent intent) {
        createNotificationChannel();
        countTime();
        return binder;
    }

    public void setTimeLimit(int newTimeLimit) {
        timeLimit = newTimeLimit;
    }

    public void startTimer() {
        isRunning = true;
    }

    public void stopTimer() {
        isRunning = false;
    }

    private void toastAndNotify() {
        if (seconds > timeLimit && !isDisplayed) {
            Toast.makeText(this, "timer expired!", Toast.LENGTH_SHORT).show();
            showNotification();
            isDisplayed = true;
        }
    }

    private void countTime() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    toastAndNotify();
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public String getTime() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{0, 1000})
                .setAutoCancel(true);

        Intent actionIntent = new Intent(this, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(actionPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
