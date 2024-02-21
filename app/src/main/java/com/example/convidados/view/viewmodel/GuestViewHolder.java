package com.example.convidados.view.viewmodel;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.model.GuestModel;

public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    public GuestViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mTextView = itemView.findViewById(R.id.text_name);
    }

    public void bind(GuestModel guest) {
        this.mTextView.setText(guest.getName());
    }
}
