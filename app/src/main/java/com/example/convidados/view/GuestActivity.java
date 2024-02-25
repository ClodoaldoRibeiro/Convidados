package com.example.convidados.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.convidados.R;
import com.example.convidados.constants.GuestConfirmation;
import com.example.convidados.constants.GuestConstants;
import com.example.convidados.model.GuestModel;
import com.example.convidados.viewmodel.GuestViewModel;

public class GuestActivity extends AppCompatActivity implements View.OnClickListener {
    private final ViewHolder mViewHolder = new ViewHolder();
    private GuestViewModel mGuestViewModel;
    private int mGuestId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        this.mGuestViewModel = new ViewModelProvider(this).get(GuestViewModel.class);

        this.mViewHolder.editName = findViewById(R.id.edit_name);
        this.mViewHolder.radioNotConfirmed = findViewById(R.id.radio_not_confirmed);
        this.mViewHolder.radioPresent = findViewById(R.id.radio_present);
        this.mViewHolder.radioAbsent = findViewById(R.id.radio_absent);
        this.mViewHolder.buttonSave = findViewById(R.id.button_save);

        this.setListeners();
        this.setObservers();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.guestId);
            mGuestViewModel.guestById(mGuestId);
        }
    }

    private void setListeners() {
        this.mViewHolder.buttonSave.setOnClickListener(this);
    }

    private void setObservers() {
        this.mGuestViewModel.guest.observe(this, new Observer<GuestModel>() {
            @Override
            public void onChanged(GuestModel guestModel) {
                mViewHolder.editName.setText(guestModel.getName());

                int conf = guestModel.getConfirmation();
                mViewHolder.radioNotConfirmed.setChecked(conf == GuestConfirmation.not_confirmation);
                mViewHolder.radioAbsent.setChecked(conf == GuestConfirmation.absent);
                mViewHolder.radioPresent.setChecked(conf == GuestConfirmation.present);

            }
        });

        this.mGuestViewModel.feedback.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    String str = "";

                    if (mGuestId == 0) {
                        str = "Convidado inserido com sucesso";
                    } else {
                        str = "Convidado atualizado com sucesso";
                    }

                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_save) {
            handleSave();
        }
    }

    private void handleSave() {
        String name = mViewHolder.editName.getText().toString();
        int confirmation = getStatusConfirmation();
        GuestModel guestModel = new GuestModel(mGuestId, name, confirmation);

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
        EditText editName;
        RadioButton radioNotConfirmed;
        RadioButton radioPresent;
        RadioButton radioAbsent;
        Button buttonSave;
    }
}