package com.example.taha.laws.userinterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taha.laws.adapter.HarvestedPlantsAdapter;
import com.example.taha.laws.R;
import com.example.taha.laws.data.GardenEntity;
import com.example.taha.laws.data.plants;

public class HarvestedFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_harvested, container, false);
        RecyclerView harvestedPlantsRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_harvested, container, false);
        GardenEntity myGarden = new GardenEntity(this.getContext());

        String[] plantedPlantsNames = new String[plants.myplants.length];
        for (int i = 0; i < plantedPlantsNames.length; i++) {
            plantedPlantsNames[i] = plants.myplants[i].getName();
        }

        int[] plantsImages = new int[plants.myplants.length];
        for (int i = 0; i < plantsImages.length; i++) {
            plantsImages[i] =/* plants.myplants[i].getImageResourceId()*/R.drawable.tomato;
        }

        HarvestedPlantsAdapter adapter = new HarvestedPlantsAdapter(plantedPlantsNames, plantsImages);
        harvestedPlantsRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        harvestedPlantsRecycler.setLayoutManager(layoutManager);
        return harvestedPlantsRecycler;
    }

}
