package com.futureinapps.ledawateradmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.adapters.GagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fappsilya on 08.07.15.
 */
public class GagerFragment extends BaseFragment {

    @Bind(R.id.gager_lv)
    ListView lv;
    private GagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gager, container, false);
        ButterKnife.bind(this, v);
        adapter = new GagerAdapter(getActivity());
        lv.setClickable(false);
        lv.setAdapter(adapter);

        return v;
    }
}
