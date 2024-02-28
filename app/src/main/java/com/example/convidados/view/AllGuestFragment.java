package com.example.convidados.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.constants.GuestConstants;
import com.example.convidados.databinding.FragmentAllGuestBinding;
import com.example.convidados.model.FeedbackModel;
import com.example.convidados.model.GuestModel;
import com.example.convidados.view.adapter.GuestAdapter;
import com.example.convidados.view.listener.OnListClick;
import com.example.convidados.viewmodel.AllGuestViewModel;

import java.util.List;

public class AllGuestFragment extends Fragment {
    private FragmentAllGuestBinding binding;
    private AllGuestViewModel mViewModel;
    private final ViewHolder mViewHolder = new ViewHolder();
    private final GuestAdapter mGuestAdapter = new GuestAdapter();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = new ViewModelProvider(this).get(AllGuestViewModel.class);

        binding = FragmentAllGuestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.mViewHolder.recyclerView = root.findViewById(R.id.recycler_view_all_guest);
        this.mViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mViewHolder.recyclerView.setAdapter(this.mGuestAdapter);

        OnListClick onListClick = new OnListClick() {
            @Override
            public void onClick(int guestId) {
                Bundle bundle = new Bundle();
                bundle.putInt(GuestConstants.guestId, guestId);

                Intent intent = new Intent(getContext(), GuestActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }

            @Override
            public void onDelete(int guestId) {
                mViewModel.delete(guestId);
            }
        };

        this.mGuestAdapter.attachListener(onListClick);
        this.observers();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mViewModel.getAll();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void observers() {
        this.mViewModel.guestModel.observe(getViewLifecycleOwner(), new Observer<List<GuestModel>>() {
            @Override
            public void onChanged(List<GuestModel> guestModels) {
                mGuestAdapter.attachList(guestModels);
            }
        });

        this.mViewModel.feedback.observe(getViewLifecycleOwner(), new Observer<FeedbackModel>() {
            @Override
            public void onChanged(FeedbackModel feedbackModel) {
                Toast.makeText(getContext(), feedbackModel.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class ViewHolder {
        RecyclerView recyclerView;
    }
}