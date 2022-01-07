package com.example.socioplant.ui.dashboard;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socioplant.GridPlantAdapter;
import com.example.socioplant.ListPlantAdapter;
import com.example.socioplant.R;
import com.example.socioplant.databinding.FragmentDashboardBinding;
import com.example.socioplant.models.Myplants;
import com.example.socioplant.models.Plant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerView rvPlants;
    private ArrayList<Myplants> list;
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private String DATABASE_URL;
    private String userId;

    DatabaseReference plant;
    FirebaseUser user;

    private void showSelectedMyPlant(Myplants myplants) {
        Toast.makeText(getActivity(), "Kamu memilih " + myplants.getUserId(), Toast.LENGTH_SHORT).show();
    }

    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvPlants = view.findViewById(R.id.rvPlants);
        rvPlants.setHasFixedSize(true);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        DATABASE_URL = getResources().getString(R.string.database_url);
        plant = FirebaseDatabase.getInstance(DATABASE_URL)
                .getReference("myplants")
                .child(userId);

        list = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        textView = (TextView) view.findViewById(R.id.textView);
        String text = "<font color=#000000>My</font> <font color=#519259>plants!</font>";
        textView.setText(Html.fromHtml(text));
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
                    Myplants listArray = postSnapshot.getValue(Myplants.class);
                    list.add(listArray);
                }
                ListPlantAdapter listPlantAdapter = new ListPlantAdapter(list);
                rvPlants.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvPlants.setAdapter(listPlantAdapter);
                listPlantAdapter.setOnItemClickCallback(new ListPlantAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Myplants data) {
                        showSelectedMyPlant(data);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}