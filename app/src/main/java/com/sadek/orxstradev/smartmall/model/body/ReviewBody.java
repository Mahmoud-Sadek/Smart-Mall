package com.sadek.orxstradev.smartmall.model.body;

public class ReviewBody {

    String user_id, text_en,text_ar,rating, product_id;

    public ReviewBody(String user_id, String text_en, String text_ar, String rating, String product_id) {
        this.user_id = user_id;
        this.text_en = text_en;
        this.text_ar = text_ar;
        this.rating = rating;
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getText_en() {
        return text_en;
    }

    public void setText_en(String text_en) {
        this.text_en = text_en;
    }

    public String getText_ar() {
        return text_ar;
    }

    public void setText_ar(String text_ar) {
        this.text_ar = text_ar;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
