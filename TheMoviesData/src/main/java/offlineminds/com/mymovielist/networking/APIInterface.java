package offlineminds.com.mymovielist.networking;

/**
 * Created by saunakc on 17/06/17.
 */

import offlineminds.com.mymovielist.pojo.ActionMovies;
import offlineminds.com.mymovielist.pojo.CommedyMovies;
import offlineminds.com.mymovielist.pojo.Documentaries;
import offlineminds.com.mymovielist.pojo.PopularMovies;
import offlineminds.com.mymovielist.pojo.Trailer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIInterface {
    @GET("/3/movie/popular?api_key=503f36886ff6cc467d3a00842eb9c4bc")
    Call<PopularMovies> getPopularMovies();


    @GET("/3/genre/35/movies?api_key=503f36886ff6cc467d3a00842eb9c4bc&language=en-US&include_adult=false&sort_by=created_at.asc")
    Call<CommedyMovies> getCommedyMovies();

    @GET("/3/genre/28/movies?api_key=503f36886ff6cc467d3a00842eb9c4bc&language=en-US&include_adult=false&sort_by=created_at.asc")
    Call<ActionMovies> getActionMovies();

    @GET("/3/genre/99/movies?api_key=503f36886ff6cc467d3a00842eb9c4bc&language=en-US&include_adult=false&sort_by=created_at.asc")
    Call<Documentaries> getDocumentaries();

    @GET("3/movie/{movie_id}/videos?api_key=503f36886ff6cc467d3a00842eb9c4bc")
    Call<Trailer> getTrailers(@Path("movie_id") String movie_id);
}

