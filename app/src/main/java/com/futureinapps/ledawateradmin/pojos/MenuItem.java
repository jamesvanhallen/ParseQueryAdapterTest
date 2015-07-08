package com.futureinapps.ledawateradmin.pojos;

import android.graphics.drawable.Drawable;

/**
 * Created by fappsilya on 07.07.15.
 */
public class MenuItem {

    private String name;
    private Drawable image;

    public MenuItem(String name, Drawable drawable){
        this.name = name;
        this.image = drawable;
    }

    public Drawable getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
