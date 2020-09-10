package com.didahdx.gadsleaderboard.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleForm {
    public static final SubmitForm submit;
    private static final Retrofit retrofit;
    public static  String BASE_URL=" https://docs.google.com/forms/d/e/";


    static {
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        submit = retrofit.create(SubmitForm.class);
    }
}
