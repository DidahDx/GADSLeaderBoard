package com.didahdx.gadsleaderboard.presentation.hourLeaderBoard;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.didahdx.gadsleaderboard.R;
import com.didahdx.gadsleaderboard.data.db.GadsDatabase;
import com.didahdx.gadsleaderboard.data.db.HourLeaderDao;
import com.didahdx.gadsleaderboard.data.db.entities.HoursLeaderDb;
import com.didahdx.gadsleaderboard.data.repository.HourLeaderRepository;
import com.didahdx.gadsleaderboard.databinding.HourLeaderFragmentBinding;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class HourLeaderFragment extends Fragment {

    HourLeaderFragmentBinding binding;
    Application application;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        application = requireNonNull(getActivity()).getApplication();
        HourLeaderDao hourLeaderDao = GadsDatabase.getInstance(application).hourLeaderDao();
        HourLeaderRepository hourLeaderRepository = new HourLeaderRepository(hourLeaderDao);
        HourLeaderViewModelFactory factory = new HourLeaderViewModelFactory(application, hourLeaderRepository);
        HourLeaderViewModel mHourLeaderViewModel = new ViewModelProvider(this, factory).get(HourLeaderViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.hour_leader_fragment, container, false);
        binding.setHourLeaderViewModel(mHourLeaderViewModel);
        HourAdapter hourAdapter = new HourAdapter();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        binding.recyclerviewHourLeader.setLayoutManager(manager);
        binding.recyclerviewHourLeader.setAdapter(hourAdapter);
        binding.setLifecycleOwner(this);
        mHourLeaderViewModel.OnRefresh();
        mHourLeaderViewModel.allHourLeader.observe(getViewLifecycleOwner(), new Observer<List<HoursLeaderDb>>() {
            @Override
            public void onChanged(List<HoursLeaderDb> hoursLeaderDbs) {
                runLayoutAnimation(binding.recyclerviewHourLeader);
                hourAdapter.submitList(hoursLeaderDbs);
            }
        });

        return binding.getRoot();
    }


    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);

        recyclerView.setLayoutAnimation(controller);
        requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}