package com.example.socioplant;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.socioplant.models.Myplants;
import com.example.socioplant.models.Plant;
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

public class ListPlantAdapter extends RecyclerView.Adapter<ListPlantAdapter.ListViewHolder> {
    private static final String TAG = "ListPlantAdapter";
    private ArrayList<Myplants> listPlant;
    private String DATABASE_URL;
    private String userId;
    private String id;
    private String name;
    private String type;
    private String image;
    DatabaseReference myplants;
    FirebaseUser user;

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
        Myplants myPlant = listPlant.get(position);
        user = FirebaseAuth.getInstance().getCurrentUser();
        id = myPlant.getPlantId();
        userId = user.getUid();
        image = myPlant.getPhoto();

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://socioplant-rpl.appspot.com/");
        StorageReference storageRef = storage.getReference();
        final StorageReference imgRef = storageRef.child("images/" + image);
        final long ONE_MEGABYTE = 1024*1024;

        Glide.with(holder.itemView.getContext())
//                .load(listPlant.get(position).getPhoto())
                .load(imgRef)
                .apply(new RequestOptions())
                .into(holder.imgPhoto);
        Log.d(TAG, name);
        holder.tvName.setText(myPlant.getName());

        holder.tvType.setText(myPlant.getType());

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
