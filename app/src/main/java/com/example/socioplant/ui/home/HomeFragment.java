package com.example.socioplant.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socioplant.GridPlantAdapter;
import com.example.socioplant.LoginActivity;
import com.example.socioplant.Plant;
import com.example.socioplant.PlantsData;
import com.example.socioplant.R;
import com.example.socioplant.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvPlants;
    private ArrayList<Plant> list = new ArrayList<>();
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private void showSelectedPlant(Plant plant) {
        Toast.makeText(getActivity(), "Kamu memilih " + plant.getName(), Toast.LENGTH_SHORT).show();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        list.addAll(PlantsData.getListData());
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvPlants = view.findViewById(R.id.rvPlants);
        rvPlants.setHasFixedSize(true);

        rvPlants.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        GridPlantAdapter gridPlantAdapter = new GridPlantAdapter(list);
        rvPlants.setAdapter(gridPlantAdapter);

        gridPlantAdapter.setOnItemClickCallback(new GridPlantAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Plant data) {
                showSelectedPlant(data);
            }
        });

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
}