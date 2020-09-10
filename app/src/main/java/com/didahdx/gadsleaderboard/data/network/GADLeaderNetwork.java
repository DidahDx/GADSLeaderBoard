package com.didahdx.gadsleaderboard.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GADLeaderNetwork {
    public static final GetData gadLeaderNetwork;
    private static final Retrofit retrofit;
    public static  String BASE_URL="https://gadsapi.herokuapp.com/";
//    public static Retrofit getRetrofitInstance() {
//        if (retrofit == null) {
//            retrofit = new retrofit2.Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }

    static {
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gadLeaderNetwork = retrofit.create(GetData.class);
    }
}

