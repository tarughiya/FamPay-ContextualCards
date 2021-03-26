package com.example.fampay.bean;

import com.google.gson.annotations.SerializedName;

public class IconImage {
    @SerializedName("image_type")
    private String image_type;
    @SerializedName("aspect_ratio")
    private Float aspect_ratio;
    @SerializedName("image_url")
    private String image_url;

    public IconImage(String image_type, Float aspect_ratio, String image_url) {
        this.image_type = image_type;
        this.aspect_ratio = aspect_ratio;
        this.image_url = image_url;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public Float getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(Float aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
