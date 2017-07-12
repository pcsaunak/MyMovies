package offlineminds.com.mymovielist.ui.DetailFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

//import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.config;
import offlineminds.com.mymovielist.pojo.AdditionalDetails;
import offlineminds.com.mymovielist.pojo.Result;
import offlineminds.com.mymovielist.pojo.TrailerDetails;

/**
 * A simple {@link Fragment} subclass.
 */

public class DetailFragment extends Fragment implements DetailFragmentView,YouTubePlayer.OnInitializedListener {

    Bundle bundle;
    int positionOfGridElement;
    ImageView moviePosterView;
    ArrayList<Result> resultList;

    TextView tv_originalTitle;
    TextView tv_releaseDate;
    TextView tv_voteAverage;
    TextView tv_overview;
    TextView tv_duration;
    FrameLayout youTubeContent;
    int runtime;
    int screenWidth;
    int screenHeight;
    LinearLayout mainLayout;
//    YouTubePlayerView youTubePlayerView;

    YouTubePlayerSupportFragment youTubePlayerFragment;
    YouTubePlayer youTubePlayer;


    List<TrailerDetails> trailerList;
    DetailFragPresenter detailFragPresenter = new DetailFragPresenterImplemented(this);

    public DetailFragment() {
    }

    public DetailFragment(List<Result> arg) {
        // Required empty public constructor
        this.resultList = (ArrayList<Result>) arg;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = this.getArguments();
        positionOfGridElement = bundle.getInt("ClickPosition");
        Display d = ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screenWidth = d.getWidth();
        screenHeight = d.getHeight();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviePosterView = (ImageView)view.findViewById(R.id.moviePoster);

        Result item = resultList.get(positionOfGridElement);
        detailFragPresenter.getMovieTrailer(String.valueOf(item.getId()));
        detailFragPresenter.getAdditionalDetails(String.valueOf(item.getId()));


        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        mainLayout = (LinearLayout)view.findViewById(R.id.mainLayout);

        youTubeContent = (FrameLayout)view.findViewById(R.id.youtubeContent);
        tv_originalTitle = (TextView)view.findViewById(R.id.tv_originalTitle);
        tv_releaseDate = (TextView)view.findViewById(R.id.tv_releaseDate);
        tv_voteAverage = (TextView)view.findViewById(R.id.tv_voteAverage);
        tv_overview = (TextView)view.findViewById(R.id.tv_overview);
        tv_duration = (TextView) view.findViewById(R.id.tv_duration);


        /*
        * Creating a replace
        * */

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.youtubeContent,youTubePlayerFragment).commit();


        Picasso.with(getContext()).load(config.imgBaseUrl+item.getPosterPath())
                .fit().centerInside().into(moviePosterView);

        tv_originalTitle.setText(item.getOriginalTitle());
        tv_voteAverage.setText(String.valueOf(item.getVoteAverage()+"/10"));
        tv_releaseDate.setText(String.valueOf(item.getReleaseDate().substring(0,4)));
        tv_overview.setText(item.getOverview());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onSuccessOfView(List list) {
        trailerList = list;
        youTubePlayerFragment.initialize(config.DEVELOPER_KEY,this);

    }

    @Override
    public void AdditionalDetailsSuccess(AdditionalDetails details) {
        runtime=details.getRuntime();
        tv_duration.setText(String.valueOf(runtime) + " mins");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        this.youTubePlayer = youTubePlayer;
        if(!wasRestored){
            String key="";
            for(TrailerDetails details:trailerList){
                key = details.getKey();
            }
            youTubePlayer.cueVideo(key);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getContext(), "Youtube Player Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(youTubePlayer!= null){
            youTubePlayer.release();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(youTubePlayer!= null){
            youTubePlayer.release();
        }
    }
}
