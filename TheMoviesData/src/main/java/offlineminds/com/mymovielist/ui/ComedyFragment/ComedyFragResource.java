package offlineminds.com.mymovielist.ui.ComedyFragment;

import java.util.List;
import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 21/06/17.
 */

public interface ComedyFragResource {
    public void fetchDataFromAPI();
    public void onSuccess(List<Result> results);
    public void onFailedResponse();
}
