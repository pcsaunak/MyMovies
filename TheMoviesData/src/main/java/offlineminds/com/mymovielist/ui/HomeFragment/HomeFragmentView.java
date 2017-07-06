package offlineminds.com.mymovielist.ui.HomeFragment;

import java.util.List;

import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 16/06/17.
 */

public interface HomeFragmentView {
    public void updateDataFromAPI(List<Result> results);
    public void displayMessage(String info);
}
