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
 * {@link TemplesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TemplesFragment extends Fragment {


    public TemplesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Temples");
        return inflater.inflate(R.layout.fragment_temples, container, false);
    }
    @Override
    public void onResume(){
    super.onResume();
    getActivity().setTitle("Temples");
 }
}

