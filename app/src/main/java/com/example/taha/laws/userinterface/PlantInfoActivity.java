package com.example.taha.laws.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taha.laws.data.Garden;
import com.example.taha.laws.data.GardenEntity;
import com.example.taha.laws.data.PlantsEntity;
import com.example.taha.laws.R;
import com.example.taha.laws.data.Users;
import com.example.taha.laws.data.plants;


import java.util.Calendar;
import java.util.Date;

import helper.UserSQLiteHandler;

public class PlantInfoActivity extends AppCompatActivity {

    private GardenEntity gardenEntity = new GardenEntity(this);
    public static String EXTRA_PLANT_ID = "plantId";
    private int plantId;
    private Garden gn = new Garden();
    private UserSQLiteHandler  currentUser = new UserSQLiteHandler(this);
    private Users user = new Users();

    public PlantInfoActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);
        plantId = Integer.parseInt(getIntent().getExtras().get(EXTRA_PLANT_ID).toString());
        PlantsEntity plantsEntity = new PlantsEntity(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        plants lawzPlant = plantsEntity.getPlant(plantId);
        String plantName = lawzPlant.getName();
    //    textView = findViewById(R.id.pNameTV);
      //  textView.setText(plantName);
        int plantImage = lawzPlant.getImageResourceId();
       ImageView imageView = findViewById(R.id.plant_image_view);
       imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
       imageView.setImageDrawable(ContextCompat.getDrawable(this, plantImage));

        TextView textView = findViewById(R.id.phTextView);
        textView.setText(lawzPlant.getSoilPh());

        textView = findViewById(R.id.wDaysTextView);
        textView.setText(lawzPlant.getWateringDays());

        textView = findViewById(R.id.HDaysTextView);
        textView.setText(lawzPlant.getHarvestDays());

        textView = findViewById(R.id.sunTextView);
        textView.setText(lawzPlant.getSun());

        textView = findViewById(R.id.zoneTextView);
        textView.setText(lawzPlant.getZone());
        }

    public void onClickDone(View view) {
        CharSequence text = "Your order has been updated";
        int duration = Snackbar.LENGTH_SHORT;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
        snackbar.setAction("Undo",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(PlantInfoActivity.this, "Undone!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
        snackbar.show();
    }
    public void addToGarden(View view)
    {
        Date c = Calendar.getInstance().getTime();
        gn.setOwnerId(user.getID());
        gn.setPlantId(Integer.parseInt(getIntent().getExtras().get(EXTRA_PLANT_ID).toString()) );
        gn.setPlantedDate(c);
        gn.setPlantStatus("1");
        user = currentUser.getUserDetails();
        gardenEntity.addPlantToGarden(gn);
        Intent intent = new Intent(this , MyGardenActivity.class);
        intent.putExtra("userId" , user.getID());
        startActivity(intent);
    }
}
