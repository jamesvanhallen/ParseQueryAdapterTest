package com.futureinapps.ledawateradmin.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.pojos.MenuItem;
import com.futureinapps.ledawateradmin.pojos.News;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by James on 05.07.2015.
 */
public class NewsAdapter extends ParseQueryAdapter<News> {

    public NewsAdapter(Context context) {
        super(context, new QueryFactory<News>() {
            @Override
            public ParseQuery<News> create() {
                ParseQuery<News> query =new ParseQuery<>("News");
                query.orderByDescending("createdAt");
                return query;
            }
        });
    }

    @Override
    public View getItemView(News news, View v, ViewGroup parent) {
        if(v == null){
            v = View.inflate(getContext(), R.layout.news_item, null);
        }

        super.getItemView(news, v, parent);

        TextView date = (TextView) v.findViewById(R.id.news_date);
        SimpleDateFormat dateParser = new SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.US);
        date.setText(dateParser.format(news.getCreatedAt()));


        TextView title = (TextView) v.findViewById(R.id.news_item);
        title.setText(news.getTitle());


        TextView message = (TextView) v.findViewById(R.id.news_message);
        message.setText(news.getMessage());

        //if views visibility is GONE
        title.setVisibility(View.VISIBLE);
        message.setVisibility(View.VISIBLE);

        ParseImageView image = (ParseImageView) v.findViewById(R.id.news_image);

        //if item don't have image but ImageView visibility is VISIBLE
        image.setVisibility(View.GONE);

        if(news.getImage()!=null){

            Picasso.with(getContext())
                    .load(news.getImage().getUrl())
                    .into(image);
            //Every news with image don't have a title and message
            title.setVisibility(View.GONE);
            message.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
        }


        return v;
    }

}
