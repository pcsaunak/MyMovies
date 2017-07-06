package offlineminds.com.mymovielist.ui.HomeFragment;

import java.util.List;

import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 16/06/17.
 */

public class HomeFragPresenterImplemented implements HomeFragPresenter {


    HomeFragResource homeResource = new HomeFragResImplemented(this);
    HomeFragmentView homeView;

    public HomeFragPresenterImplemented(HomeFragmentView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getDataFromPresenter() {
        homeResource.fetchDataFromAPI();
    }

    @Override
    public void callBackFromModel(String info) {
//        homeView.displayMessage(info);
    }

    @Override
    public void onSuccess(List<Result> results) {
        homeView.updateDataFromAPI(results);
    }
}
