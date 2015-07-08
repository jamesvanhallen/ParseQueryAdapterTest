package com.futureinapps.ledawateradmin.pojos;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by James on 04.07.2015.
 */


public class Door implements Parcelable{
    private String name;
    private Drawable icon;

    public Door(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public Door(Parcel in){
        name = in.readString();
        Bitmap bitmap = (Bitmap)in.readParcelable(getClass().getClassLoader());
        // Convert Bitmap to Drawable:
        icon = new BitmapDrawable(bitmap);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        Bitmap bitmap = (Bitmap)((BitmapDrawable) icon).getBitmap();
        // Serialize bitmap as Parcelable:
        dest.writeParcelable(bitmap, flags);
    }
}
