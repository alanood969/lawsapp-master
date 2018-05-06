package com.example.taha.laws.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.taha.laws.R;

public class PlantsEntity extends SQLiteOpenHelper {


    private static final String DB_NAME = "LAWZDB"; // the name of our database
    private static final int DB_VERSION = 2; //for database versions update
    private static final String TABLE = "plant";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PLANT_NAME = "NAME";
    private static final String COLUMN_PLANT_SOIL_PH = "SOIL_PH";
    private static final String COLUMN_PLANT_ZONE = "ZONE";
    private static final String COLUMN_PLANT_IMAGE = "IMAGE_RESOURCE_ID";
    private static final String COLUMN_PLANT_SUN = "SUN";
    private static final String COLUMN_PLANT_HARVEST_DAYS = "HARVEST_DAYS";
    private static final String COLUMN_PLANT_WATERING_DAYS = "WATERING_DAYS";

    public PlantsEntity(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE +"("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PLANT_NAME+" TEXT, "
                + COLUMN_PLANT_SOIL_PH+" TEXT, "
                + COLUMN_PLANT_ZONE  +" TEXT ,"
                + COLUMN_PLANT_WATERING_DAYS  +" TEXT ,"
                + COLUMN_PLANT_HARVEST_DAYS + " TEXT ,"
                + COLUMN_PLANT_SUN  +" TEXT ,"
                + COLUMN_PLANT_IMAGE  +" INTEGER );");


        insertPlant(db, "Tomato", "7",Integer.toString(R.string.zone_out),"5","75","Full sun", R.drawable.tomato);
        insertPlant(db, "Potato", "6",Integer.toString(R.string.zone_out),"3","90","Full sun", R.drawable.potato);
        insertPlant(db, "Lettuce", "7",Integer.toString(R.string.zone_in),"3","50","Shadow", R.drawable.lettuce);
        insertPlant(db, "Coriander", "7.2",Integer.toString(R.string.zone_out),"2","32","Full sun", R.drawable.coriander);
        insertPlant(db, "Radishes", "6.5",Integer.toString(R.string.zone_out),"3","30","Full sun", R.drawable.radishes);

    }

    private static void insertPlant(SQLiteDatabase db,
                                    String name,
                                    String soilPH,
                                    String zone,
                                    String wateringDays,
                                    String harvestedDays,
                                    String sun,
                                    int resourceId) {
        //container for plants data
        ContentValues plantValues = new ContentValues();
        plantValues.put(COLUMN_PLANT_NAME, name);
        plantValues.put(COLUMN_PLANT_SOIL_PH , soilPH);
        plantValues.put(COLUMN_PLANT_ZONE , zone);
        plantValues.put(COLUMN_PLANT_WATERING_DAYS, wateringDays);
        plantValues.put(COLUMN_PLANT_HARVEST_DAYS , harvestedDays);
        plantValues.put(COLUMN_PLANT_SUN ,sun);
        plantValues.put(COLUMN_PLANT_IMAGE, resourceId);
        db.insert(TABLE, null, plantValues);
        db.close();
    }


    // return array of plants objects
    public plants[] plantsList(){
        //Create a cursor
        SQLiteDatabase db = this.getReadableDatabase();
        // select table colomns
        Cursor cursor = db.query("PLANT",
                new String[]{COLUMN_ID,COLUMN_PLANT_NAME, COLUMN_PLANT_SOIL_PH, COLUMN_PLANT_ZONE, COLUMN_PLANT_WATERING_DAYS, COLUMN_PLANT_HARVEST_DAYS, COLUMN_PLANT_SUN, COLUMN_PLANT_IMAGE},
                null, null, COLUMN_ID, null, null);
        plants[] lawzPlants = new plants[5] ;
        plants p ;
        if (cursor.moveToFirst()) {
            int i = 0 ;
            do {
                p = new plants();
                p.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                p.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_NAME)));
                p.setSoilPh(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_SOIL_PH)));
                p.setZone(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_ZONE)));
                p.setWateringDays(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_WATERING_DAYS)));
                p.setHarvestDays(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_HARVEST_DAYS)));
                p.setSun(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_SUN)));
                p.setImageResourceId(cursor.getInt(cursor.getColumnIndex(COLUMN_PLANT_IMAGE)));
                lawzPlants[i] = p ;
                i++;
                } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
     return lawzPlants;
    }

    public plants getPlant(int _id)
    {
        plants plant = new plants();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query (TABLE,
                new String[]{COLUMN_ID,COLUMN_PLANT_NAME, COLUMN_PLANT_SOIL_PH, COLUMN_PLANT_ZONE, COLUMN_PLANT_WATERING_DAYS, COLUMN_PLANT_HARVEST_DAYS, COLUMN_PLANT_SUN, COLUMN_PLANT_IMAGE},
                "_id = ?",
                new String[] {Integer.toString(_id)},
                null, null, null);
        plants p ;
        p = new plants();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            p.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            p.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_NAME)));
            p.setSoilPh(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_SOIL_PH)));
            p.setZone(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_ZONE)));
            p.setWateringDays(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_WATERING_DAYS)));
            p.setHarvestDays(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_HARVEST_DAYS)));
            p.setSun(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_SUN)));
            p.setImageResourceId(cursor.getInt(cursor.getColumnIndex(COLUMN_PLANT_IMAGE)));
        }
        cursor.close();
        db.close();
        return p;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        this.onCreate(db);
    }
}