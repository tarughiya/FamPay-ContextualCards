package com.example.fampay.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fampay.CardApplication;
import com.example.fampay.R;
import com.example.fampay.bean.CardGroup;
import com.example.fampay.bean.Cards;
import com.example.fampay.bean.Cta;
import com.example.fampay.utilities.ImageHelper;
import com.example.fampay.utilities.PreferenceHelper;
import com.example.fampay.utilities.TextFormatter;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private String designType;
    private CardGroup.DesignTypes types;
    private Long groupId;
    private List<Cards> cardData = new ArrayList<>();
    private int SHOW_MENU = 1;
    private int HIDE_MENU = 2;

    public CardAdapter(String designType, Long groupId) {
        this.designType = designType;
        this.groupId = groupId;
    }

    /**
     * To specify the view type as either 'SHOW_MENU' or 'HIDE_MENU'.
     */
    @Override
    public int getItemViewType(int position) {
        if (cardData.get(position).isSwipeMenu()) {
            return SHOW_MENU;
        } else {
            return HIDE_MENU;
        }
    }

    @Override
    public int getItemCount() {
        return cardData.size();
    }

    /**
     * This method is used to display "Right-Swipe Menu" by simply adding a new card item, let's
     * call it as "Menu Card", in the list. The if conditions in the beginning are put to
     * avoid creation or multiple "Menu Cards" for the same item. For creating a "Menu Card", simply
     * an instance of [Card] is created with it's name as "menu_card" and 'swipeMenu' field set to
     * true. This field is false for all other(normal) cards.
     *
     * @param position to specify the card for which menu card is to be created
     */
    private void showMenu(int position) {
        if (!cardData.isEmpty() && !cardData.get(0).isSwipeMenu()) {
            Cards menuCard = new Cards("menu_card");
            menuCard.setSwipeMenu(true);
            cardData.add(position, menuCard);
            notifyDataSetChanged();
        }
    }

    /**
     * To hide the "Menu Card". This function is not private as it is also being
     * called inside [CardGroupAdapter].
     */
    void hideMenu() {
        if (!cardData.isEmpty() && !cardData.get(0).isSwipeMenu()) {
            cardData.remove(0);
            notifyDataSetChanged();
        }
    }

    /**
     * To delete the specified card from list and store it's group's id in local storage to avoid
     * putting this group in list in future.
     *
     * @param position to specify the card whose group is to be excluded
     */
    private void deleteCard(int position) {
        if (cardData.size() > position) {
            cardData.remove(position);
            notifyDataSetChanged();
            PreferenceHelper.addGroupId(groupId.toString());
        }
        hideMenu();
    }

    /**
     * To set/update the [ArrayList] of [Card].
     *
     * @param cards updated list
     */
    void setCardData(List<Cards> cards) {
        cardData.clear();
        cardData.addAll(cards);
        notifyDataSetChanged();
    }


    /**
     * To specify the [CardViewHolder] depending upon the [CardGroup.DesignType] and whether
     * following item is "Menu Card" or not.
     *
     * @param parent   the parent [ViewGroup]
     * @param viewType used to specify whether this a "Menu Card" or not. Note that the 'viewType'
     *                 is 'SHOW_MENU' for a "Menu Card" and 'HIDE_MENU' for others.
     */

    @NonNull
    @Override
    public CardAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int resourceLayout = R.layout.item_big_display_card;

        types= CardGroup.DesignTypes.valueOf(designType);

        switch (types) {
            case BIG_DISPLAY_CARD: {
                if (viewType == SHOW_MENU) {
                    resourceLayout = R.layout.menu_long_press;
                } else {
                    resourceLayout = R.layout.item_big_display_card;
                }
                break;
            }

            case SMALL_CARD_WITH_ARROW:
                resourceLayout = R.layout.item_small_card_arrow;

            case IMAGE_CARD:
                resourceLayout = R.layout.item_image_card;

            case SMALL_DISPLAY_CARD:
                resourceLayout = R.layout.item_small_display_card;

            case DYNAMIC_WIDTH_CARD:
                ////
                resourceLayout = R.layout.item_center_card;

        }

        return new CardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(resourceLayout, parent, false)
        );

    }


    /**
     * This method is used to bind data with corresponding views. Since in our case we have
     * varied sets of data and their corresponding views, this method again makes calls to
     * different sets of methods depending upon the card type to bind the views accordingly.
     *
     * @param holder   instance of [CardViewHolder] which contains methods for binding data with
     *                 their corresponding views.
     * @param position used to specify the card whose data will be binded.
     */
    @Override
    public void onBindViewHolder(@NonNull CardAdapter.CardViewHolder holder, int position) {
        switch (types) {
            case BIG_DISPLAY_CARD: {
                if (!cardData.get(position).isSwipeMenu()) {
                    holder.bindBigDisplayCard(cardData.get(position));
                    holder.itemView.setOnLongClickListener(v -> {
                        showMenu(position);
                        return true;
                    });


                } else {

                    holder.itemView.findViewById(R.id.menu_ll_dismiss).setOnClickListener(v -> {
                        deleteCard(position + 1);
                    });

                    holder.itemView.findViewById(R.id.menu_ll_remind).setOnClickListener(v -> {
                        deleteCard(position + 1);
                    });
                }
            }

            case SMALL_CARD_WITH_ARROW:
                holder.bindSmallCardArrow(cardData.get(position));

            case IMAGE_CARD:
                holder.bindImageCard(cardData.get(position));

            case SMALL_DISPLAY_CARD:
                holder.bindSmallCard(cardData.get(position));

            case DYNAMIC_WIDTH_CARD:
                ////
                holder.bindCenterCard(cardData.get(position));

        }
    }


    class CardViewHolder extends RecyclerView.ViewHolder {

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * To bind the data and view for 'Big Display Cards'
         *
         * @param card an instance [Card] which contains the data fetched from the APIs.
         */
        void bindBigDisplayCard(Cards card) {
            TextFormatter.applyFormattedText(
                    card.getFormattedTitle(), itemView.findViewById(R.id.bdc_tv_title), card.getTitle()
            );

            TextFormatter.applyFormattedText(
                    card.getFormatted_description(), itemView.findViewById(R.id.bdc_tv_description), card.getFormatted_description().text
            );

            if (null != card.getBg_image().getImage_url()) {
                ImageHelper.loadImage(card.getBg_image().getImage_url(), itemView, itemView.findViewById(R.id.bdc_card_view));
            }

            if (null != card.getCtaList().get(0)) {
                Cta cta = card.getCtaList().get(0);
                Button button = itemView.findViewById(R.id.bdc_bt_action);
                itemView.findViewById(R.id.bdc_bt_action).setBackgroundColor(Color.parseColor(cta.getBg_color()));
                button.setText(cta.getText());
                button.setTextColor(Color.parseColor(cta.getText_color()));
                itemView.findViewById(R.id.bdc_bt_action).setOnClickListener(v -> {
                    {
                        if (cta.getUrl() != null) {
                            launchAction(cta.getUrl());
                        } else if (cta.getOtherUrl() != null) {
                            launchAction(cta.getOtherUrl());
                        }
                    }
                });
            }

        }

        /**
         * To bind the data and view for 'Small Card with Arrows'
         *
         * @param card an instance [Card] which contains the data fetched from the APIs.
         */
        void bindSmallCardArrow(Cards card) {
            TextFormatter.applyFormattedText(
                    card.getFormattedTitle(), itemView.findViewById(R.id.sca_tv_title), card.getTitle()
            );

            CardView cardView = itemView.findViewById(R.id.sca_card_view);
            cardView.setCardBackgroundColor(Color.parseColor(card.getBg_color()));

            ImageHelper.loadImage(card.getIcon().getImage_url(), itemView, itemView.findViewById(R.id.sca_iv_icon));

            itemView.findViewById(R.id.sca_card_view).setOnClickListener(v -> {
                {
                    if (card.getUrl() != null) {
                        launchAction(card.getUrl());
                    }
                }
            });
        }

        /**
         * To bind the data and view for 'Image Cards'
         *
         * @param card an instance [Card] which contains the data fetched from the APIs.
         */
        void bindImageCard(Cards card) {
            ImageHelper.loadImage(card.getBg_image().getImage_url(), itemView, itemView.findViewById(R.id.ic_card_view));


            itemView.findViewById(R.id.ic_card_view).setOnClickListener(v -> {
                {
                    if (card.getUrl() != null) {
                        launchAction(card.getUrl());
                    }
                }
            });
        }

        /**
         * To bind the data and view for 'Center Cards'
         *
         * @param card an instance [Card] which contains the data fetched from the APIs.
         */
        void bindCenterCard(Cards card) {
            TextFormatter.applyFormattedText(
                    card.getFormattedTitle(), itemView.findViewById(R.id.cc_tv_title), card.getTitle()
            );

            TextFormatter.applyFormattedText(
                    card.getFormatted_description(), itemView.findViewById(R.id.cc_tv_description), card.getFormatted_description().text
            );

            GradientDrawable gradient = new GradientDrawable();
            int[] arr = new int[card.getBg_gradient().getColorList().size()];

            for (int i = 0; i < card.getBg_gradient().getColorList().size(); i++) {
                arr[i] = Color.parseColor(card.getBg_gradient().getColorList().get(i));
            }

            gradient.setColors(arr);

            gradient.setShape(GradientDrawable.RECTANGLE);
            itemView.findViewById(R.id.cc_linear_layout).setBackgroundDrawable(gradient);

            {
                ImageHelper.loadImage(card.getIcon().getImage_url(), itemView, itemView.findViewById(R.id.cc_iv_icon));
            }

            if (null != card.getCtaList().get(0)) {
                Cta cta = card.getCtaList().get(0);
                Button button= itemView.findViewById(R.id.cc_bt_action_first);
                itemView.findViewById(R.id.cc_bt_action_first).setBackgroundColor(Color.parseColor(cta.getBg_color()));
                button.setText(cta.getText());
                button.setTextColor(Color.parseColor(cta.getText_color()));
                itemView.findViewById(R.id.cc_bt_action_first).setOnClickListener(v -> {
                    {
                        if (cta.getUrl() != null) {
                            launchAction(cta.getUrl());
                        } else if (cta.getOtherUrl() != null) {
                            launchAction(cta.getOtherUrl());
                        }
                    }
                });
            }

            if (null != card.getCtaList().get(0)) {
                Cta cta = card.getCtaList().get(0);
                Button button= itemView.findViewById(R.id.cc_bt_action_second);
                itemView.findViewById(R.id.cc_bt_action_second).setBackgroundColor(Color.parseColor(cta.getBg_color()));
                button.setText(cta.getText());
                button.setTextColor(Color.parseColor(cta.getText_color()));
                itemView.findViewById(R.id.cc_bt_action_second).setOnClickListener(v -> {
                    {
                        if (cta.getUrl() != null) {
                            launchAction(cta.getUrl());
                        } else if (cta.getOtherUrl() != null) {
                            launchAction(cta.getOtherUrl());
                        }
                    }
                });
            }


        }

        /**
         * To bind the data and view for 'Small Cards'
         *
         * @param card an instance [Card] which contains the data fetched from the APIs.
         */
        void bindSmallCard(Cards card) {
            TextFormatter.applyFormattedText(
                    card.getFormattedTitle(), itemView.findViewById(R.id.sdc_tv_title), card.getTitle()
            );

            TextFormatter.applyFormattedText(
                    card.getFormatted_description(), itemView.findViewById(R.id.sdc_tv_description), card.getFormatted_description().text
            );

            itemView.findViewById(R.id.sdc_linear_layout).setBackgroundColor(Color.parseColor(card.getBg_color()));


            ImageHelper.loadImage(card.getIcon().getImage_url(), itemView, itemView.findViewById(R.id.sdc_iv_icon));


            itemView.findViewById(R.id.sdc_card_view).setOnClickListener(v -> {
                {
                    if (card.getUrl() != null) {
                        launchAction(card.getUrl());
                    }
                }
            });
        }

        /**
         * This method creates [Intent] to handle "Call To Action" functionality
         *
         * @param url the url or deep link fetched from the API for a specified card
         */
        private boolean launchAction(String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            CardApplication.getContext().startActivity(intent);
            return true;
        }
    }


}
