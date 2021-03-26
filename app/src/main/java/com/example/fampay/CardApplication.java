package com.example.fampay;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class CardApplication extends Application {

    private static CardApplication instance = new CardApplication();

    public CardApplication getInstance() {
        return instance;
    }


    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
