package com.example.fampay.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cards {

    @SerializedName("name")
    private String name;
    @SerializedName("title")
    private String title;
    @SerializedName("formattedTitle")
    private FormattedTitle formattedTitle;
    @SerializedName("icon")
    private IconImage icon;
    @SerializedName("url")
    private String url;
    @SerializedName("bg_color")
    private String bg_color;
    @SerializedName("is_disabled")
    private boolean is_disabled;
    @SerializedName("bg_image")
    private IconImage bg_image;
    @SerializedName("formatted_description")
    private FormattedTitle formatted_description;
    @SerializedName("cta")
    private List<Cta> ctaList;
    @SerializedName("bg_gradient")
    private Gradient bg_gradient;
    private boolean swipeMenu;

    public Cards(String name, String title, FormattedTitle formattedTitle, IconImage icon, String url, String bg_color, boolean is_disabled, IconImage bg_image, FormattedTitle formatted_description, List<Cta> ctaList, Gradient bg_gradient, boolean swipeMenu) {
        this.name = name;
        this.title = title;
        this.formattedTitle = formattedTitle;
        this.icon = icon;
        this.url = url;
        this.bg_color = bg_color;
        this.is_disabled = is_disabled;
        this.bg_image = bg_image;
        this.formatted_description = formatted_description;
        this.ctaList = ctaList;
        this.bg_gradient = bg_gradient;
        this.swipeMenu = swipeMenu;
    }


    public Cards(String menu_card) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FormattedTitle getFormattedTitle() {
        return formattedTitle;
    }

    public void setFormattedTitle(FormattedTitle formattedTitle) {
        this.formattedTitle = formattedTitle;
    }

    public IconImage getIcon() {
        return icon;
    }

    public void setIcon(IconImage icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public boolean isIs_disabled() {
        return is_disabled;
    }

    public void setIs_disabled(boolean is_disabled) {
        this.is_disabled = is_disabled;
    }

    public IconImage getBg_image() {
        return bg_image;
    }

    public void setBg_image(IconImage bg_image) {
        this.bg_image = bg_image;
    }

    public FormattedTitle getFormatted_description() {
        return formatted_description;
    }

    public void setFormatted_description(FormattedTitle formatted_description) {
        this.formatted_description = formatted_description;
    }

    public List<Cta> getCtaList() {
        return ctaList;
    }

    public void setCtaList(List<Cta> ctaList) {
        this.ctaList = ctaList;
    }

    public Gradient getBg_gradient() {
        return bg_gradient;
    }

    public void setBg_gradient(Gradient bg_gradient) {
        this.bg_gradient = bg_gradient;
    }

    public boolean isSwipeMenu() {
        return swipeMenu;
    }

    public void setSwipeMenu(boolean swipeMenu) {
        this.swipeMenu = swipeMenu;
    }
}
