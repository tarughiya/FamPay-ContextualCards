package com.example.fampay.bean;

import com.google.gson.annotations.SerializedName;

public class Cta {

    @SerializedName("text")
    private String text;
    @SerializedName("bg_color")
    private String bg_color;
    @SerializedName("text_color")
    private String text_color;
    @SerializedName("url_choice")
    private String url_choice;
    @SerializedName("url")
    private String url;
    @SerializedName("other_url")
    private String otherUrl;

    public Cta(String text, String bg_color, String text_color, String url_choice, String url, String otherUrl) {
        this.text = text;
        this.bg_color = bg_color;
        this.text_color = text_color;
        this.url_choice = url_choice;
        this.url = url;
        this.otherUrl = otherUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getUrl_choice() {
        return url_choice;
    }

    public void setUrl_choice(String url_choice) {
        this.url_choice = url_choice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOtherUrl() {
        return otherUrl;
    }

    public void setOtherUrl(String otherUrl) {
        this.otherUrl = otherUrl;
    }
}
