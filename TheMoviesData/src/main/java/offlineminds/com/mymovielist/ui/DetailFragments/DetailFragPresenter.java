package offlineminds.com.mymovielist.ui.DetailFragments;

import java.util.List;

/**
 * Created by saunakc on 23/06/17.
 */

public interface DetailFragPresenter {

    public void getMovieTrailer(String movieId);
    public void onSuccessOfPresenter(List list);
}
