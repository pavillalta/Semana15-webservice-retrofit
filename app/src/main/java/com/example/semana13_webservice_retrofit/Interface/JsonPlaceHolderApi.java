package com.example.semana13_webservice_retrofit.Interface;

import com.example.semana13_webservice_retrofit.Modelo.Posts;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts") Call<List<Posts>> getPost();


}
