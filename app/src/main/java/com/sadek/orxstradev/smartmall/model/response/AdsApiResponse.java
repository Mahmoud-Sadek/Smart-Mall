package com.sadek.orxstradev.smartmall.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class AdsApiResponse {


    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("data")
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public class DataBean {
        @Expose
        @SerializedName("updated_at")
        private UpdatedAtEntity updatedAt;
        @Expose
        @SerializedName("created_at")
        private CreatedAtEntity createdAt;
        @Expose
        @SerializedName("desc4")
        private String desc4;
        @Expose
        @SerializedName("desc3")
        private String desc3;
        @Expose
        @SerializedName("desc2")
        private String desc2;
        @Expose
        @SerializedName("desc1")
        private String desc1;
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
        @SerializedName("sub4_name")
        private String sub4Name;
        @Expose
        @SerializedName("sub4_id")
        private String sub4Id;
        @Expose
        @SerializedName("sub3_name")
        private String sub3Name;
        @Expose
        @SerializedName("sub3_id")
        private String sub3Id;
        @Expose
        @SerializedName("sub2_name")
        private String sub2Name;
        @Expose
        @SerializedName("sub2_id")
        private String sub2Id;
        @Expose
        @SerializedName("sub1_name")
        private String sub1Name;
        @Expose
        @SerializedName("sub1_id")
        private String sub1Id;
        @Expose
        @SerializedName("sub_category_name")
        private String subCategoryName;
        @Expose
        @SerializedName("sub_category_id")
        private String subCategoryId;
        @Expose
        @SerializedName("maincategory_name")
        private String maincategoryName;
        @Expose
        @SerializedName("maincategory_id")
        private String maincategoryId;
        @Expose
        @SerializedName("main_icon")
        private String mainIcon;
        @Expose
        @SerializedName("mobile")
        private String mobile;
        @Expose
        @SerializedName("id")
        private int id;

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

        public String getDesc4() {
            return desc4;
        }

        public void setDesc4(String desc4) {
            this.desc4 = desc4;
        }

        public String getDesc3() {
            return desc3;
        }

        public void setDesc3(String desc3) {
            this.desc3 = desc3;
        }

        public String getDesc2() {
            return desc2;
        }

        public void setDesc2(String desc2) {
            this.desc2 = desc2;
        }

        public String getDesc1() {
            return desc1;
        }

        public void setDesc1(String desc1) {
            this.desc1 = desc1;
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

        public String getSub4Name() {
            return sub4Name;
        }

        public void setSub4Name(String sub4Name) {
            this.sub4Name = sub4Name;
        }

        public String getSub4Id() {
            return sub4Id;
        }

        public void setSub4Id(String sub4Id) {
            this.sub4Id = sub4Id;
        }

        public String getSub3Name() {
            return sub3Name;
        }

        public void setSub3Name(String sub3Name) {
            this.sub3Name = sub3Name;
        }

        public String getSub3Id() {
            return sub3Id;
        }

        public void setSub3Id(String sub3Id) {
            this.sub3Id = sub3Id;
        }

        public String getSub2Name() {
            return sub2Name;
        }

        public void setSub2Name(String sub2Name) {
            this.sub2Name = sub2Name;
        }

        public String getSub2Id() {
            return sub2Id;
        }

        public void setSub2Id(String sub2Id) {
            this.sub2Id = sub2Id;
        }

        public String getSub1Name() {
            return sub1Name;
        }

        public void setSub1Name(String sub1Name) {
            this.sub1Name = sub1Name;
        }

        public String getSub1Id() {
            return sub1Id;
        }

        public void setSub1Id(String sub1Id) {
            this.sub1Id = sub1Id;
        }

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

        public String getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(String subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public String getMaincategoryName() {
            return maincategoryName;
        }

        public void setMaincategoryName(String maincategoryName) {
            this.maincategoryName = maincategoryName;
        }

        public String getMaincategoryId() {
            return maincategoryId;
        }

        public void setMaincategoryId(String maincategoryId) {
            this.maincategoryId = maincategoryId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public String getMainIcon() {
            return mainIcon;
        }

        public void setMainIcon(String mainIcon) {
            this.mainIcon = mainIcon;
        }
    }

}
