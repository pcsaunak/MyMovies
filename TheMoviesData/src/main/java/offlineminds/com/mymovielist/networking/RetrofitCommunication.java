package offlineminds.com.mymovielist.networking;

import android.content.Context;
import android.util.Log;

import java.util.List;

import offlineminds.com.mymovielist.config;
import offlineminds.com.mymovielist.pojo.AdditionalDetails;
import offlineminds.com.mymovielist.pojo.ComedyMovies;
import offlineminds.com.mymovielist.pojo.PopularMovies;
import offlineminds.com.mymovielist.pojo.Result;
import offlineminds.com.mymovielist.pojo.Trailer;
import offlineminds.com.mymovielist.pojo.TrailerDetails;
import offlineminds.com.mymovielist.sqlite.DBHelper;
import offlineminds.com.mymovielist.ui.ComedyFragment.ComedyFragResource;
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

    DBHelper dbHelperForRetrofitCom;
    HomeFragResource homeFragResource;
    DetailFragResource detailFragResource;
    ComedyFragResource comedyFragResource;

    public RetrofitCommunication(Context context) {
        dbHelperForRetrofitCom = new DBHelper(context);
    }

    public RetrofitCommunication(ComedyFragResource comedyFragResource) {
        this.comedyFragResource = comedyFragResource;
    }

    public RetrofitCommunication(DetailFragResource detailFragResource) {
        this.detailFragResource = detailFragResource;
    }

    public RetrofitCommunication(HomeFragResource homeFragResource) {
        this.homeFragResource = homeFragResource;
    }

    public void getPopularMoviesJson(){

        Call call = apiInterface.getPopularMovies();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " ");

                PopularMovies popularMovies =(PopularMovies) response.body();
                resultList = popularMovies.getResults();
                for(Result myResult : resultList){
                    dbHelperForRetrofitCom.insertIntoResult(myResult, config.TABLE_RESULT);
                }
//                homeFragResource.onSuccess(resultList);

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


    public void getComedyMoviesJson(){
        Call comedyMoviesJson = apiInterface.getCommedyMovies();
        comedyMoviesJson.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ComedyMovies comedyMovies = (ComedyMovies)response.body();
                resultList = comedyMovies.getResults();
                comedyFragResource.onSuccess(resultList);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG,"COMEDY MOVIES FAILED REQUEST: "+t.toString());
            }
        });
    }


    public void getAdditionalMovieDetails(String movieId){

        Call<AdditionalDetails> getAdditionalDetails = apiInterface.getAdditionalDetails(movieId);
        getAdditionalDetails.enqueue(new Callback<AdditionalDetails>() {
            @Override
            public void onResponse(Call<AdditionalDetails> call, Response<AdditionalDetails> response) {
                AdditionalDetails moreMovieDetails = (AdditionalDetails)response.body();
                Log.d(TAG,"RUNTIME OF MOVIE: ##### " + moreMovieDetails.getRuntime());
                detailFragResource.additionalDetailsSuccess(moreMovieDetails);
            }

            @Override
            public void onFailure(Call<AdditionalDetails> call, Throwable t) {
                Log.d(TAG,"Additonal Details FAILED REQUEST: "+t.toString());
            }
        });
    }

}
