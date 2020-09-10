package com.didahdx.gadsleaderboard.presentation.iqLeaderBoard;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.didahdx.gadsleaderboard.R;
import com.didahdx.gadsleaderboard.data.db.GadsDatabase;
import com.didahdx.gadsleaderboard.data.db.IQLeaderDao;
import com.didahdx.gadsleaderboard.data.db.entities.IQLeaderDb;
import com.didahdx.gadsleaderboard.data.repository.IQLeaderRepository;
import com.didahdx.gadsleaderboard.databinding.IQLeaderFragmentBinding;

import java.util.List;
import java.util.Objects;

public class IQLeaderFragment extends Fragment {

    private IQLeaderViewModel mIqLeaderViewModel;
    IQLeaderFragmentBinding binding;
    Application application;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        application = requireActivity().getApplication();
        IQLeaderDao iqLeaderDao=GadsDatabase.getInstance(application).iqLeaderDao();
        IQLeaderRepository iqLeaderRepository=new IQLeaderRepository(iqLeaderDao);
        IQLeaderViewModelFactory factory=new IQLeaderViewModelFactory(application,iqLeaderRepository);
        mIqLeaderViewModel=new ViewModelProvider(this,factory).get(IQLeaderViewModel.class);
        binding= DataBindingUtil.inflate(inflater,R.layout.i_q_leader_fragment, container, false);
        binding.setIqLeaderViewModel(mIqLeaderViewModel);
        IQAdapter iqAdapter=new IQAdapter();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        binding.recyclerviewIqLeader.setLayoutManager(manager);
        binding.recyclerviewIqLeader.setAdapter(iqAdapter);
        binding.setLifecycleOwner(this);
        mIqLeaderViewModel.OnRefresh();
        mIqLeaderViewModel.allIQLeader.observe(getViewLifecycleOwner(), new Observer<List<IQLeaderDb>>() {
            @Override
            public void onChanged(List<IQLeaderDb> iqLeaderDbs) {
                iqAdapter.submitList(iqLeaderDbs);
            }
        });

        return binding.getRoot();
    }


}