package offlineminds.com.mymovielist.ui.DetailFragments;

import java.util.List;

import offlineminds.com.mymovielist.pojo.AdditionalDetails;

/**
 * Created by saunakc on 23/06/17.
 */

public interface DetailFragmentView {
    public void onSuccessOfView(List list);
    public void AdditionalDetailsSuccess(AdditionalDetails details);
}
