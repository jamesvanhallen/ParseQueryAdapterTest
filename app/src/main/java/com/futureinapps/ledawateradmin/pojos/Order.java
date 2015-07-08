package com.futureinapps.ledawateradmin.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by fappsilya on 06.07.15.
 */
@ParseClassName("Order")
public class Order extends ParseObject implements Parcelable{

    private String params;
    private String count;
    private String image;
    private String name;
    private String customerId;
    private String status;

    public Order(){

    }

    public Order(Parcel in){
        params = in.readString();
        count = in.readString();
        image = in.readString();
        name = in.readString();
        customerId = in.readString();
        status = in.readString();
    }


    public void setParams(String s){
        put("Params", s);
    }

    public String getParams(){
        return getString("Params");
    }

    public void setCount(String s){
        put("Count", s);
    }

    public String getCount(){
        return getString("Count");
    }

    public void setImage(String s){
        put("Image", s);
    }

    public String getImage(){
        return getString("Image");
    }

    public void setName(String s){
        put("Name", s);
    }

    public String getName(){
        return getString("Name");
    }

    public void setCustomerId(String s){
        put("CustomerId", s);
    }

    public String getCustomerId(){
        return getString("CustomerId");
    }

    public void setStatus(String s){
        put("Status", s);
    }

    public String getStatus(){
        return getString("Status");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(params);
        dest.writeString(count);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(customerId);
        dest.writeString(status);
    }
}
