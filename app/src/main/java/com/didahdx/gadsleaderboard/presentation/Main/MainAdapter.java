package com.didahdx.gadsleaderboard.presentation.Main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentStateAdapter {
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> fragmentTitle = new ArrayList<String>();
    public static final String ARG_OBJECT = "ARG_OBJECT";

    public MainAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = fragmentList.get(position);
        Bundle args = new Bundle();
        args.putString(ARG_OBJECT, fragmentTitle.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public void AddFragment(Fragment fragment, String Title) {
        fragmentList.add(fragment);
        fragmentTitle.add(Title);
    }

    public String getTitle(int position) {
        return fragmentTitle.get(position);
    }
}
