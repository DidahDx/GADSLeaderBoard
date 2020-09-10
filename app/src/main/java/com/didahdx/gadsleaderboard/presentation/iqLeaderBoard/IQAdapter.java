package com.didahdx.gadsleaderboard.presentation.iqLeaderBoard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.didahdx.gadsleaderboard.data.db.entities.IQLeaderDb;
import com.didahdx.gadsleaderboard.databinding.IqContainerBinding;

import java.util.Objects;

public class IQAdapter extends ListAdapter<IQLeaderDb, IQAdapter.IQViewHolder> {

    public IQAdapter() {
        super(DIFF_CALLBACK);
    }


    private static final DiffUtil.ItemCallback<IQLeaderDb> DIFF_CALLBACK = new DiffUtil.ItemCallback<IQLeaderDb>() {
        @Override
        public boolean areItemsTheSame(@NonNull IQLeaderDb oldItem, @NonNull IQLeaderDb newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull IQLeaderDb oldItem, @NonNull IQLeaderDb newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };

    public static class IQViewHolder extends RecyclerView.ViewHolder {
        private IqContainerBinding binding;

        public IQViewHolder(IqContainerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(IQLeaderDb iqLeaderDb) {
            binding.setIqLeader(iqLeaderDb);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public IQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        IqContainerBinding iqContainerBinding = IqContainerBinding.inflate(layoutInflater, parent, false);
        return new IQViewHolder(iqContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IQViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

}