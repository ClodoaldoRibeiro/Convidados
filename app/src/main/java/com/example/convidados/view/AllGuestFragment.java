package com.example.convidados.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.convidados.databinding.FragmentAllGuestBinding;
import com.example.convidados.viewmodel.AllGuestViewModel;

public class AllGuestFragment extends Fragment {

    private FragmentAllGuestBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AllGuestViewModel allGuestViewModel =
                new ViewModelProvider(this).get(AllGuestViewModel.class);

        binding = FragmentAllGuestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        allGuestViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}