package offlineminds.com.mymovielist;

/**
 * Created by saunakc on 16/06/17.
 */

public class config {
    public static String  baseUrl="http://api.themoviedb.org/3/movie/popular?api_key=503f36886ff6cc467d3a00842eb9c4bc";
    public static String imgBaseUrl="http://image.tmdb.org/t/p/w185";
    //    3/movie/popular?api_key=503f36886ff6cc467d3a00842eb9c4bc"
    public static final String DEVELOPER_KEY = "AIzaSyCQJMzjTSIV8ZR7gXPM8B7YiED7ywMnu5Q";

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "clientTmdb";

    // Table Names
    public static final String TABLE_RESULT = "result";

    // Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_CREATED_AT = "created_at";


    // Result Table Column Name
    public static final String KEY_movie_id = "movie_id";
    public static final String KEY_votecount = "votecount";
    public static final String KEY_video = "video";
    public static final String KEY_vote_average = "average";
    public static final String KEY_title = "title";
    public static final String KEY_popularity = "popularity";
    public static final String KEY_poster_path = "poster_path";
    public static final String KEY_original_title = "original_title";
    public static final String KEY_original_language = "original_language";
    public static final String KEY_genre_ids = "genre_ids";
    public static final String KEY_backdrop_path = "backdrop_path";
    public static final String KEY_adult = "adult";
    public static final String KEY_overview = "overview";
    public static final String KEY_release_date = "release_date";


    // Table Create Statements
    // Todo table create statement

    public static final String CREATE_TABLE_RESULT = "CREATE TABLE "
            + TABLE_RESULT + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_movie_id + " INTEGER," +
            KEY_votecount + " INTEGER," +
            KEY_video + " INTEGER," +
            KEY_vote_average + " REAL," +
            KEY_title + " TEXT," +
            KEY_popularity + " REAL," +
            KEY_poster_path + " TEXT," +
            KEY_original_title + " TEXT," +
            KEY_original_language + " TEXT," +
            KEY_genre_ids + " TEXT," +
            KEY_backdrop_path + " TEXT," +
            KEY_adult + " TEXT," +
            KEY_release_date + " TEXT," +
            KEY_overview + " TEXT," +
            KEY_CREATED_AT + " DATETIME" + ")";


}
