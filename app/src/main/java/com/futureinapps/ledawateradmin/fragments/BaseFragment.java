package com.futureinapps.ledawateradmin.fragments;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.futureinapps.ledawateradmin.activities.MainActivity;


public abstract class BaseFragment extends Fragment {

    protected MainActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

}