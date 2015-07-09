package com.futureinapps.ledawateradmin.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by fappsilya on 06.07.15.
 */

@ParseClassName("Order")
public class Order extends ParseObject implements Parcelable{

    public Order(){

    }

    public ParseObject getProduct(){
        return getParseObject("Product");
    }

    public void setProduct(ParseObject object){
        put("Product", object);
    }

    public ParseUser getCustomer(){
        return getParseUser("Customer");
    }

    public void setCustomer(ParseUser user){
        put("Customer", user);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
