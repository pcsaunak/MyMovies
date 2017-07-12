package offlineminds.com.mymovielist.ui.ComedyFragment;

import java.util.List;
import offlineminds.com.mymovielist.pojo.Result;


/**
 * Created by saunakc on 21/06/17.
 */

public interface ComedyFragmentPresenter {
    public void getDataFromPresenter();
    public void callBackFromModel(String info);
    public void onSuccess(List<Result> results);
}
