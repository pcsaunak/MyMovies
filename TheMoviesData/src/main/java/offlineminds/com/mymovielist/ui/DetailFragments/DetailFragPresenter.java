package offlineminds.com.mymovielist.ui.DetailFragments;

import java.util.List;
import java.util.Objects;

/**
 * Created by saunakc on 23/06/17.
 */

public interface DetailFragPresenter {

    public void getMovieTrailer(String movieId);
    public void onSuccessOfPresenter(List list);
    public void getAdditionalDetails(String movieId);
    public void additionalDetailsSuccess(Object obj);
}
