package com.example.convidados.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.convidados.databinding.FragmentAbsentBinding;
import com.example.convidados.viewmodel.AbsentViewModel;

public class AbsentFragment extends Fragment {

    private FragmentAbsentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AbsentViewModel absentViewModel =
                new ViewModelProvider(this).get(AbsentViewModel.class);

        binding = FragmentAbsentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        absentViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}