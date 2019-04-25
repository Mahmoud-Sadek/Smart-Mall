package com.sadek.orxstradev.smartmall.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductApiResponse {


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
        @SerializedName("time")
        private String time;
        @Expose
        @SerializedName("brand_id")
        private String brandId;
        @Expose
        @SerializedName("category_id")
        private String categoryId;
        @Expose
        @SerializedName("offer_id")
        private String offerId;
        @Expose
        @SerializedName("publisher")
        private String publisher;
        @Expose
        @SerializedName("links")
        private String links;
        @Expose
        @SerializedName("discount")
        private String discount;
        @Expose
        @SerializedName("freeshing")
        private String freeshing;
        @Expose
        @SerializedName("detail")
        private String detail;
        @Expose
        @SerializedName("sku")
        private String sku;
        @Expose
        @SerializedName("likes")
        private String likes;
        @Expose
        @SerializedName("infromation")
        private String infromation;
        @Expose
        @SerializedName("selling_quantity")
        private String sellingQuantity;
        @Expose
        @SerializedName("quantity")
        private String quantity;
        @Expose
        @SerializedName("image5")
        private String image5;
        @Expose
        @SerializedName("image4")
        private String image4;
        @Expose
        @SerializedName("image3")
        private String image3;
        @Expose
        @SerializedName("image2")
        private String image2;
        @Expose
        @SerializedName("image1")
        private String image1;
        @Expose
        @SerializedName("offer_price")
        private String offerPrice;
        @Expose
        @SerializedName("price")
        private String price;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getLinks() {
            return links;
        }

        public void setLinks(String links) {
            this.links = links;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getFreeshing() {
            return freeshing;
        }

        public void setFreeshing(String freeshing) {
            this.freeshing = freeshing;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getInfromation() {
            return infromation;
        }

        public void setInfromation(String infromation) {
            this.infromation = infromation;
        }

        public String getSellingQuantity() {
            return sellingQuantity;
        }

        public void setSellingQuantity(String sellingQuantity) {
            this.sellingQuantity = sellingQuantity;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getImage5() {
            return image5;
        }

        public void setImage5(String image5) {
            this.image5 = image5;
        }

        public String getImage4() {
            return image4;
        }

        public void setImage4(String image4) {
            this.image4 = image4;
        }

        public String getImage3() {
            return image3;
        }

        public void setImage3(String image3) {
            this.image3 = image3;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
