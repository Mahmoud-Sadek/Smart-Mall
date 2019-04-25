package com.sadek.orxstradev.smartmall.model.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCartBody {

    String user_id, product_id, quantity;


    public AddCartBody(String user_id, String product_id, String quantity) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
