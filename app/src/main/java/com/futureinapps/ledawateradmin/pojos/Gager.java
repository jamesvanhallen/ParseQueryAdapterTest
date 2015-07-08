package com.futureinapps.ledawateradmin.pojos;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by fappsilya on 08.07.15.
 */
@ParseClassName("Gager")
public class Gager extends ParseObject {

    public Date getDate(){
        return getDate("Date");
    }

    public void setDate(Date date){
        put("Date", date);
    }

    public String getCustomerName(){
        return getString("CustomerName");
    }

    public void setCustomername(String s){
        put("CustomerName", s);
    }

    public String getCustomerPhone(){
        return getString("CustomerPhone");
    }

    public void setCustomerPhone(String s){
        put("CustomerPhone", s);
    }

}
