package offlineminds.com.mymovielist.ui.HomeFragment;

import java.util.List;

import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 16/06/17.
 */

public interface HomeFragPresenter {
    public void getDataFromPresenter();
    public void callBackFromModel(String info);
    public void onSuccess(List<Result> results);
}
