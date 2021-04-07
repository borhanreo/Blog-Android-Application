package com.example.blog_android_application.dagger;

import android.app.Application;

/**
 * Created by Borhan Uddin on 4/7/2021.
 * borhan.u.cse@gmail.com
 */
public class BlogApplication extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().refriProjectConstant(new RefriProjectConstant()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
