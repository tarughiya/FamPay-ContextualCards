package com.example.fampay.data;

import retrofit2.http.GET;

public interface APIService {
    @GET(ENDPOINT)
    fun fetchCardGroups(): Observable<List<CardGroup>>
}
