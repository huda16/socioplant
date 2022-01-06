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

import java.util.ArrayList;

public class GridPlantAdapter extends RecyclerView.Adapter<GridPlantAdapter.GridViewHolder> {

    private ArrayList<Plant> listPlant;
    public GridPlantAdapter(ArrayList<Plant> list) {
        this.listPlant = list;
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_plant, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Plant plant = listPlant.get(position);

        Glide.with(holder.itemView.getContext())
                .load(listPlant.get(position).getPhoto())
                .apply(new RequestOptions())
                .into(holder.imgPhoto);

        holder.tvName.setText(plant.getName());

        holder.tvType.setText(plant.getType());

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

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName,tvType;

        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imageItem);
            tvName = itemView.findViewById(R.id.nameItem);
            tvType = itemView.findViewById(R.id.typeItem);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Plant data);
    }
}
