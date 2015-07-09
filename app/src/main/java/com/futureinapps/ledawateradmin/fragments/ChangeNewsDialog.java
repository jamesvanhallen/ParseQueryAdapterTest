package com.futureinapps.ledawateradmin.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.activities.MainActivity;
import com.futureinapps.ledawateradmin.pojos.News;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fappsilya on 07.07.15.
 */
public class ChangeNewsDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_news_change, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        ButterKnife.bind(this ,v);


        return v;
    }

    @OnClick(R.id.delete_news_btn)
    void onDeleteBtnClick(){
        ((News)getArguments().getParcelable("news")).deleteInBackground();
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,
                getActivity().getIntent());
        dismiss();
    }

    @OnClick(R.id.change_news_btn)
    void onChangeBtnClick(){
        NewsCreatingFragment frag = new NewsCreatingFragment();
        Bundle b = new Bundle();
        b.putParcelable("news", getArguments().getParcelable("news"));
        frag.setArguments(b);
        MainActivity.changeFragment(frag, true, (AppCompatActivity)getActivity());
        dismiss();
    }
}
