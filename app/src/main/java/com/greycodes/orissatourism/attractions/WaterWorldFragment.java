package com.greycodes.orissatourism.attractions;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.orissatourism.R;


public class WaterWorldFragment extends Fragment {

    public WaterWorldFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_1, container, false);
        getActivity().setTitle("Water World");
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Water World");
    }
}

