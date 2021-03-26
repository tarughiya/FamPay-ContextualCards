package com.example.fampay;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fampay.adapter.CardGroupAdapter;
import com.example.fampay.bean.CardGroup;
import com.example.fampay.viewModel.CardViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CardViewModel cardGroupViewModel = new ViewModelProvider(this).get(CardViewModel.class);
    private SwipeRefreshLayout swipe_refresh;
    private CardGroupAdapter cardgroupAdapter;
    private RecyclerView rv_card_groups;
    private LinearLayout err_layout;
    private TextView error_layout_tv_message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        cardgroupAdapter = new CardGroupAdapter(this);
        rv_card_groups.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_card_groups.setAdapter(cardgroupAdapter);
        swipe_refresh.setOnRefreshListener(() -> {
            getData();
        });
        getData();


    }

    private void initView() {
        rv_card_groups = findViewById(R.id.rv_card_groups);
        swipe_refresh = findViewById(R.id.swipe_refresh);
        err_layout = findViewById(R.id.err_layout);
        error_layout_tv_message = findViewById(R.id.error_layout_tv_message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }


    protected void onRefresh() {
        showLoadingScreen();
        cardGroupViewModel.fetchConCards();
    }

    /**
     * This method is used to setup observer for 'successfulFetch' live data and depending upon the
     * value, modify the view.
     * If value of 'successfulFetch' live data is true, data is displayed to the user, else a
     * self-descriptive error screen with a message is displayed.
     */
    private void getData() {
        cardGroupViewModel.successfulFetch.observe(this, users -> {
            if (users != null && users) {
                if (users) {
                    setData(cardGroupViewModel.cardGroups);
                } else {
                    showErrorScreen(cardGroupViewModel.errorMessage);
                }
            }

        });

    }

    /**
     * This method is used to modify visibility of various view components to show a
     * shimmering loading screen to the user while data is being fetched from the API.
     */
    private void showLoadingScreen() {
        swipe_refresh.setRefreshing(false);
        err_layout.setVisibility(View.GONE);
        rv_card_groups.setVisibility(View.GONE);
//        shimmer_recycler_view.visibility = View.VISIBLE
    }

    /**
     * This method to show the data to the user in the form of a recycler view and hide all other
     * layouts. This method is called once the data has been successfully from the API.
     *
     * @param cardGroups [List] of [CardGroup] that is fetched from the API and it is passed to the
     *                   adapted so that the data can be bound with the views
     */
    private void setData(List<CardGroup> cardGroups) {
        swipe_refresh.setRefreshing(false);
        err_layout.setVisibility(View.GONE);
        rv_card_groups.setVisibility(View.VISIBLE);
        cardgroupAdapter.setGroupData(cardGroups);
    }

    /**
     * This method is called when any errors occur while attempting to fetch data from API. This
     * method modifies the visibility of view components to show an error screen to the user.
     *
     * @param errorMessage the message that is to be displayed to the user describing the cause
     *                     of the failure in an abstracted manner.
     */
    private void showErrorScreen(String errorMessage) {
        swipe_refresh.setRefreshing(false);
        rv_card_groups.setVisibility(View.GONE);
        err_layout.setVisibility(View.VISIBLE);
        error_layout_tv_message.setText(errorMessage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cardGroupViewModel.successfulFetch.removeObservers(this);
        cardGroupViewModel.successfulFetch.setValue(null);
    }

}
