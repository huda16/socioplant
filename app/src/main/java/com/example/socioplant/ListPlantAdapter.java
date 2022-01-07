package com.example.socioplant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.socioplant.models.Myplants;
import com.example.socioplant.models.Plant;

import java.util.ArrayList;

public class ListPlantAdapter extends RecyclerView.Adapter<ListPlantAdapter.ListViewHolder> {
    private ArrayList<Myplants> listPlant;
    public ListPlantAdapter(ArrayList<Myplants> list) {
        this.listPlant = list;
    }

    private ListPlantAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(ListPlantAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListPlantAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_plant, parent, false);
        return new ListPlantAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListPlantAdapter.ListViewHolder holder, int position) {
        Myplants myplant = listPlant.get(position);

        Glide.with(holder.itemView.getContext())
                .load(listPlant.get(position).getPhoto())
                .apply(new RequestOptions())
                .into(holder.imgPhoto);

        holder.tvName.setText(myplant.getName());

        holder.tvType.setText(myplant.getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPlant.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPlant.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName,tvType;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imageItem);
            tvName = itemView.findViewById(R.id.nameItem);
            tvType = itemView.findViewById(R.id.typeItem);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Myplants data);
    }
}
