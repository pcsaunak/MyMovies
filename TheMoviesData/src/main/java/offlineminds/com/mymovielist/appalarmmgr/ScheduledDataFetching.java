package offlineminds.com.mymovielist.appalarmmgr;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import offlineminds.com.mymovielist.networking.RetrofitCommunication;

/**
 * Created by saunakc on 11/07/17.
 */

public class ScheduledDataFetching extends IntentService{

    static String TAG = ScheduledDataFetching.class.getName();
    RetrofitCommunication myNetworkComObj;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ScheduledDataFetching(String name) {
        super(name);
    }

    public ScheduledDataFetching(){
        super("SchedulingService ");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        myNetworkComObj = new RetrofitCommunication(getApplicationContext());
        Log.d(TAG,"Inside on Handle Intent");
        Toast.makeText(this, "Inside On Handle Intent", Toast.LENGTH_SHORT).show();

        myNetworkComObj.getPopularMoviesJson();
    }
}
