package com.example.fampay.data;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.fampay.data.UrlHelper.BASE_URL;
import static com.example.fampay.data.UrlHelper.getAPIService;

public class RetrofitClient {
    private static Retrofit retrofit;

    private APIService apiService;

    public static Retrofit getRetrofitInstance(Context context) {

        if (retrofit == null) {
            retrofit = (Retrofit) new Retrofit.Builder()
                    .client(getOkClient())
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(getAPIService(context));
        }
        return retrofit;
    }

    public static OkHttpClient getOkClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        return clientBuilder.build();
    }


}
