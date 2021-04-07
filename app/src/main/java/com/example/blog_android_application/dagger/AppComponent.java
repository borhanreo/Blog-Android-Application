package com.example.blog_android_application.dagger;

import com.example.blog_android_application.activity.MainActivity;

import dagger.Component;

/**
 * Created by Borhan Uddin on 4/7/2021.
 * borhan.u.cse@gmail.com
 */
@Component(modules = RefriProjectConstant.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
