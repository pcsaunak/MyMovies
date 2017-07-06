package offlineminds.com.mymovielist.ui.DetailFragments;

import java.util.List;

/**
 * Created by saunakc on 23/06/17.
 */

public interface DetailFragResource {
    public void getMovieTrailFromAPI(String movieId);
    public void onSuccess(List list);
}
