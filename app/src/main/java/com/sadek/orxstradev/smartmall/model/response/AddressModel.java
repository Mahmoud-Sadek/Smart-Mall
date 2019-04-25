package com.sadek.orxstradev.smartmall.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressModel {


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

    public static class DataEntity {
        @Expose
        @SerializedName("user_id")
        private String userId;
        @Expose
        @SerializedName("whatsapp")
        private String whatsapp;
        @Expose
        @SerializedName("shopping_note")
        private String shoppingNote;
        @Expose
        @SerializedName("mobile_number2")
        private String mobileNumber2;
        @Expose
        @SerializedName("mobile_number1")
        private String mobileNumber1;
        @Expose
        @SerializedName("location_type")
        private String locationType;
        @Expose
        @SerializedName("nearst_landman")
        private String nearstLandman;
        @Expose
        @SerializedName("address_detail")
        private String addressDetail;
        @Expose
        @SerializedName("street")
        private String street;
        @Expose
        @SerializedName("city")
        private String city;
        @Expose
        @SerializedName("state")
        private String state;
        @Expose
        @SerializedName("country")
        private String country;
        @Expose
        @SerializedName("id")
        private int id;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getShoppingNote() {
            return shoppingNote;
        }

        public void setShoppingNote(String shoppingNote) {
            this.shoppingNote = shoppingNote;
        }

        public String getMobileNumber2() {
            return mobileNumber2;
        }

        public void setMobileNumber2(String mobileNumber2) {
            this.mobileNumber2 = mobileNumber2;
        }

        public String getMobileNumber1() {
            return mobileNumber1;
        }

        public void setMobileNumber1(String mobileNumber1) {
            this.mobileNumber1 = mobileNumber1;
        }

        public String getLocationType() {
            return locationType;
        }

        public void setLocationType(String locationType) {
            this.locationType = locationType;
        }

        public String getNearstLandman() {
            return nearstLandman;
        }

        public void setNearstLandman(String nearstLandman) {
            this.nearstLandman = nearstLandman;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
