package offlineminds.com.mymovielist.ui.DetailFragments;

import java.util.List;

/**
 * Created by saunakc on 23/06/17.
 */

public class DetailFragPresenterImplemented implements DetailFragPresenter {

    DetailFragmentView detailFragment;
    DetailFragResource detailFragResource = new DetailFragResImplemented(this);

    public DetailFragPresenterImplemented(DetailFragmentView detailFragment) {
        this.detailFragment = detailFragment;
    }

    @Override
    public void getMovieTrailer(String movieId) {
        detailFragResource.getMovieTrailFromAPI(movieId);
    }

    @Override
    public void onSuccessOfPresenter(List list) {
        detailFragment.onSuccessOfView(list);
    }
}
