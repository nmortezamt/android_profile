package com.example.v2.tasks.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2.R;

import java.util.List;

public class UserSectionAdapter extends RecyclerView.Adapter<UserSectionAdapter.VViewHolder> {

    private List<List<UserModel>> parentList;
    private OnUserClickListener listener;

    public UserSectionAdapter(List<List<UserModel>> parentList, OnUserClickListener listener) {
        this.parentList = parentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vertical_list, parent, false);
        return new VViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VViewHolder holder, int position) {
        UserRowAdapter adapter = new UserRowAdapter(parentList.get(position), listener);
        holder.recycler.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    class VViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycler;

        public VViewHolder(View itemView) {
            super(itemView);
            recycler = itemView.findViewById(R.id.rvHorizontalUsers);
            recycler.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
        }
    }
}