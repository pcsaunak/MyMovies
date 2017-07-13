package offlineminds.com.mymovielist.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.appalarmmgr.MyReceiver;
import offlineminds.com.mymovielist.ui.mainactivity.MainActivity;

public class SplashScreen extends AppCompatActivity {
    public static String TAG = SplashScreen.class.getName();
    Handler mainThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setNewAlarm(getApplicationContext());
        mainThread = new Handler();
        mainThread.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainActivityIntent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(mainActivityIntent);
            }
        }, 10000);
    }


    public void setNewAlarm(Context context) {
        Intent myIntent = new Intent(context, MyReceiver.class);
        PendingIntent myPendingIntent = PendingIntent.getBroadcast(context, 1231231, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long interval = (3 * 60 * 60 * 1000);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, myPendingIntent);

        Toast.makeText(context, "Alarm Set After " + (3 * 60) + " seconds", Toast.LENGTH_SHORT).show();
    }

}
