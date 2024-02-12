package com.example.convidados.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.convidados.databinding.FragmentPresentBinding;
import com.example.convidados.viewmodel.PresentViewModel;

public class PresentFragment extends Fragment {

    private FragmentPresentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PresentViewModel presentViewModel =
                new ViewModelProvider(this).get(PresentViewModel.class);

        binding = FragmentPresentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPresent;
        presentViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}