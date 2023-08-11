package com.example.thi11_8_ph26008.api;

import com.example.thi11_8_ph26008.models.Spmodel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://64d5cd86613ee4426d979df7.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("api/api")
    Call<List<Spmodel>> GetDataMockApi();

    @POST("api/api")
    Call<Spmodel> ThemDataToSever(@Body Spmodel spModel);

    @PUT("api/api/{id}")
    Call<Spmodel> UpdateData(@Path("id") String id, @Body Spmodel spModel );

    @DELETE("api/api/{id}")
    Call<Spmodel> DeleteData(@Path("id") String id);
}
