package com.futureinapps.ledawateradmin.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
        Fragment oldFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container2);
        if (oldFragment != null) {
            ft.remove(oldFragment);
        }
        Fragment oldFragment2 = getSupportFragmentManager().findFragmentById(R.id.fragment_container1);
        if (oldFragment2 != null) {
            ft.remove(oldFragment2);
        }
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

    public static void changeFragment(Fragment f, boolean addToBackStack, AppCompatActivity activity) {
        FragmentManager mFm = activity.getSupportFragmentManager();
        FragmentTransaction ft = mFm.beginTransaction();

        // Backstack
        if (addToBackStack) {
            ft.addToBackStack(null);
        }

        // Adding fragment
        Fragment oldFragment = mFm.findFragmentById(R.id.fragment_container2);
        if (oldFragment != null) {
            ft.remove(oldFragment);
        }
        ft.add(R.id.fragment_container2, f);

        // Commit transaction
        ft.commit();
    }



}
