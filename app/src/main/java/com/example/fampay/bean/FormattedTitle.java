package com.example.fampay.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FormattedTitle {
    @SerializedName("text")
    public String text;
    @SerializedName("align")
    public String align;
    @SerializedName("entities")
    public List<Entity> entityList;

    public FormattedTitle(String text, String align, List<Entity> entityList) {
        this.text = text;
        this.align = align;
        this.entityList = entityList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }
}
