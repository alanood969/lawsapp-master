package com.example.taha.laws.data;

import com.example.taha.laws.R;

public class plants {

    private int ID ;
    private String name;
    private int imageResourceId;
    private String plantInfo;
    private String zone;
    private String soilPh;
    private String wateringDays;
    private String harvestDays;
    private String sun;



    public plants(plants plant) {
        this.ID = plant.ID;
        this.name = plant.name;
        this.soilPh = plant.soilPh;
        this.zone = plant.zone;
        this.wateringDays = plant.wateringDays;
        this.harvestDays = plant.harvestDays;
        this.sun = plant.sun;
        this.imageResourceId = plant.imageResourceId;
    }


    public plants(int ID, String name, int imageResourceId, String plantInfo, String zone, String soilPh, String wateringDays, String harvestDays, String sun) {
        this.ID = ID;
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.plantInfo = plantInfo;
        this.zone = zone;
        this.soilPh = soilPh;
        this.wateringDays = wateringDays;
        this.harvestDays = harvestDays;
        this.sun = sun;
    }

    private plants(int id ,String name, int imageResourceId, String plantInfo) {
        this.ID = id ;
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.plantInfo = plantInfo;
    }



    public plants() {
    }

    public static final plants[] myplants = {
            new plants(1,"tomato", R.drawable.tomato, "Some info !"),
            new plants(2,"potato", R.drawable.potato, "Some info !"),
            new plants(3,"tomato", R.drawable.tomato, "Some info !"),
            new plants(4,"potato", R.drawable.potato, "Some info !"),
            new plants(5,"tomato", R.drawable.tomato, "Some info !"),
            new plants(0,"potato", R.drawable.potato, "Some info !"),

    };


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public void setPlantInfo(String plantInfo) {
        this.plantInfo = plantInfo;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setSoilPh(String soilPh) {
        this.soilPh = soilPh;
    }

    public void setWateringDays(String wateringDays) {
        this.wateringDays = wateringDays;
    }

    public void setHarvestDays(String harvestDays) {
        this.harvestDays = harvestDays;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }


    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getPlantInfo() {
        return zone + " " + soilPh;
    }

    public String getZone() {
        return zone;
    }

    public String getSoilPh() {
        return soilPh;
    }

    public String getWateringDays() {
        return wateringDays;
    }

    public String getHarvestDays() {
        return harvestDays;
    }

    public String getSun() {
        return sun;
    }
}
