package offlineminds.com.mymovielist.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import offlineminds.com.mymovielist.appalarmmgr.MovieAlarmReceiver;
import offlineminds.com.mymovielist.config;

public class MovieContentProvider extends ContentProvider {


    private DBHelper dbHelper;
    static String TAG = MovieContentProvider.class.getName();
    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {

        sUriMatcher.addURI(config.AUTHORITY, config.TABLE_RESULT, 0);
        sUriMatcher.addURI(config.AUTHORITY,config.TABLE_COMEDY_MOVIES,1);
        sUriMatcher.addURI(config.AUTHORITY,config.TABLE_ACTION_MOVIES,2);
        sUriMatcher.addURI(config.AUTHORITY,config.TABLE_DOCUMANTARY,3);
    }

    public MovieContentProvider() {
    }




    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase readableDB = dbHelper.getReadableDatabase();

        switch (sUriMatcher.match(uri)){
            case 0:
                Log.d(TAG,"URI MATCHED TO Popular");
                return readableDB.query(config.TABLE_RESULT,projection,selection,selectionArgs,null,null,null);

            case 1:
                Log.d(TAG,"URI MATCHED TO COMEDY");
                return readableDB.query(config.TABLE_COMEDY_MOVIES,projection,selection,selectionArgs,null,null,null);

            case 2:
                Log.d(TAG,"URI MATCHED TO ACTION");
                return readableDB.query(config.TABLE_ACTION_MOVIES,projection,selection,selectionArgs,null,null,null);

            case 3:
                Log.d(TAG,"URI MATCHED TO DOCUMENTARY");
                return readableDB.query(config.TABLE_ACTION_MOVIES,projection,selection,selectionArgs,null,null,null);

            default:
                Log.d(TAG,"URI MATCHED TO DEFAULT");
                return readableDB.query(config.TABLE_RESULT,projection,selection,selectionArgs,null,null,null);
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
