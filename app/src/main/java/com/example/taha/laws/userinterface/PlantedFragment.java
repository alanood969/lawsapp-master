package com.example.taha.laws.userinterface;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taha.laws.R;
import com.example.taha.laws.data.Garden;
import com.example.taha.laws.data.GardenEntity;
import com.example.taha.laws.adapter.PlantedPlantsAdapter;
import com.example.taha.laws.data.PlantsEntity;
import com.example.taha.laws.data.plants;


import java.util.LinkedList;
import java.util.List;

import helper.UserSQLiteHandler;


public class PlantedFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_planted, container, false);
        UserSQLiteHandler currentUser = new UserSQLiteHandler(this.getContext());
        //binding data into views
        RecyclerView plantedPlantsRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_planted, container, false);
        GardenEntity myGarden = new GardenEntity(this.getContext());
        PlantsEntity plantsEntity = new PlantsEntity(this.getContext());
        List<Garden> curGarden =  myGarden.gardenPlantsList("1");
        List<Integer> plantIds = new LinkedList<>();
        List<plants> myPlantedPlants= new LinkedList<>();
        //get all planted plant ids
        for(int i = 0 ; i< curGarden.size() ; i++)
        {
            myPlantedPlants.add(plantsEntity.getPlant(curGarden.get(i).getPlantId())) ;
        }
        /*String[] plantedPlantsNames = new String[plantIds.size()];

        for (int i = 0; i < plantedPlantsNames.length; i++) {
            plantedPlantsNames[i] = plantsEntity.getPlant(plantIds.get(i)).getName() ;
        }
*/
        int[] plantsImages = new int[plantIds.size()];
        for (int i = 0; i < plantsImages.length; i++) {
            plantsImages[i] = myPlantedPlants.get(i).getImageResourceId();
        }
        PlantedPlantsAdapter adapter = new PlantedPlantsAdapter(this.getContext() , myPlantedPlants);
        plantedPlantsRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        plantedPlantsRecycler.setLayoutManager(layoutManager);
        return plantedPlantsRecycler;
    }

 public void harvestPlant(View view)
 {

 }
}
