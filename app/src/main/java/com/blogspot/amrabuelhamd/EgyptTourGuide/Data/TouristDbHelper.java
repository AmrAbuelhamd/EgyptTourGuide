package com.blogspot.amrabuelhamd.EgyptTourGuide.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Data.TouristContract.TouristEntry;

/**
 * Created by amro mohamed on 3/31/2018.
 */
//DATA TYPE OF COLUMNS AND CONSTRAINTS
public class TouristDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TouristDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "TouristsData.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link TouristDbHelper}.

     * @param context of the app
     */
    public TouristDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_TOURST_TABLE =  "CREATE TABLE " + TouristEntry.TABLE_NAME + " ("
                + TouristEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TouristEntry.COLUMN_TOURIST_FNAME + " TEXT NOT NULL, "
                + TouristEntry.COLUMN_TOURIST_LNAME + " TEXT NOT NULL, "
                + TouristEntry.COLUMN_TOURIST_EMAIL+ " TEXT, "
                + TouristEntry.COLUMN_TOURIST_PHONE+ " INTEGER, "
                + TouristEntry.COLUMN_TOURIST_CONTRY+ " TEXT, "
                + TouristEntry.COLUMN_TOURISTS_NUMBER+ " INTEGER, "
                + TouristEntry.COLUMN_TOURIST_DURATION+ " INTEGER, "
                + TouristEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL,"
                + TouristEntry.COLUMN_BOOKED_HOTEL+ " TEXT, "
                + TouristEntry.COLUMN_GEVARNO_NAME+ " TEXT );";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TOURST_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}