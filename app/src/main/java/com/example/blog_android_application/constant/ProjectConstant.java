package com.example.blog_android_application.constant;

import com.example.blog_android_application.retrofit.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Borhan Uddin on 4/6/2021.
 * borhan.u.cse@gmail.com
 */
public class ProjectConstant {
    //public static String D_COVER_PHOTO_URL="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/1024px-Circle-icons-profile.svg.png";
    public static String D_COVER_PHOTO_URL="https://i.picsum.photos/id/579/200/300.jpg?hmac=9MD8EV4Jl9EqKLkTj5kyNdBUKQWyHk2m4pE4UCBGc8Q";

    public static String KEY="obj_key";
    public static String KEY_FROM="obj_key_from";
    public static String BUNDLE_KEY_ID="id";
    public static String BUNDLE_KEY_TITLE="title";
    public static String BUNDLE_KEY_DESC="desc";
    public static String BUNDLE_KEY_COVER_PHOTO="cphoto";
    public static String BUNDLE_KEY_CATEORY="category";
    public static String BUNDLE_KEY_AUTHOR="author";
    public static String BASE_URL="https://my-json-server.typicode.com/";
    private static ApiService apiService;
    public static ApiService getApiService(){
        if(apiService == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(5, TimeUnit.MINUTES);
            httpClient.readTimeout(5, TimeUnit.MINUTES);
            httpClient.addInterceptor(logging);
            /*httpClient.addInterceptor(new HeaderInterceptor());
            httpClient.addInterceptor(provideOfflineCacheInterceptor());
            httpClient.addInterceptor(provideCacheInterceptor());
            httpClient.cache(provideCache());*/
            apiService = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }


}
