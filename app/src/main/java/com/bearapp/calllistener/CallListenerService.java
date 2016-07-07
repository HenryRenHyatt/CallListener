package com.bearapp.calllistener;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallListenerService extends Service {
    private static final String TAG = "CallListenerService";

    public CallListenerService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate>>>>>>>");
        // 获取电话管理器
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        // 监听电话状态
        telephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }


    private class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                Toast.makeText(getApplicationContext(), incomingNumber + " 来电", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand>>>>>>>");
        // 设置为前台服务
        Intent notifyIntent = new Intent(this, SettingsActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 1, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_notification_ring)
                .setColor(getResources().getColor(R.color.green))
                .setContentTitle(getString(R.string.notification_title))
                .setContentIntent(notifyPendingIntent)
                .build();
        startForeground(1, notification);
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
}
