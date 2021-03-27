package com.example.mymovie.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String Base_URL="http://api.themoviedb.org/3/";
    private static final OkHttpClient client;
    private static MovieService Instance;;


    static {
        HttpLoggingInterceptor loggingInterceptor =new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();


    }

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();

    }

    public static MovieService getInstance(){
        if(Instance==null){
            Instance= getRetrofitInstance().create(MovieService.class);
        }
        return Instance;
    }


}
