package com.example.fampay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fampay.R;
import com.example.fampay.bean.CardGroup;

import java.util.ArrayList;
import java.util.List;

public class CardGroupAdapter extends RecyclerView.Adapter<CardGroupAdapter.ViewHolder> {
    private Context context;
    private List<CardGroup> groupData = new ArrayList<>();

    public CardGroupAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_group,
                parent, false);
        return new CardGroupAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardGroupAdapter.ViewHolder holder, int position) {
        cardView(holder, position);
    }

    private void cardView(ViewHolder holder, int position) {
        CardAdapter cardAdapter = new CardAdapter(groupData.get(position).getDesign_type(), groupData.get(position).getId());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(cardAdapter);

        holder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    cardAdapter.hideMenu();
                }
            }
        });
        cardAdapter.setCardData(groupData.get(position).getCardsList());

    }


    public void setGroupData(List<CardGroup> groups) {
        groupData.clear();
        groupData.addAll(groups);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return groupData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_card);
        }
    }

}
