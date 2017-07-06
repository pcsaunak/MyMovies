package offlineminds.com.mymovielist.networking;

import android.provider.Settings;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import offlineminds.com.mymovielist.config;
import offlineminds.com.mymovielist.ui.HomeFragment.HomeFragResImplemented;
import offlineminds.com.mymovielist.ui.HomeFragment.HomeFragResource;

/**
 * Created by saunakc on 16/06/17.
 */

public class NetworkCommunication {
    public static String TAG = NetworkCommunication.class.getName();

//    HomeFragResource homeResource = new HomeFragResImplemented();

    public void makeGetRequest(){

        Log.d(TAG,"Inside Network Communication");

        AndroidNetworking.get("http://api.themoviedb.org/3/movie/popular?api_key=503f36886ff6cc467d3a00842eb9c4bc")
                .addHeaders("Accept", "application/json")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        homeResource.onSuccess();
                        System.out.println(response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {
//                        homeResource.onFailedResponse();
                        System.out.println(anError.toString());
                    }
                });
    }
}
