package com.sadek.orxstradev.smartmall.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class AddressModel {


    @Expose
    @SerializedName("data")
    private DataEntity data;
    @Expose
    @SerializedName("success")
    private boolean success;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataEntity {
        @Expose
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("created_at")
        private String createdAt;
        @Expose
        @SerializedName("active")
        private String active;
        @Expose
        @SerializedName("postal_code")
        private String postalCode;
        @Expose
        @SerializedName("shipping_note")
        private String shippingNote;
        @Expose
        @SerializedName("whatsapp")
        private String whatsapp;
        @Expose
        @SerializedName("alt_phone")
        private String altPhone;
        @Expose
        @SerializedName("phone")
        private String phone;
        @Expose
        @SerializedName("location_type")
        private String locationType;
        @Expose
        @SerializedName("landmark")
        private String landmark;
        @Expose
        @SerializedName("address")
        private String address;
        @Expose
        @SerializedName("street")
        private String street;
        @Expose
        @SerializedName("district")
        private String district;
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
        @SerializedName("last_name")
        private String lastName;
        @Expose
        @SerializedName("first_name")
        private String firstName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getShippingNote() {
            return shippingNote;
        }

        public void setShippingNote(String shippingNote) {
            this.shippingNote = shippingNote;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getAltPhone() {
            return altPhone;
        }

        public void setAltPhone(String altPhone) {
            this.altPhone = altPhone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLocationType() {
            return locationType;
        }

        public void setLocationType(String locationType) {
            this.locationType = locationType;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
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

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }

}
