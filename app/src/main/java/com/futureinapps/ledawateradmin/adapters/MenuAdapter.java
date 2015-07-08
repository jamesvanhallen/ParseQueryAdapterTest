package com.futureinapps.ledawateradmin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.pojos.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fappsilya on 07.07.15.
 */
public class MenuAdapter extends BaseAdapter {

    private List<MenuItem> doors = new ArrayList<>();


    @Override
    public int getCount() {
        return doors.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return doors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_item, parent, false);

            h = new ViewHolder(convertView);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }

        h.bind(getItem(position));

        return convertView;
    }

    public void setItems(List<MenuItem> myItems) {
        doors = myItems;
        notifyDataSetChanged();
    }

    public static class ViewHolder {

        @Bind(R.id.item_menu_iv)
        ImageView image;
        @Bind(R.id.item_menu_tv)
        TextView text;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }

        public void bind(MenuItem item) {
            text.setText(item.getName());
            image.setImageDrawable(item.getImage());
        }
    }
}