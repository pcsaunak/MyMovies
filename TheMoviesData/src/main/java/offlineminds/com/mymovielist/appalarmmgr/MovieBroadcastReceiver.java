package offlineminds.com.mymovielist.appalarmmgr;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MovieBroadcastReceiver extends BroadcastReceiver {

    public MovieAlarmReceiver alarmReceiver = new MovieAlarmReceiver();
    static String TAG = MovieBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            Log.d(TAG,"BOOT COMPLETE INTENT RECEIVED ");
        }else {
            alarmReceiver.setAlarm(context);
        }
    }
}
