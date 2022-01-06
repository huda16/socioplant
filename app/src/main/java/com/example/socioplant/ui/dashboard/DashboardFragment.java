package com.example.socioplant.ui.dashboard;

import android.os.Bundle;
import android.text.Html;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socioplant.ListPlantAdapter;
import com.example.socioplant.Plant;
import com.example.socioplant.PlantsData;
import com.example.socioplant.R;
import com.example.socioplant.databinding.FragmentDashboardBinding;
import com.example.socioplant.databinding.FragmentHomeBinding;
import com.example.socioplant.ui.home.HomeViewModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerView rvPlants;
    private ArrayList<Plant> list = new ArrayList<>();
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private void showSelectedPlant(Plant plant) {
        Toast.makeText(getActivity(), "Kamu memilih " + plant.getName(), Toast.LENGTH_SHORT).show();
    }

    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        rvPlants = view.findViewById(R.id.rvPlants);
        rvPlants.setHasFixedSize(true);
        list.addAll(PlantsData.getListData());
        rvPlants.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListPlantAdapter listPlantAdapter = new ListPlantAdapter(list);
        rvPlants.setAdapter(listPlantAdapter);

        listPlantAdapter.setOnItemClickCallback(new ListPlantAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Plant data) {
                showSelectedPlant(data);
            }
        });

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
}