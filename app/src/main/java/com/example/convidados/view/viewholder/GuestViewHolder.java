package com.example.convidados.view.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.model.GuestModel;
import com.example.convidados.view.listener.OnListClick;

public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    private Context mContext;

    public GuestViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mTextView = itemView.findViewById(R.id.text_name);
        this.mContext = itemView.getContext();
    }

    public void bind(GuestModel guest, OnListClick listener) {
        this.mTextView.setText(guest.getName());

        this.mTextView.setOnClickListener(view -> listener.onClick(guest.getId()));

        this.mTextView.setOnLongClickListener(view -> {
            new AlertDialog.Builder(mContext).setTitle("Remove Guest").setMessage("Delete on Guest?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    listener.onDelete(guest.getId());
                }
            }).setNegativeButton("No", null).show();

            return false;
        });
    }
}
