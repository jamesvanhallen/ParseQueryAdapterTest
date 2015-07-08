package com.futureinapps.ledawateradmin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.activities.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fappsilya on 24.04.15.
 */
public class NoInternetFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.no_internet_fragment, container, false);
        ButterKnife.bind(this, v);
//        getActivity().getActionBar().hide();
        return  v;
    }

    @OnClick(R.id.network_btn)
    void onNetworkBtnClick(){
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
