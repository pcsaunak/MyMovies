package offlineminds.com.mymovielist.ui.ComedyFragment;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.config;
import offlineminds.com.mymovielist.pojo.Result;
import offlineminds.com.mymovielist.sqlite.DBHelper;
import offlineminds.com.mymovielist.ui.Adapter.AdapterComedyFragment;
import offlineminds.com.mymovielist.ui.DetailFragments.DetailFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComedyFragment extends Fragment implements ComedyFragmentView,View.OnClickListener {


    private GridView mGridView;
    private ProgressBar mProgressBar;
    private AdapterComedyFragment mGridAdapter;
    private ArrayList<Result> mGridData;
    private DBHelper dbHelper;
    private Uri providerUri;

    private static String TAG = ComedyFragment.class.getName();
    ComedyFragmentPresenter comedyFragmentPresenter = new ComedyFragPresenterImplemented(this);



    public ComedyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dbHelper = new DBHelper(getContext());
        return inflater.inflate(R.layout.fragment_home, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView = (GridView) view.findViewById(R.id.gridView);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int dpi = metrics.densityDpi;

        mGridView.setColumnWidth(dpi/2);

//        dbData = (Button) view.findViewById(R.id.getDataFromDb);

        //Initialize with empty data
        mGridData = new ArrayList<>();
        mGridAdapter = new AdapterComedyFragment(getContext(),
                R.layout.grid_item_layout,
                mGridData,
                getContext(),
                R.layout.grid_item_layout,
                mGridData);
        mGridView.setAdapter(mGridAdapter);

        comedyFragmentPresenter.getDataFromPresenter();
        mProgressBar.setVisibility(View.VISIBLE);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Grid Item Clicked", Toast.LENGTH_SHORT).show();
                Fragment detailFrag = new DetailFragment(mGridData);
                Bundle bundle = new Bundle();
                bundle.putInt("ClickPosition",position);
                detailFrag.setArguments(bundle);
                FragmentManager detailFragMgr= getActivity().getSupportFragmentManager();
                FragmentTransaction detailFragTransct = detailFragMgr.beginTransaction();
                detailFragTransct.replace(R.id.contentFrame,detailFrag).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public void updateDataFromAPI(List<Result> results) {

        for(Result myResult : results){
            // CREATE DB FOR STORING THE VALUES FOR COMEDY MOVIES
            dbHelper.insertIntoResult(myResult, config.TABLE_COMEDY_MOVIES);
        }

       /*
        String[] projection=null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        ContentResolver movieResolver = getContext().getContentResolver();
        providerUri=Uri.parse("content://offlineminds.com.own.PROVIDER/TABLE_COMEDY_MOVIES");

        Cursor movieCursor = movieResolver.query(providerUri,projection,selection,selectionArgs,sortOrder);
        mGridData=getDataFromCursor(movieCursor);
        */

        mGridData=(ArrayList<Result>)dbHelper.getMoviesFromDB(config.TABLE_COMEDY_MOVIES);
        Log.d(TAG,"SIZE OF LIST:::::: " + mGridData.size());
        mGridAdapter.setGridData(mGridData);
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void displayMessage(String info) {
        Toast.makeText(getContext(), "Message Passed: "+info, Toast.LENGTH_LONG).show();
    }


    public ArrayList<Result> getDataFromCursor(Cursor movieCursor) {
        ArrayList<Result> resultList = new ArrayList<>();
        if (movieCursor != null && movieCursor.moveToFirst()) {
            do {
                int code = movieCursor.getInt(movieCursor.getColumnIndex(config.KEY_ID));
                int movie_id = movieCursor.getInt(movieCursor.getColumnIndex(config.KEY_movie_id));
                String voteCount = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_votecount));
                String video = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_video));
                String voteAverage = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_vote_average));
                String title = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_title));
                String popularity = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_popularity));
                String poster_path = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_poster_path));
                String original_language = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_original_language));
                String genre_ids = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_genre_ids));
                String backdrop_path = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_backdrop_path));
                String adult = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_adult));
                String release_date = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_release_date));
                String overview = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_overview));
                String original_title = movieCursor.getString(movieCursor.getColumnIndex(config.KEY_original_title));
                String s = genre_ids.substring(1, genre_ids.length() - 1);

                String[] s1 = s.split(",");
                System.out.println(Arrays.toString(s1));
                List<Integer> genereList = new ArrayList<>();


                for (String genereId : s1) {
                    Log.d("VALUE OF s1 ",genereId);
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
            } while (movieCursor.moveToNext());
        }
        return resultList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.layout.grid_item_layout:
                Toast.makeText(getContext(), "Grid Item Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getContext(), "Nothing found", Toast.LENGTH_SHORT).show();

        }
    }
}
