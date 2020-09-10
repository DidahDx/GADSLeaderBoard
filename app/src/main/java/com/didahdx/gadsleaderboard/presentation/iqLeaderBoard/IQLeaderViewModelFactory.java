package com.didahdx.gadsleaderboard.presentation.iqLeaderBoard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.didahdx.gadsleaderboard.data.db.IQLeaderDao;
import com.didahdx.gadsleaderboard.data.repository.IQLeaderRepository;

public class IQLeaderViewModelFactory implements ViewModelProvider.Factory {
    Application application;
    IQLeaderRepository iqLeaderRepository;

    public IQLeaderViewModelFactory(Application application, IQLeaderRepository iqLeaderRepository) {
        this.application = application;
        this.iqLeaderRepository = iqLeaderRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(IQLeaderViewModel.class)) {
            return (T) (new IQLeaderViewModel(application, iqLeaderRepository));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
