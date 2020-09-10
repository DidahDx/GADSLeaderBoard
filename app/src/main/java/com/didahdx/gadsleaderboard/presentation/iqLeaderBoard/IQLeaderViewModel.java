package com.didahdx.gadsleaderboard.presentation.iqLeaderBoard;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.didahdx.gadsleaderboard.data.db.entities.IQLeaderDb;
import com.didahdx.gadsleaderboard.data.repository.IQLeaderRepository;

import java.util.List;

import timber.log.Timber;

public class IQLeaderViewModel extends ViewModel {

    Application application;
    IQLeaderRepository iqLeaderRepository;
    LiveData<List<IQLeaderDb>> allIQLeader;


    public IQLeaderViewModel(Application application, IQLeaderRepository iqLeaderRepository) {
        this.application = application;
        this.iqLeaderRepository = iqLeaderRepository;
        allIQLeader = iqLeaderRepository.iqLeaderDb;
    }


    public void OnRefresh(){
        try{
            iqLeaderRepository.onRefresh();
        }catch (Exception e){
            e.printStackTrace();
            Timber.e(e +" "+ e.getLocalizedMessage());
        }

    }

}