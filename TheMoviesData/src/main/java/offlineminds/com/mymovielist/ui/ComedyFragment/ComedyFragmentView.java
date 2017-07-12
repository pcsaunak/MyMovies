package offlineminds.com.mymovielist.ui.ComedyFragment;

import java.util.List;

import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 21/06/17.
 */

public interface ComedyFragmentView {
    public void updateDataFromAPI(List<Result> results);
    public void displayMessage(String info);
}
