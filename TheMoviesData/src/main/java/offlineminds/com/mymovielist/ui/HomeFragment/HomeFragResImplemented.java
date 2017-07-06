package offlineminds.com.mymovielist.ui.HomeFragment;


import android.util.Log;

import java.util.List;

import offlineminds.com.mymovielist.pojo.Result;
import offlineminds.com.mymovielist.networking.RetrofitCommunication;

/**
 * Created by saunakc on 16/06/17.
 */

public class HomeFragResImplemented implements HomeFragResource {

    static String TAG = HomeFragResImplemented.class.getName();
    RetrofitCommunication retrofitObj = new RetrofitCommunication(this);
    HomeFragPresenter homeFragPresenter;
    List<Result> resultList;

    public HomeFragResImplemented(HomeFragPresenter presenter) {
        this.homeFragPresenter=presenter;
    }

    @Override
    public void fetchDataFromAPI() {
        Log.d(TAG,"Inside fetchData");
//        networkObj.makeGetRequest();
        retrofitObj.makeRequest();
    }

    @Override
    public void onSuccess(List<Result> results) {

        String displayResponse = "";
        resultList=results;
        for (Result result: resultList){
            displayResponse += result.getId() + " " +
                    result.getOriginalTitle() + " " +
                    result.getAdult() + " " +
                    result.getGenreIds() + " " +
                    result.getReleaseDate() + "\n";
        }
        Log.d(TAG,displayResponse);

        homeFragPresenter.onSuccess(resultList);

    }

    @Override
    public void onFailedResponse() {
//        homePresenter.callBackFromModel("failed");
    }


}
