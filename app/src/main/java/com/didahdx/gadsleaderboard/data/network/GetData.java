package com.didahdx.gadsleaderboard.data.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData{

    @GET("/api/hours")
    Call<List<HoursLeaders>> getAllHourLeaders();

    @GET("/api/skilliq")
    Call<List<IQLeader>> getAllIQLeaders();
}
