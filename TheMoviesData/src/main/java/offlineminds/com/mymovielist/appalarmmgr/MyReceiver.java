package offlineminds.com.mymovielist.appalarmmgr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import offlineminds.com.mymovielist.R;

public class MyReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        mp=MediaPlayer.create(context, R.raw.alrm	);
        mp.start();
        Log.d("MYRECEIVER","ALARM SET SUCCESSFULLY");
//        Toast.makeText(context, "Alarm Receiver set", Toast.LENGTH_SHORT).show();

        Intent service = new Intent(context,ScheduledDataFetching.class);
        context.startService(service);
    }
}
