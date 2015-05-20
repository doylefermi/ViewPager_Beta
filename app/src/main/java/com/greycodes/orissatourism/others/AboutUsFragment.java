package com.greycodes.orissatourism.others;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.orissatourism.R;


public class AboutUsFragment extends Fragment {

    public AboutUsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_4, container, false);
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("About Us");
    }
}
