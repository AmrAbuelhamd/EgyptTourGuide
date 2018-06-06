package com.blogspot.amrabuelhamd.EgyptTourGuide.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by amro mohamed on 3/31/2018.
 */
//CREATE DATABASE COLUMNS
public final class TouristContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
   private TouristContract(){}
    public static final String CONTENT_AUTHORITY = "com.blogspot.amrabuelhamd.EgyptTourGuide";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TOURIST = "EgyptTourGuide";

    public static final class TouristEntry implements BaseColumns {

        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TOURIST);



        /** Name of database table for pets */
        public final static String TABLE_NAME = "tourists";

        /**
         * Unique ID number for the tourist (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * FName of the tourist.
         *
         * Type: TEXT
         */
        public final static String COLUMN_TOURIST_FNAME ="f_name";

        /**
         * lName of the tourist.
         *
         * Type: TEXT
         */
        public final static String COLUMN_TOURIST_LNAME = "l_name";

        /**
         * email of the tourist.
         *
         * Type: TEXT
         */
        public final static String COLUMN_TOURIST_EMAIL = "email";
        /**
         * phone of the tourist.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_TOURIST_PHONE= "phone";
        /**
         * country of the tourist.
         *
         * Type: TEXT
         */
        public final static String COLUMN_TOURIST_CONTRY = "country";
        /**
         * number of the tourists.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_TOURISTS_NUMBER = "number";
        /**
         * duration of the tourist.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_TOURIST_DURATION= "duration";
        /**
         * Gender of the tourist.
         *
         * The only possible values are {@link #GENDER_MALE},{@link #GENDER_FEMALE}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PET_GENDER = "gender";


        /**
         * Possible values for the gender of the tourist.
         */
        public static final int GENDER_MALE = 0;
        public static final int GENDER_FEMALE = 1;

        /**
         * Returns whether or not the given gender is  {@link #GENDER_MALE},{@link #GENDER_FEMALE}.
         */
        public static boolean isValidGender(int gender) {
            if (gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;

    }        /**
         * booked hotel of the tourist.
         *
         * Type: TEXT
         */
        public final static String COLUMN_BOOKED_HOTEL= "hotel";
        /**
         * gaverno name  of the tourist.
         *
         * Type: TEXT
         */
        public final static String COLUMN_GEVARNO_NAME= "gaverno_name";

    }

}
