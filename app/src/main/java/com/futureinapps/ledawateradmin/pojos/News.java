package com.futureinapps.ledawateradmin.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by James on 04.07.2015.
 */

@ParseClassName("News")
public class News extends ParseObject implements Parcelable {

    private String title;
    private String text;

    public News(){

    }

    public News(Parcel in){
        title = in.readString();
        text = in.readString();

    }

    public void setTitle(String s){
        put("Title", s);
    }

    public  void setMessage(String s){
        put("Message", s);
    }

    public String getMessage(){
        return getString("Message");
    }

    public String getTitle(){
        return getString("Title");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(text);
    }
}
