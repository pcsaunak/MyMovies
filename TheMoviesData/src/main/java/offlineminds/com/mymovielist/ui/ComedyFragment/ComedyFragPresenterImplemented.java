package offlineminds.com.mymovielist.ui.ComedyFragment;

import java.util.List;

import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 08/07/17.
 */

public class ComedyFragPresenterImplemented implements ComedyFragmentPresenter {

    private ComedyFragmentView comedyFragmentView;

    public ComedyFragPresenterImplemented(ComedyFragmentView comedyFragmentView) {
        this.comedyFragmentView = comedyFragmentView;
    }

    private ComedyFragResource comedyFragResource = new ComedyFragResImplemented(this);

    @Override
    public void getDataFromPresenter() {
        comedyFragResource.fetchDataFromAPI();
    }

    @Override
    public void callBackFromModel(String info) {

    }

    @Override
    public void onSuccess(List<Result> results) {
        comedyFragmentView.updateDataFromAPI(results);
    }
}
