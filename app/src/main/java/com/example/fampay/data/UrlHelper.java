package com.example.fampay.data;

import android.content.Context;

public class UrlHelper {
    public static final String ENDPOINT = "fefcfbeb-5c12-4722-94ad-b8f92caad1ad/";
    private static String PROTOCOL = "http://";
    private static String DOMAIN_NAME = "www.mocky.io/";
    private static String VERSION = "v3";
    public static final String BASE_URL = PROTOCOL.concat(DOMAIN_NAME).concat(VERSION);

    public static APIService getAPIService(Context ctx) {
        return RetrofitClient.getRetrofitInstance(ctx).create(APIService.class);
    }

}
