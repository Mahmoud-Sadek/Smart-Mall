package com.sadek.orxstradev.smartmall.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sadek.orxstradev.smartmall.model.body.OptionModel;

import java.util.ArrayList;
import java.util.List;

public class ProductApiResponse {
    @Expose
    @SerializedName("data")
    private List<DataEntity> data;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("status")
    private String status;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

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

    public class DataEntity {
        @Expose
        @SerializedName("updated_at")
        private UpdatedAtEntity updatedAt;
        @Expose
        @SerializedName("created_at")
        private CreatedAtEntity createdAt;
        @Expose
        @SerializedName("is_like")
        private boolean isLike;
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
        private int discount;
        @Expose
        @SerializedName("freeshing")
        private String freeshing;
        @Expose
        @SerializedName("detail")
        private String detail;
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
        @SerializedName("image1")
        private List<String> image1;
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
        @Expose
        @SerializedName("sku")
        private String sku;
        @Expose
        @SerializedName("colors")
        private String colors;
        @Expose
        @SerializedName("size")
        private String size;
        @Expose
        @SerializedName("volume")
        private String volume;

        public UpdatedAtEntity getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(UpdatedAtEntity updatedAt) {
            this.updatedAt = updatedAt;
        }

        public CreatedAtEntity getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(CreatedAtEntity createdAt) {
            this.createdAt = createdAt;
        }

        public boolean getIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
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

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
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

        public List<String> getImage1() {
            return image1;
        }

        public void setImage1(List<String> image1) {
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

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public List<OptionModel> getColors() {
            if (size != null) {
                String strMain = colors;
                List<OptionModel> colorList = new ArrayList<>();
                String[] arrSplit = strMain.split(",");
                for (int i = 0; i < arrSplit.length; i++) {
                    colorList.add(new OptionModel("#ffe5e7df", arrSplit[i]));
                }
                return colorList;
            }
            return null;
        }

        public void setColors(String colors) {
            this.colors = colors;

        }

        public List<OptionModel> getSize() {
            if (size != null) {
                String strMain = size;
                List<OptionModel> sizeList = new ArrayList<>();
                String[] arrSplit = strMain.split(",");
                for (int i = 0; i < arrSplit.length; i++) {
                    sizeList.add(new OptionModel("#ffe5e7df", arrSplit[i]));
                }
                return sizeList;
            }
            return null;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public List<OptionModel> getVolume() {
            if (volume != null) {
                String strMain = volume;
                List<OptionModel> volumeList = new ArrayList<>();
                String[] arrSplit = strMain.split(",");
                for (int i = 0; i < arrSplit.length; i++) {
                    volumeList.add(new OptionModel("#ffe5e7df", arrSplit[i]));
                }
                return volumeList;
            }
            return null;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }
    }


}
