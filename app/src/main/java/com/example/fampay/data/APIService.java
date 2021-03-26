package com.example.fampay.data;

import com.example.fampay.bean.CardGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Observable;

import static com.example.fampay.data.UrlHelper.ENDPOINT;

public interface APIService {
    @GET(ENDPOINT)
    static Call<Observable<List<CardGroup>>> fetchCardGroups() {
        return null;
    }

}
