package offlineminds.com.mymovielist.networking;

import android.util.Log;

import java.util.List;

import offlineminds.com.mymovielist.pojo.PopularMovies;
import offlineminds.com.mymovielist.pojo.Result;
import offlineminds.com.mymovielist.pojo.Trailer;
import offlineminds.com.mymovielist.pojo.TrailerDetails;
import offlineminds.com.mymovielist.ui.DetailFragments.DetailFragResource;
import offlineminds.com.mymovielist.ui.HomeFragment.HomeFragResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by saunakc on 18/06/17.
 */

public class RetrofitCommunication {
    public static String TAG = RetrofitCommunication.class.getName();
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);;
    List<Result> resultList;

    HomeFragResource homeFragResource;
    DetailFragResource detailFragResource;

    public RetrofitCommunication(DetailFragResource detailFragResource) {
        this.detailFragResource = detailFragResource;
    }

    public RetrofitCommunication(HomeFragResource homeFragResource) {
        this.homeFragResource = homeFragResource;
    }

    public void makeRequest(){
//        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call call = apiInterface.getPopularMovies();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + "");
                String displayResponse = "";


                PopularMovies popularMovies =(PopularMovies) response.body();
//                Integer text = popularMovies.getPage();
//
//                Integer totalPages = popularMovies.getTotalPages();

                resultList = popularMovies.getResults();
                homeFragResource.onSuccess(resultList);

//                displayResponse += text + " Page\n" + totalPages + " Total\n";
//                for (Result result: resultList){
//                    displayResponse += result.getId() + " " +
//                            result.getOriginalTitle() + " " +
//                            result.getReleaseDate() + "\n";
//
//                    Log.d(TAG,displayResponse);
//                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG,"Failed API CALL: "+t.toString());
                call.cancel();
            }
        });
    }

    public void getTrailerList(String movieId){
        Call trailerCall = apiInterface.getTrailers(movieId);
        trailerCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Trailer movieTrailer = (Trailer)response.body();
                List<TrailerDetails> trailerList = movieTrailer.getResults();
                detailFragResource.onSuccess(trailerList);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG,"Failed trailer response !! " + t.toString());
            }
        });
    }

}
