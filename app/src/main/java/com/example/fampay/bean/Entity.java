package com.example.fampay.bean;

import com.google.gson.annotations.SerializedName;

public class Entity {

    @SerializedName("text")
    private String text;
    @SerializedName("color")
    private String color;
    @SerializedName("url")
    private String url;
    @SerializedName("font_style")
    private String font_style;

    public Entity(String text, String color, String url, String font_style) {
        this.text = text;
        this.color = color;
        this.url = url;
        this.font_style = font_style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFont_style() {
        return font_style;
    }

    public void setFont_style(String font_style) {
        this.font_style = font_style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
