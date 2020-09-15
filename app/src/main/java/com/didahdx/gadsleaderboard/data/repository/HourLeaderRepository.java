package com.didahdx.gadsleaderboard.data.repository;

import androidx.annotation.Keep;
import androidx.lifecycle.LiveData;

import com.didahdx.gadsleaderboard.data.db.GadsDatabase;
import com.didahdx.gadsleaderboard.data.db.HourLeaderDao;
import com.didahdx.gadsleaderboard.data.db.entities.HoursLeaderDb;
import com.didahdx.gadsleaderboard.data.network.GADLeaderNetwork;
import com.didahdx.gadsleaderboard.data.network.HoursLeaders;
import com.didahdx.gadsleaderboard.util.IOExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HourLeaderRepository {
    HourLeaderDao hourLeaderDao;
    public LiveData<List<HoursLeaderDb>> allHourLeader;

    public HourLeaderRepository(HourLeaderDao hourLeaderDao) {
        this.hourLeaderDao = hourLeaderDao;
        allHourLeader = hourLeaderDao.getAllHoursLeaders();
    }

    public void onRefresh() {
        IOExecutor.getInstance().execute(() -> {
            GADLeaderNetwork.gadLeaderNetwork.getAllHourLeaders().enqueue(new Callback<List<HoursLeaders>>() {
                @Override
                public void onResponse(Call<List<HoursLeaders>> call, Response<List<HoursLeaders>> response) {
                    IOExecutor.getInstance().execute(()->{
                    List<HoursLeaders> hoursLeaders = response.body();
                    List<HoursLeaderDb> hoursLeaderDbs = new ArrayList<>();
                    if (hoursLeaders != null) {
                        for (HoursLeaders hoursLeaders1 : hoursLeaders) {
                            HoursLeaderDb hoursLeaderDb = new HoursLeaderDb(hoursLeaders.indexOf(hoursLeaders1),hoursLeaders1.getName(), hoursLeaders1.getHours(), hoursLeaders1.getCountry(), hoursLeaders1.getBadgeUrl());
                            hoursLeaderDbs.add(hoursLeaderDb);
                        }
                    }

                        hourLeaderDao.insertAll(hoursLeaderDbs);
                    });
                }

                @Override
                public void onFailure(Call<List<HoursLeaders>> call, Throwable t) {

                }
            });



        });
    }

}
