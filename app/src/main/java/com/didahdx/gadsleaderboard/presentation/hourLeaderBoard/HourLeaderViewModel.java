package com.didahdx.gadsleaderboard.presentation.hourLeaderBoard;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.didahdx.gadsleaderboard.data.db.entities.HoursLeaderDb;
import com.didahdx.gadsleaderboard.data.repository.HourLeaderRepository;

import java.util.List;

import timber.log.Timber;

public class HourLeaderViewModel extends ViewModel {
    Application application;
    LiveData<List<HoursLeaderDb>> allHourLeader;
    HourLeaderRepository hourLeaderRepository;

    public HourLeaderViewModel(Application application, HourLeaderRepository hourLeaderRepository) {
        this.application = application;
        this.hourLeaderRepository = hourLeaderRepository;
        allHourLeader=hourLeaderRepository.allHourLeader;
    }


    public void OnRefresh(){
        try{
            hourLeaderRepository.onRefresh();
        }catch (Exception e){
            e.printStackTrace();
            Timber.e(e +" "+ e.getLocalizedMessage());
        }
    }
}