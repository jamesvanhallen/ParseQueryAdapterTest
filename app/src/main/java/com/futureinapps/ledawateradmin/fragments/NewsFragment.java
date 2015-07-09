package com.futureinapps.ledawateradmin.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.activities.MainActivity;
import com.futureinapps.ledawateradmin.adapters.NewsAdapter;
import com.melnykov.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 04.07.2015.
 */
public class NewsFragment extends BaseFragment {

    private NewsAdapter adapter;

    @Bind(R.id.news_lv)
    ListView lv;
    @Bind(R.id.fab)
    FloatingActionButton mFab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, v);

        adapter = new NewsAdapter(getActivity());

        lv.setAdapter(adapter);
        lv.setClickable(false);
        mFab.attachToListView(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChangeNewsDialog frag = new ChangeNewsDialog();
                Bundle b = new Bundle();
                b.putParcelable("news", adapter.getItem(position));
                frag.setArguments(b);
                frag.setTargetFragment(NewsFragment.this, 1);
                frag.show(getActivity().getSupportFragmentManager(), "dssdfds");
            }
        });

        return  v;
    }

    @OnClick(R.id.fab)
    void onFabButtonClick(){
        NewsCreatingFragment frag = new NewsCreatingFragment();
        MainActivity.changeFragment(frag, false, (AppCompatActivity) getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK){
            MainActivity.changeFragment(new NewsFragment(), false, (AppCompatActivity) getActivity());
        }
    }
}
