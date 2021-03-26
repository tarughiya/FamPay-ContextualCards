package com.example.fampay.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardGroup {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("design_type")
    private String design_type;
    @SerializedName("card_type")
    private Long card_type;
    @SerializedName("cards")
    private List<Cards> cardsList;
    @SerializedName("is_scrollable")
    private boolean is_scrollable;
    public CardGroup(Long id, String name, String design_type, Long card_type, List<Cards> cardsList, boolean is_scrollable) {
        this.id = id;
        this.name = name;
        this.design_type = design_type;
        this.card_type = card_type;
        this.cardsList = cardsList;
        this.is_scrollable = is_scrollable;
    }

    public CardGroup() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesign_type() {
        return design_type;
    }

    public void setDesign_type(String design_type) {
        this.design_type = design_type;
    }

    public Long getCard_type() {
        return card_type;
    }

    public void setCard_type(Long card_type) {
        this.card_type = card_type;
    }

    public List<Cards> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<Cards> cardsList) {
        this.cardsList = cardsList;
    }

    public boolean isIs_scrollable() {
        return is_scrollable;
    }

    public void setIs_scrollable(boolean is_scrollable) {
        this.is_scrollable = is_scrollable;
    }

   public enum DesignTypes {

        @SerializedName("HC1")
        SMALL_DISPLAY_CARD,

        @SerializedName("HC3")
        BIG_DISPLAY_CARD,

        @SerializedName("HC5")
        IMAGE_CARD,

        @SerializedName("HC6")
        SMALL_CARD_WITH_ARROW,

        @SerializedName("HC9")
        DYNAMIC_WIDTH_CARD

    }


}

