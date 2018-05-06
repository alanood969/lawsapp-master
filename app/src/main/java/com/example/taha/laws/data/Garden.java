package com.example.taha.laws.data;

import java.util.Date;

public class Garden {
    private int gardenId;
    private int ownerId;
    private int plantId;
    private String plantIsWatered;
    private Date plantedDate;
    private String plantStatus;

    public Garden(int gardenId, int ownerId, int plantId, Date plantedDate) {
        this.gardenId = gardenId;
        this.ownerId = ownerId;
        this.plantId = plantId;
        this.plantIsWatered = "1";
        this.plantedDate = plantedDate;
        this.plantStatus = "planted";

    }

    public Garden() {
        plantIsWatered = "1";
        plantStatus = "planted";
    }

    public int getGardenId() {
        return gardenId;
    }

    public void setGardenId(int gardenId) {
        this.gardenId = gardenId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantIsWatered() {
        return plantIsWatered;
    }

    public void setPlantIsWatered(String plantIsWatered) {
        this.plantIsWatered = plantIsWatered;
    }

    public Date getPlantedDate() {
        return plantedDate;
    }

    public void setPlantedDate(Date plantedDate) {
        this.plantedDate = plantedDate;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(String plantStatus) { this.plantStatus = plantStatus; }
}