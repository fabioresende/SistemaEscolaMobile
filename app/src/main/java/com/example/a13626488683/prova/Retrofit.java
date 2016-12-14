package com.example.a13626488683.prova;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by 13626488683 on 01/11/2016.
 */
public interface Retrofit {


    @GET("dados")
    Call<Objeto> converterIMC();

}
