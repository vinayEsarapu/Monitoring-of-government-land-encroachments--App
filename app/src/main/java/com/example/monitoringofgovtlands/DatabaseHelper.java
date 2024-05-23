package com.example.monitoringofgovtlands;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {
    //to create sqlite database
    public static final String DATABASE_NAME = "GovernmentLand_database";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "SNO";
    public static final String COL_2 = "District";
    public static final String COL_3 = "Mandal";
    public static final String COL_4 = "Village";
    public static final String COL_5 = "SurveyNumber";
    public static final String COL_6 = "Date";
    public static final String COL_7 = "Time";
    public static final String COL_8 = "IsLandEncroached";
    public static final String COL_9 = "EncroacherName";
    public static final String COL_10 = "IdProof";
    public static final String COL_11 = "TypeOfEncroachment";
    public static final String COL_12 = "EncroachmentSince_date";
    public static final String COL_13 = "Imagepath";
    public static final String COL_14 = "Latitude";
    public static final String COL_15 = "Longitude";
    public static final String COL_16 = "Address";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT, " + COL_3 + " TEXT, " + COL_4 + " TEXT, " + COL_5 + " TEXT, " + COL_6 + " TEXT, " + COL_7 + " TEXT, " + COL_8 + " TEXT," + COL_9 + " TEXT," + COL_10 + " TEXT," + COL_11 + " TEXT," + COL_12 + " TEXT," + COL_13 + " TEXT," + COL_14 + " TEXT," + COL_15 + " TEXT," + COL_16 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String districtname, String mandalname, String villagename, String surveynumber, String date, String time, String IsLandEncroached, String EncroacherName, String IdProof, String TypeofEncroachment, String EncroachedSince_date, String Imagepath, String latitude, String longitude, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, districtname);
        contentValues.put(COL_3, mandalname);
        contentValues.put(COL_4, villagename);
        contentValues.put(COL_5, surveynumber);
        contentValues.put(COL_6, date);
        contentValues.put(COL_7, time);
        contentValues.put(COL_8, IsLandEncroached);
        contentValues.put(COL_9, EncroacherName);
        contentValues.put(COL_10, IdProof);
        contentValues.put(COL_11, TypeofEncroachment);
        contentValues.put(COL_12, EncroachedSince_date);
        contentValues.put(COL_13, Imagepath);
        contentValues.put(COL_14, latitude);
        contentValues.put(COL_15, longitude);
        contentValues.put(COL_16, address);

        long result = -1;

        result = db.insert(TABLE_NAME, null, contentValues);


            db.close();


        return result>-1;
    }



    //to save the user data

    private String BitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;
    }
}











