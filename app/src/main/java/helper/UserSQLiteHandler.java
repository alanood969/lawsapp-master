package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.taha.laws.data.Users;

import java.util.HashMap;

//created by mohammad for handling data coming from php api
//to be stored in SQLite database.
public class UserSQLiteHandler extends SQLiteOpenHelper{
    private static final String TAG = UserSQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_AGE = "age";
    private static final String KEY_REGISTER_DATE = "register_date";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_IMAGE = "image";

    public UserSQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_FIRST_NAME + " TEXT," + KEY_LAST_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_PHONE + " TEXT,"
                + KEY_AGE + " TEXT,"
                + KEY_REGISTER_DATE + " TEXT,"
                + KEY_LONGITUDE + " TEXT," + KEY_LATITUDE + " TEXT,"
                + KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String user_id, String username, String fname, String lname, String email, String phone, String age, String registerdate, String longitude, String latitude, String image) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, user_id); // ID
        values.put(KEY_USERNAME, username); // UserName
        values.put(KEY_FIRST_NAME, fname); // FirstName
        values.put(KEY_LAST_NAME, lname); // LastName
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_PHONE, phone); // Phone
        values.put(KEY_AGE, age); // Age
        values.put(KEY_REGISTER_DATE, registerdate); // Register Date
        values.put(KEY_LONGITUDE, longitude); // Longitude
        values.put(KEY_LATITUDE, latitude); // Latitude
        values.put(KEY_IMAGE, image); // Image

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public Users getUserDetails() {
        Users user = new Users();
        String selectQuery = "SELECT  * FROM " + TABLE_USER ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            user.setAge(cursor.getString(cursor.getColumnIndex(KEY_AGE)));
            user.setRegisterDate(cursor.getString(cursor.getColumnIndex(KEY_REGISTER_DATE)));
            user.setLongitude(cursor.getString(cursor.getColumnIndex(KEY_LONGITUDE)));
            user.setLatitude(cursor.getString(cursor.getColumnIndex(KEY_LATITUDE)));
            user.setImageId(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Recreate database; Delete all tables and create them again
     * */
    public void deleteUsers(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER,null , null );

        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
}
