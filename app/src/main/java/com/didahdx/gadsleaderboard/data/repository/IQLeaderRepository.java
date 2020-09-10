package com.didahdx.gadsleaderboard.data.repository;

import androidx.lifecycle.LiveData;

import com.didahdx.gadsleaderboard.data.db.IQLeaderDao;
import com.didahdx.gadsleaderboard.data.db.entities.IQLeaderDb;
import com.didahdx.gadsleaderboard.data.network.GADLeaderNetwork;
import com.didahdx.gadsleaderboard.data.network.IQLeader;
import com.didahdx.gadsleaderboard.util.IOExecutor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IQLeaderRepository {
    IQLeaderDao iqLeaderDao;
    public LiveData<List<IQLeaderDb>> iqLeaderDb;

    public IQLeaderRepository(IQLeaderDao iqLeaderDao) {
        this.iqLeaderDao = iqLeaderDao;
        iqLeaderDb = iqLeaderDao.getAllIQLeaders();
    }

    public void onRefresh() {
        IOExecutor.getInstance().execute(() -> {
            GADLeaderNetwork.gadLeaderNetwork.getAllIQLeaders().enqueue(new Callback<List<IQLeader>>() {
                @Override
                public void onResponse(Call<List<IQLeader>> call, Response<List<IQLeader>> response) {
                   IOExecutor.getInstance().execute(()->{
                    List<IQLeader> iqLeaders = response.body();
                    List<IQLeaderDb> iqLeaderDbs = new ArrayList<>();
                    if (iqLeaders != null) {
                        for (IQLeader iqLeader : iqLeaders) {
                            IQLeaderDb iqLeaderDb1 = new IQLeaderDb(iqLeaders.indexOf(iqLeader),iqLeader.getName(), iqLeader.getScore(), iqLeader.getCountry(), iqLeader.getBadgeUrl());
                            iqLeaderDbs.add(iqLeaderDb1);
                        }
                    }
                    iqLeaderDao.insertAll(iqLeaderDbs);
                   });
                }

                @Override
                public void onFailure(Call<List<IQLeader>> call, Throwable t) {

                }
            });

        });
    }
}
