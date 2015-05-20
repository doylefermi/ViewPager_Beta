package com.greycodes.orissatourism.festivals;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.orissatourism.R;

public class FestivalsFragment extends Fragment {

    public FestivalsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_3, container, false);
        getActivity().setTitle("Festivals");
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Festivals");
    }
}
