package offlineminds.com.mymovielist.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.ui.mainactivity.MainActivity;

public class SplashScreen extends AppCompatActivity {

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
