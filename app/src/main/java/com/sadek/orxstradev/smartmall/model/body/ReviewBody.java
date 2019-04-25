package com.sadek.orxstradev.smartmall.model.body;

public class ReviewBody {

    String user_id, text_en, product_id;
    long time;

    public ReviewBody(String user_id, String text_en, String product_id) {
        this.user_id = user_id;
        this.text_en = text_en;
        this.product_id = product_id;
        this.time = System.currentTimeMillis();
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
