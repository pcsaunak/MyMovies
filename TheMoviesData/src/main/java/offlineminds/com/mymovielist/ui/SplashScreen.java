package offlineminds.com.mymovielist.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.ui.mainactivity.MainActivity;

public class SplashScreen extends AppCompatActivity {
    public static String TAG = SplashScreen.class.getName();
    Handler mainThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mainThread = new Handler();
        mainThread.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(mainActivityIntent);
            }
        }, 2000);
    }
}
