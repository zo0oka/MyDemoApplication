package com.zo0okadev.mydemoapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("TitleEN")
    @Expose
    private String titleEN;
    @SerializedName("TitleAR")
    @Expose
    private String titleAR;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("ProductCount")
    @Expose
    private String productCount;
    @SerializedName("HaveModel")
    @Expose
    private String haveModel;
    @SerializedName("SubCategories")
    @Expose
    private List<Category> subCategories = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public String getTitleAR() {
        return titleAR;
    }

    public void setTitleAR(String titleAR) {
        this.titleAR = titleAR;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getHaveModel() {
        return haveModel;
    }

    public void setHaveModel(String haveModel) {
        this.haveModel = haveModel;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
