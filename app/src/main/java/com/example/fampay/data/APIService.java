package com.example.fampay.data;

import com.example.fampay.bean.CardGroup;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

import static com.example.fampay.data.UrlHelper.ENDPOINT;

public interface APIService {
    @GET(ENDPOINT)
    static Observable<List<CardGroup>> fetchCardGroups() {
        return null;
    }

}
