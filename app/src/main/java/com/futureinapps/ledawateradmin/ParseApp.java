package com.futureinapps.ledawateradmin;

import android.app.Application;
import android.util.Log;

import com.futureinapps.ledawateradmin.pojos.Gager;
import com.futureinapps.ledawateradmin.pojos.News;
import com.futureinapps.ledawateradmin.pojos.Order;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by James on 04.07.2015.
 */
public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Order.class);
        ParseObject.registerSubclass(News.class);
        ParseObject.registerSubclass(Gager.class);

        Parse.initialize(this, getString(R.string.MyParseId), getString(R.string.MyParsePass));

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/roboto.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("deviceId", "ADMINISTRATOR");
        installation.saveInBackground();


        ParsePush.subscribeInBackground("global", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}
