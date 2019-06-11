package com.sadek.orxstradev.smartmall.model.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialLoginBody {

    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("api_token")
    private String apiToken;
    @Expose
    @SerializedName("id")
    private int id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
