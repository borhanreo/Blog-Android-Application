package com.example.blog_android_application.dagger;

import com.example.blog_android_application.retrofit.ApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Borhan Uddin on 4/7/2021.
 * borhan.u.cse@gmail.com
 */
@Module
public class RefriProjectConstant {
    public RefriProjectConstant() {
    }
    private String BASE_URL="https://my-json-server.typicode.com/";
    private ApiService apiService;
    @Provides
    public ApiService provideApiService(){
        if(apiService == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(5, TimeUnit.MINUTES);
            httpClient.readTimeout(5, TimeUnit.MINUTES);
            httpClient.addInterceptor(logging);
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
