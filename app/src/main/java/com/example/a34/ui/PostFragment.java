package com.example.a34.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a34.R;
import com.example.a34.data.MockerModel;
import com.example.a34.databinding.FragmentPostBinding;
import com.example.a34.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostFragment extends Fragment {
    private FragmentPostBinding binding;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            MockerModel model;
            model = (MockerModel) getArguments().getSerializable("ff_key");
            Log.d("tag", model.getTitle());

            binding.etTitle.setText(model.getTitle());
            binding.etContent.setText(model.getContent());
            binding.etGroup.setText(String.valueOf(model.getGroup()));
            binding.etUser.setText(String.valueOf(model.getUser()));
            id = model.getId();
            Log.d("tag", model.getId().toString());
        }

        binding.btnUpgrade.setOnClickListener(view1 -> {
            RetrofitClient.getApi().update(String.valueOf(id+""), new MockerModel(binding.etTitle.getText().toString().trim(),
                    binding.etContent.getText().toString().trim(),
                    Integer.valueOf(binding.etGroup.getText().toString().trim()),
                    Integer.valueOf(binding.etUser.getText().toString().trim()))).enqueue(new Callback<MockerModel>() {
                @Override
                public void onResponse(Call<MockerModel> call, Response<MockerModel> response) {
                    open();
                }

                @Override
                public void onFailure(Call<MockerModel> call, Throwable t) {

                }
            });
        });

        binding.btnCreate.setOnClickListener(view1 -> {
            RetrofitClient.getApi().createMocker(new MockerModel(binding.etTitle.getText().toString().trim(),
                    binding.etContent.getText().toString().trim(),
                    Integer.valueOf(binding.etGroup.getText().toString().trim()),
                    Integer.valueOf(binding.etUser.getText().toString().trim()))).enqueue(new Callback<MockerModel>() {
                @Override
                public void onResponse(Call<MockerModel> call, Response<MockerModel> response) {
                    open();
                }

                @Override
                public void onFailure(Call<MockerModel> call, Throwable t) {

                }
            });
        });

    }

    private void open() {

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment);
    }
}