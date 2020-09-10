package com.didahdx.gadsleaderboard.presentation.Main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.didahdx.gadsleaderboard.R;
import com.didahdx.gadsleaderboard.presentation.hourLeaderBoard.HourLeaderFragment;
import com.didahdx.gadsleaderboard.presentation.iqLeaderBoard.IQLeaderFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainFragment extends Fragment {

    ViewPager2 viewPager;
    MainAdapter mainAdapter;
    TabLayout tabLayout;

    private void collectIds(View view) {
         tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      collectIds(view);
        mainAdapter = new MainAdapter(this);
        mainAdapter.AddFragment(new HourLeaderFragment(),getString(R.string.learning_leaders));
        mainAdapter.AddFragment(new IQLeaderFragment(),getString(R.string.skill_iq_leaders));
        viewPager.setAdapter(mainAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(mainAdapter.getTitle(position))
        ).attach();
    }
}