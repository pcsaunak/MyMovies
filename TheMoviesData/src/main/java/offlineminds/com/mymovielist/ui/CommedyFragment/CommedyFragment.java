package offlineminds.com.mymovielist.ui.CommedyFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import offlineminds.com.mymovielist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommedyFragment extends Fragment {


    public CommedyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commedy, container, false);
    }

}
