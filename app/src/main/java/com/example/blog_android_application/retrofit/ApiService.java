package com.example.blog_android_application.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Borhan Uddin on 4/6/2021.
 * borhan.u.cse@gmail.com
 */
public interface ApiService {
    @GET("sohel-cse/simple-blog-api/db")
    Call<ResponseModel> getData();
}
