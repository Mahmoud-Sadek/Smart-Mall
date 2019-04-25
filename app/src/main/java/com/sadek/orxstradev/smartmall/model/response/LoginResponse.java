package com.sadek.orxstradev.smartmall.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("data")
    private List<DataEntity> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }
    public class DataEntity {
        @Expose
        @SerializedName("token")
        private String token;
        @Expose
        @SerializedName("id")
        private int id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
