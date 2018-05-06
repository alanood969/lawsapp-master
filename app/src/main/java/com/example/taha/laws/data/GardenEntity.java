package com.example.taha.laws.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class GardenEntity extends SQLiteOpenHelper {

    private static final String DB_NAME = "lDB"; // the name of our database
    private static final int DB_VERSION = 1; //for database versions update
    private static final String TABLE = "GARDEN";
    private static final String COLUMN_GARDEN_ID = "GARDEN_id";
    private static final String COLUMN_USER_ID = "USER_ID";
    private static final String COLUMN_PLANT_ID = "PLANT_ID";
    private static final String COLUMN_PLANTED_DATE = "PLANT_DATE";
    private static final String COLUMN_IS_WATERED = "IS_WATERED";
    private static final String COLUMN_PLANT_STATUS = "STATUS";

    public GardenEntity(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+ TABLE +" ( "
                    + COLUMN_GARDEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USER_ID+" INTEGER, "
                    + COLUMN_PLANT_ID  +" INTEGER ,"
                    + COLUMN_PLANTED_DATE  +" TEXT ,"
                    + COLUMN_IS_WATERED + " Text ,"
                    + COLUMN_PLANT_STATUS  +" TEXT );");
    }


        /**create record**/
        public void addPlantToGarden(Garden garden) {
        SQLiteDatabase db = this.getWritableDatabase();
        //container for plants data
        ContentValues gardenValues = new ContentValues();
        gardenValues.put(COLUMN_USER_ID , garden.getOwnerId());
        gardenValues.put(COLUMN_PLANT_ID , garden.getPlantId());
        gardenValues.put(COLUMN_PLANTED_DATE, garden.getPlantedDate().toString());
        gardenValues.put(COLUMN_IS_WATERED , (garden.getPlantIsWatered()));
        gardenValues.put(COLUMN_PLANT_STATUS , garden.getPlantStatus());
        db.insert(TABLE, null, gardenValues);
        db.close();
    }

    public void updatePlantStatus(Garden garden)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues plantValues = new ContentValues();
        //Zero means planted and 1 means harvested
        plantValues.put(COLUMN_PLANT_STATUS, "1" );
             db.update(TABLE,
                    plantValues,
                    COLUMN_USER_ID +"= ?",
                    new String[]{Integer.toString(garden.getOwnerId())});
        }

    public void removePlant(Garden garden)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE,
                COLUMN_USER_ID + " = ?",
                new String[] {Integer.toString(garden.getOwnerId())});
    }



    public List<Garden> gardenPlantsList(String Status ) {

        List<Garden> plantsLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[] {COLUMN_GARDEN_ID, COLUMN_USER_ID, COLUMN_PLANT_ID ,COLUMN_PLANTED_DATE,COLUMN_IS_WATERED},
                COLUMN_PLANT_STATUS +" = ? ",
                new String[] {Status},
                null, null, null);
        Garden garden;
        if (cursor.moveToFirst()) {
            do {
                garden = new Garden();

                garden.setPlantId(cursor.getInt(cursor.getColumnIndex(COLUMN_GARDEN_ID)));
                plantsLinkedList.add(garden);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return plantsLinkedList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE);
            this.onCreate(db);
    }
}
