package com.example.fampay.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gradient {
    @SerializedName("angle")
    private Long angle;
    @SerializedName("colors")
    private List<String> colorList;

    public Gradient(Long angle, List<String> colorList) {
        this.angle = angle;
        this.colorList = colorList;
    }

    public Long getAngle() {
        return angle;
    }

    public void setAngle(Long angle) {
        this.angle = angle;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }
}
