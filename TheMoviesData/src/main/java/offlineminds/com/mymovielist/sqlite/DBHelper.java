package offlineminds.com.mymovielist.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import offlineminds.com.mymovielist.config;
import offlineminds.com.mymovielist.pojo.Result;

/**
 * Created by saunakc on 27/06/17.
 */

public class DBHelper extends SQLiteOpenHelper {



    public DBHelper(Context context) {
        super(context, config.DATABASE_NAME, null, config.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + config.TABLE_RESULT);
        db.execSQL(config.CREATE_TABLE_RESULT);

        db.execSQL("DROP TABLE IF EXISTS " + config.TABLE_COMEDY_MOVIES);
        db.execSQL(config.CREATE_TABLE_COMEDY_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + config.TABLE_RESULT);
        db.execSQL("DROP TABLE IF EXISTS " + config.TABLE_COMEDY_MOVIES);
        onCreate(db);
    }


    public long insertIntoResult(Result result,String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(config.KEY_movie_id,result.getId());
        data.put(config.KEY_votecount, result.getVoteCount());
        data.put(config.KEY_video, result.getVideo());
        data.put(config.KEY_vote_average, result.getVoteAverage());
        data.put(config.KEY_title, result.getTitle());
        data.put(config.KEY_popularity, result.getPopularity());
        data.put(config.KEY_poster_path, result.getPosterPath());
        data.put(config.KEY_original_title, result.getOriginalTitle());
        data.put(config.KEY_original_language, result.getOriginalLanguage());
        data.put(config.KEY_genre_ids, String.valueOf(result.getGenreIds()));
        data.put(config.KEY_backdrop_path, result.getBackdropPath());
        data.put(config.KEY_overview, result.getOverview());
        data.put(config.KEY_adult, result.getAdult());
        data.put(config.KEY_release_date, result.getReleaseDate());
        data.put(config.KEY_CREATED_AT, getDateTime());

        long id = db.insert(tableName, null, data);
        return id;
    }


    public List<Result> getMoviesFromDB(String tableName) {
        String selectQueryOnResultTable = "Select * from " + tableName;
        SQLiteDatabase readDb = getReadableDatabase();
        List<Result> resultList = new ArrayList<>();
        Cursor c = readDb.rawQuery(selectQueryOnResultTable, null);


        // If i do not perform this check it will give outof bounds error

        if (c != null && c.moveToFirst()) {
            do {
                int code = c.getInt(c.getColumnIndex(config.KEY_ID));
                int movie_id = c.getInt(c.getColumnIndex(config.KEY_movie_id));
                String voteCount = c.getString(c.getColumnIndex(config.KEY_votecount));
                String video = c.getString(c.getColumnIndex(config.KEY_video));
                String voteAverage = c.getString(c.getColumnIndex(config.KEY_vote_average));
                String title = c.getString(c.getColumnIndex(config.KEY_title));
                String popularity = c.getString(c.getColumnIndex(config.KEY_popularity));
                String poster_path = c.getString(c.getColumnIndex(config.KEY_poster_path));
                String original_language = c.getString(c.getColumnIndex(config.KEY_original_language));
                String genre_ids = c.getString(c.getColumnIndex(config.KEY_genre_ids));
                String backdrop_path = c.getString(c.getColumnIndex(config.KEY_backdrop_path));
                String adult = c.getString(c.getColumnIndex(config.KEY_adult));
                String release_date = c.getString(c.getColumnIndex(config.KEY_release_date));
                String overview = c.getString(c.getColumnIndex(config.KEY_overview));
                String original_title = c.getString(c.getColumnIndex(config.KEY_original_title));


                String s = genre_ids.substring(1, genre_ids.length() - 1);
                String[] s1 = s.split(",");

//                System.out.println(Arrays.toString(s1));

                List<Integer> genereList = new ArrayList<>();
                for (String genereId : s1) {
                    genereList.add(Integer.parseInt(genereId.trim()));
                }


                Result myResult = new Result();
                myResult.setId(movie_id);
                myResult.setVoteCount(Integer.parseInt(voteCount));
                myResult.setVideo(Boolean.parseBoolean(video));
                myResult.setVoteAverage(Double.parseDouble(voteAverage));
                myResult.setTitle(title);
                myResult.setAdult(Boolean.parseBoolean(adult));
                myResult.setBackdropPath(backdrop_path);
                myResult.setGenreIds(genereList);
                myResult.setOriginalLanguage(original_language);
                myResult.setOriginalTitle(original_title);
                myResult.setOverview(overview);
                myResult.setPopularity(Double.parseDouble(popularity));
                myResult.setPosterPath(poster_path);
                myResult.setReleaseDate(release_date);
                resultList.add(myResult);
            } while (c.moveToNext());

        }
        return resultList;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
