package com.example.admin.nodejs_android.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static int DATABASE_VER= 1;
    public static String DATABASE_NAME ="MY_DATA_BASE";
    //
    //
    // USERS TABLE
    public static String TABLE_USER_NAME ="TABLE_USER";
    public static String USER_ID ="Id";
    public static String USER_UN="Username";
    public static String USER_PW="Password";
    //
    //
    // Order TABLE
    public static String TABLE_ORDER_NAME ="TABLE_ORDER";
    public static String ORDER_ID ="Id";
    public static String ORDER_TITTLE ="Title";
    public static String ORDER_CONTENT="Content";
    /////////////////////////////////////////

    ////////////////////////////////////////
    //// QUERY TABLE
    ///////////////////////////////////////
    // TABLE USER
    /////////////////////////////////////
    public String CREATE_TABLE_USER= "CREATE TABLE " + TABLE_USER_NAME+" ( "+
                  USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                  USER_UN+ " TEXT, "+
                  USER_PW+ " TEXT )";

    /////////////////////////////////////
    // TABLE CATEGORY
    /////////////////////////////////////
    public String CREATE_TABLE_CATEGORY= "CREATE TABLE " + TABLE_ORDER_NAME+" ( "+
            ORDER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            ORDER_TITTLE+ " TEXT, " +
            ORDER_CONTENT+ " TEXT " +
            ")";
        ///////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////
    public SQLiteHelper(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);

        sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
}
