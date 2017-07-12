package offlineminds.com.mymovielist.ui.DetailFragments;

import java.util.List;

import offlineminds.com.mymovielist.networking.RetrofitCommunication;

/**
 * Created by saunakc on 23/06/17.
 */

public class DetailFragResImplemented implements DetailFragResource {

    DetailFragPresenter detailFragPresenter;
    RetrofitCommunication retrofitCommunication = new RetrofitCommunication(this);



    public DetailFragResImplemented(DetailFragPresenter detailFragPresenter) {
        this.detailFragPresenter = detailFragPresenter;
    }

    @Override
    public void getMovieTrailFromAPI(String movieId) {
        retrofitCommunication.getTrailerList(movieId);
    }

    @Override
    public void onSuccess(List list) {
        detailFragPresenter.onSuccessOfPresenter(list);
    }

    @Override
    public void getAdditionalMovieDetails(String movieId) {
        retrofitCommunication.getAdditionalMovieDetails(movieId);
    }

    @Override
    public void additionalDetailsSuccess(Object o) {
        detailFragPresenter.additionalDetailsSuccess(o);
    }
}
