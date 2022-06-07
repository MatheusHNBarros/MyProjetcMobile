package com.example.myproject;


import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/v2/api-doc")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CursoService criarService() {
        return retrofit.create(CursoService.class);
    }

}
