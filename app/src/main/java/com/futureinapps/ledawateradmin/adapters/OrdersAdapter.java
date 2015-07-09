package com.futureinapps.ledawateradmin.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.activities.MainActivity;
import com.futureinapps.ledawateradmin.pojos.Order;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by fappsilya on 06.07.15.
 */
public class OrdersAdapter extends ParseQueryAdapter<Order> {

    public Context context;

    public OrdersAdapter(final Context context) {
        super(context, new QueryFactory<Order>() {
            @Override
            public ParseQuery<Order> create() {

                ParseQuery<Order> query =new ParseQuery<>("Order");
                query.orderByDescending("createdAt");
                return query;
            }
        });
        this.context = context;
    }

    @Override
    public View getItemView(Order order, View v, ViewGroup parent) {
        if(v == null){
            v = View.inflate(getContext(), R.layout.item_order, null);
        }

        super.getItemView(order, v, parent);

        TextView date = (TextView) v.findViewById(R.id.order_date);
        SimpleDateFormat dateParser = new SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.US);
        date.setText(dateParser.format(order.getCreatedAt()));


        TextView title = (TextView) v.findViewById(R.id.order_name);
        title.setText(order.getString("Name"));


        TextView count = (TextView) v.findViewById(R.id.order_count);
        count.setText("Количество: " + order.getString("Count"));

        TextView status = (TextView) v.findViewById(R.id.order_status);
        status.setText(order.getString("Status").toUpperCase());

        status.setTextColor(Color.BLACK);
        if(order.getString("Status").equals("Подтвержден")){
            status.setTextColor(context.getResources().getColor(R.color.blue));
        }

        ImageView orderIcon = (ImageView) v.findViewById(R.id.order_image);
       // MainActivity.imageSelector(order, orderIcon, context);


        return v;
    }


}
