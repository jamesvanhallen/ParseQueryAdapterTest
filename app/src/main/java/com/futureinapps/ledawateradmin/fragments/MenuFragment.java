package com.futureinapps.ledawateradmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.activities.MainActivity;
import com.futureinapps.ledawateradmin.adapters.MenuAdapter;
import com.futureinapps.ledawateradmin.pojos.MenuItem;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fappsilya on 07.07.15.
 */
public class MenuFragment extends BaseFragment {


    @Bind(R.id.items_lv)
    ListView lv;

    private MenuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, v);
        adapter = new MenuAdapter();
        MenuItem item = new MenuItem("Новости", getResources().getDrawable(R.drawable.news_64));
        MenuItem item2 = new MenuItem("Заказы", getResources().getDrawable(R.drawable.product_64));
        MenuItem item3 = new MenuItem("Рассылка", getResources().getDrawable(R.drawable.sending_icon));
        List<MenuItem>list = Arrays.asList(item, item2, item3);
        adapter.setItems(list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0: {
                        MainActivity.changeFragment(new NewsFragment(), false, (AppCompatActivity) getActivity());
                        break;
                    }
                    case 1: {
                        MainActivity.changeFragment(new OrdersFragment(), false, (AppCompatActivity) getActivity());
                        break;
                    }
                    case 2: {
                        //ft.replace(R.id.fragment_container2, new GagerFragment());
                        break;
                    }
                }
            }
        });

        return v;
    }
}
