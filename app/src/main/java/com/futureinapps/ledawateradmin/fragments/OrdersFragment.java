package com.futureinapps.ledawateradmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.adapters.OrdersAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fappsilya on 06.07.15.
 */
public class OrdersFragment extends BaseFragment {

    @Bind(R.id.user_oreders_listview)
    ListView lv;

    private OrdersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_orders_fragment, container, false);
        ButterKnife.bind(this, v);
        adapter = new OrdersAdapter(getActivity());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailsOrderFragment frag = new DetailsOrderFragment();
                Bundle b = new Bundle();
                b.putParcelable("order", adapter.getItem(position));
                frag.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container2, frag)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}
