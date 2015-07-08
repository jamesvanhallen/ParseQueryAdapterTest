package com.futureinapps.ledawateradmin.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.fragments.MenuFragment;
import com.futureinapps.ledawateradmin.fragments.NewsFragment;
import com.futureinapps.ledawateradmin.fragments.NoInternetFragment;
import com.futureinapps.ledawateradmin.pojos.Order;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Администратор");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                if(isNetworkConnected()){
                    fragmentsInit(ft);
                } else {
                    ft.add(R.id.fragment_container2, new NoInternetFragment());
                }
                ft.commit();

    }

    private void fragmentsInit(FragmentTransaction ft) {
        ft.add(R.id.fragment_container2, new NewsFragment());
        ft.add(R.id.fragment_container1, new MenuFragment());
    }

    private boolean isNetworkConnected(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void imageSelector(Order order, ImageView orderIcon, Context context) {
        switch (order.getString("Image")){
            case "0":{
                orderIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.door2));
                break;
            }
            case "1":{
                orderIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.door3));
                break;
            }
            case "2":{
                orderIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.door4));
                break;
            }
            case "3":{
                orderIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.door5));
                break;
            }
            case "4":{
                orderIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.door6));
                break;
            }

        }
    }



}
