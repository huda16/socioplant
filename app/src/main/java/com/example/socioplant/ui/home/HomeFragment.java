package com.example.socioplant.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socioplant.GridPlantAdapter;
import com.example.socioplant.LoginActivity;
import com.example.socioplant.R;
import com.example.socioplant.databinding.FragmentHomeBinding;
import com.example.socioplant.models.Plant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvPlants;
    private ArrayList<Plant> list;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private String DATABASE_URL;
    private String userId;

    DatabaseReference plant;
    FirebaseUser user;

    private void showSelectedPlant(Plant plant) {
        Toast.makeText(getActivity(), "Kamu memilih " + plant.getName(), Toast.LENGTH_SHORT).show();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvPlants = view.findViewById(R.id.rvPlants);
        rvPlants.setHasFixedSize(true);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        DATABASE_URL = getResources().getString(R.string.database_url);
        plant = FirebaseDatabase.getInstance(DATABASE_URL)
                .getReference("plants");

        list = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

        } else {
            // No user is signed in
            Intent moveIntent = new Intent(getActivity(), LoginActivity.class);
            startActivity(moveIntent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        plant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Plant listArray = postSnapshot.getValue(Plant.class);
                    list.add(listArray);
                }
                GridPlantAdapter gridPlantAdapter = new GridPlantAdapter(list);
                rvPlants.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rvPlants.setAdapter(gridPlantAdapter);
                gridPlantAdapter.setOnItemClickCallback(new GridPlantAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Plant data) {
                        showSelectedPlant(data);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}