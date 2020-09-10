package com.didahdx.gadsleaderboard.presentation.hourLeaderBoard;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.didahdx.gadsleaderboard.data.db.entities.HoursLeaderDb;
import com.didahdx.gadsleaderboard.databinding.HourContainerBinding;

import java.util.Objects;

public class HourAdapter extends ListAdapter<HoursLeaderDb, HourAdapter.HourViewHolder> {

    public HourAdapter() {
        super(DIFF_CALLBACK);
    }


    private static final DiffUtil.ItemCallback<HoursLeaderDb> DIFF_CALLBACK = new DiffUtil.ItemCallback<HoursLeaderDb>() {
        @Override
        public boolean areItemsTheSame(@NonNull HoursLeaderDb oldItem, @NonNull HoursLeaderDb newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull HoursLeaderDb oldItem, @NonNull HoursLeaderDb newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };


    public static class HourViewHolder extends RecyclerView.ViewHolder {
        private HourContainerBinding binding;

        public HourViewHolder(HourContainerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HoursLeaderDb hoursLeaderDb) {
            binding.setHourLeaderDetails(hoursLeaderDb);
            binding.executePendingBindings();
        }

    }

    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HourContainerBinding itemBinding = HourContainerBinding.inflate(layoutInflater, parent, false);
        return new HourViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        HoursLeaderDb hoursLeaderDb = getItem(position);
        holder.bind(hoursLeaderDb);
    }


}