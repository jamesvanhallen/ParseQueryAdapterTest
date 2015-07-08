package com.futureinapps.ledawateradmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.futureinapps.ledawateradmin.R;
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
        MenuItem item = new MenuItem("Новости", getResources().getDrawable(R.drawable.news512));
        MenuItem item2 = new MenuItem("Заказы", getResources().getDrawable(R.drawable.product512));
        MenuItem item3 = new MenuItem("Замерщик", getResources().getDrawable(R.drawable.pencil_and_ruler_512));
        List<MenuItem>list = Arrays.asList(item, item2, item3);
        adapter.setItems(list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0: {
                        ft.replace(R.id.fragment_container2, new NewsFragment());
                        break;
                    }
                    case 1: {
                        ft.replace(R.id.fragment_container2, new OrdersFragment());
                        break;
                    }
                    case 2: {
                        ft.replace(R.id.fragment_container2, new GagerFragment());
                        break;
                    }
                }
                ft.commit();
            }
        });

        return v;
    }
}
