package offlineminds.com.mymovielist.appalarmmgr;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;

public class MovieAlarmReceiver extends BroadcastReceiver {

    // The app's AlarmManager, which provides access to the system alarm services.
    public AlarmManager alarmMgr;
    // The pending intent that is triggered when the alarm fires.
    public PendingIntent alarmPendingIntent;

    static String TAG = MovieAlarmReceiver.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d(TAG,"Alarm Received in Movie Alarm Receiver");

        Intent service = new Intent(context,ScheduledDataFetching.class);
        context.startService(service);
    }


    public void setAlarm(Context context){

        Log.d(TAG,"Inside SET ALARM OF ALARM RECEIVER");

        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MovieAlarmReceiver.class);

        alarmPendingIntent = PendingIntent.getActivity(context,0,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 00);
//        long interval = (1*60*1000);

        long interval = 1000;

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                /*calendar.getTimeInMillis() */ SystemClock.elapsedRealtime() + interval,
                interval ,
                alarmPendingIntent);
    }

    public void cancelAlarm(Context context) {
        // If the alarm has been set, cancel it.
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmPendingIntent);
        }
        // Disable {@code KitchenBroadCastReceiver} so that it doesn't automatically restart the
        // alarm when the device is rebooted.
        ComponentName receiver = new ComponentName(context, MovieBroadcastReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
