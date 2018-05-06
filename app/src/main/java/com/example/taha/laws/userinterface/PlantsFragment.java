package com.example.taha.laws.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taha.laws.data.PlantsEntity;
import com.example.taha.laws.adapter.PlantAdapter;
import com.example.taha.laws.R;
import com.example.taha.laws.data.plants;


public class PlantsFragment extends Fragment {

    public PlantsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get plants from database
        PlantsEntity plantsEntity = new PlantsEntity(this.getContext());
        final plants[] lawzPlants = plantsEntity.plantsList();
      //  final plants[] lawzPlants = plants.myplants;
        RecyclerView plantsRecycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_plants, container, false);

        PlantAdapter adapter = new PlantAdapter(this.getContext(), lawzPlants );
        plantsRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        plantsRecycler.setLayoutManager(layoutManager);

        // card view click
        adapter.setListener(new PlantAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PlantInfoActivity.class);
                intent.putExtra(PlantInfoActivity.EXTRA_PLANT_ID,lawzPlants[position].getID());
                getActivity().startActivity(intent);
            }
        });
        return plantsRecycler;
    }
}