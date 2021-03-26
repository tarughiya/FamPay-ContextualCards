package com.example.fampay.data;

import com.example.fampay.bean.CardGroup;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;

public class Repository {
    private RetrofitClient apiManager;

    /**
     * used to fetch card groups
     *
     * @return an [Observable] of list of card groups fetched from the API.
     */
    public Call<Observable<List<CardGroup>>> getCardGroups(){
        return APIService.fetchCardGroups();
    }

}
