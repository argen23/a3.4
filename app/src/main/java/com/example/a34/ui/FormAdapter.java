package com.example.a34.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a34.data.MockerModel;
import com.example.a34.databinding.ItemFormBinding;
import com.example.a34.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    private List<MockerModel> list = new ArrayList<>();
    private OnItemCLickListener onItemCLickListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFormBinding binding = ItemFormBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(ArrayList<MockerModel> body) {
        list.addAll(body);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFormBinding binding;
        public ViewHolder(@NonNull ItemFormBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(MockerModel model) {
            binding.tvTitle.setText(model.getTitle());
            binding.tvContent.setText(model.getContent());
            binding.tvUser.setText(String.valueOf(model.getUser()));
            binding.tvGroup.setText(String.valueOf(model.getGroup()));
            itemView.setOnClickListener(view -> {
                onItemCLickListener.clickItem(model);
            });

            itemView.setOnLongClickListener(view -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext())
                        .setMessage("You serious?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                RetrofitClient.getApi().deleteMockerModel(model.getId()).enqueue(new Callback<MockerModel>() {
                                    @Override
                                    public void onResponse(Call<MockerModel> call, Response<MockerModel> response) {

                                        list.remove(getAdapterPosition());
                                        notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(Call<MockerModel> call, Throwable t) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("no",null);
                alert.create().show();
                return true;
            });

        }
    }
    public interface OnItemCLickListener{
        void clickItem(MockerModel model);
    }

}
