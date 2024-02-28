package com.example.convidados.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.model.GuestModel;
import com.example.convidados.view.listener.OnListClick;
import com.example.convidados.view.viewholder.GuestViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestViewHolder> {
    private List<GuestModel> mList = new ArrayList<>();
    private OnListClick mOnListClick;

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_guest_row, parent, false);

        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        holder.bind(this.mList.get(position), mOnListClick);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void attachList(List<GuestModel> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void attachListener(OnListClick listener) {
        this.mOnListClick = listener;
    }


}
