package com.example.convidados.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.convidados.R;
import com.example.convidados.constants.GuestConfirmation;
import com.example.convidados.model.GuestModel;
import com.example.convidados.viewmodel.GuestViewModel;

public class GuestActivity extends AppCompatActivity implements View.OnClickListener {
    private final ViewHolder mViewHolder = new ViewHolder();
    private GuestViewModel mGuestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        this.mGuestViewModel = new ViewModelProvider(this).get(GuestViewModel.class);

        this.mViewHolder.editText = findViewById(R.id.edit_name);
        this.mViewHolder.radioNotConfirmed = findViewById(R.id.radio_not_confirmed);
        this.mViewHolder.radioPresent = findViewById(R.id.radio_present);
        this.mViewHolder.radioAbsent = findViewById(R.id.radio_absent);
        this.mViewHolder.buttonSave = findViewById(R.id.button_save);

        this.setListeners();
    }

    private void setListeners() {
        this.mViewHolder.buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_save) {
            handleSave();
        }
    }

    private void handleSave() {
        String name = mViewHolder.editText.getText().toString();
        int confirmation = getStatusConfirmation();
        GuestModel guestModel = new GuestModel(name, confirmation);

        mGuestViewModel.save(guestModel);
    }

    int getStatusConfirmation() {
        if (mViewHolder.radioPresent.isChecked()) {
            return GuestConfirmation.present;
        }

        if (mViewHolder.radioAbsent.isChecked()) {
            return GuestConfirmation.absent;
        }

        return GuestConfirmation.not_confirmation;
    }

    private static class ViewHolder {
        EditText editText;
        RadioButton radioNotConfirmed;
        RadioButton radioPresent;
        RadioButton radioAbsent;
        Button buttonSave;
    }
}