package com.didahdx.gadsleaderboard.presentation.hourLeaderBoard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.didahdx.gadsleaderboard.data.db.HourLeaderDao;
import com.didahdx.gadsleaderboard.data.repository.HourLeaderRepository;
import com.didahdx.gadsleaderboard.presentation.iqLeaderBoard.IQLeaderViewModel;

public class HourLeaderViewModelFactory implements ViewModelProvider.Factory {

    Application application;
    HourLeaderRepository hourLeaderRepository;

    public HourLeaderViewModelFactory(Application application, HourLeaderRepository hourLeaderRepository) {
        this.application = application;
        this.hourLeaderRepository = hourLeaderRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HourLeaderViewModel.class)) {
            return (T) (new HourLeaderViewModel(application, hourLeaderRepository));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
