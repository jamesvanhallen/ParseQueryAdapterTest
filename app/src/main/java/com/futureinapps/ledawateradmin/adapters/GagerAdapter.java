package com.futureinapps.ledawateradmin.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.pojos.Gager;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by fappsilya on 08.07.15.
 */
public class GagerAdapter extends ParseQueryAdapter<Gager> {

    public Context context;

    public GagerAdapter(final Context context) {
        super(context, new QueryFactory<Gager>() {
            @Override
            public ParseQuery<Gager> create() {

                ParseQuery<Gager> query =new ParseQuery<>("Gager");
                query.orderByDescending("createdAt");
                return query;
            }
        });
        this.context = context;
    }

    @Override
    public View getItemView(Gager gager, View v, ViewGroup parent) {
        if(v == null){
            v = View.inflate(getContext(), R.layout.gager_item, null);
        }

        super.getItemView(gager, v, parent);

        TextView date = (TextView) v.findViewById(R.id.gager_date);
        SimpleDateFormat dateParser = new SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.US);
        date.setText(dateParser.format(gager.getCreatedAt()));


        TextView title = (TextView) v.findViewById(R.id.gager_name);
        title.setText("Заказчик: " + gager.getCustomerName());


        TextView count = (TextView) v.findViewById(R.id.gager_phone);
        count.setText("Телефон: " + gager.getCustomerPhone());

        return v;
    }


}
