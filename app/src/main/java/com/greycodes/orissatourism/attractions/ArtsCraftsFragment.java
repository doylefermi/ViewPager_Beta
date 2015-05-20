package com.greycodes.orissatourism.attractions;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.orissatourism.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArtsCraftsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArtsCraftsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtsCraftsFragment extends Fragment {
    public ArtsCraftsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Arts & Crafts");
        return inflater.inflate(R.layout.fragment_arts_crafts, container, false);
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Arts & Crafts");
    }
}
