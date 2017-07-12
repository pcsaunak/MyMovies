package offlineminds.com.mymovielist.ui.ComedyFragment;

import java.util.List;

import offlineminds.com.mymovielist.networking.RetrofitCommunication;
import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 08/07/17.
 */

public class ComedyFragResImplemented implements ComedyFragResource {

    private ComedyFragmentPresenter comedyFragmentPresenter;

    public ComedyFragResImplemented(ComedyFragmentPresenter presenter) {
        this.comedyFragmentPresenter = presenter;
    }

    RetrofitCommunication retrofitCommunication=new RetrofitCommunication(this);

    @Override
    public void fetchDataFromAPI() {
        retrofitCommunication.getComedyMoviesJson();
    }

    @Override
    public void onSuccess(List<Result> results) {
        comedyFragmentPresenter.onSuccess(results);
    }

    @Override
    public void onFailedResponse() {

    }
}
