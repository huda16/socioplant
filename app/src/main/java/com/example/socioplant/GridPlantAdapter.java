package com.example.socioplant;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.socioplant.models.Plant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class GridPlantAdapter extends RecyclerView.Adapter<GridPlantAdapter.GridViewHolder> {

    private static final String TAG = "GridPlantAdapter";
    private ArrayList<Plant> listPlant;
    private String DATABASE_URL;
    private String userId;
    DatabaseReference userAttribute, dateAttribute;
    FirebaseUser user;
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
        String image = plant.getPhoto();
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://socioplant-rpl.appspot.com/");
        StorageReference storageRef = storage.getReference();
        final StorageReference imgRef = storageRef.child("images/" + image);
        final long ONE_MEGABYTE = 1024*1024;
        String uri = "@drawable/";
//        int imageResource = holder.itemView.getContext().getResources().getIdentifier(uri+image.replace(".png",""), null, holder.itemView.getContext().getPackageName());
//        Drawable myImage = holder.itemView.getContext().getResources().getDrawable(imageResource);
//        String gambar = holder.imgPhoto.setImageDrawable(myImage);

        Glide.with(holder.itemView.getContext())
                .load(imgRef)
                .placeholder(R.drawable.bonsai)
                .into(holder.imgPhoto);
//        imgRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Glide.with(holder.itemView.getContext()).load(imgRef).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imgPhoto);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
        Log.d(TAG, image);
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
