package com.greycodes.orissatourism.specialinterest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.orissatourism.R;


public class SpecialInterestFragment extends Fragment {

    public SpecialInterestFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_2, container, false);
        getActivity().setTitle("Special Interest");
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Special Interest");
    }
}
