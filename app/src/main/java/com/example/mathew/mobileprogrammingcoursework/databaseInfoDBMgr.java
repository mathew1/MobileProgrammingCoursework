package com.example.mathew.mobileprogrammingcoursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mathew on 05/12/2015.
 */
public class databaseInfoDBMgr extends SQLiteOpenHelper {
//Variables
    private static final int DB_VER = 1;
    private static final String DB_PATH = "data/data/com.example.mathew.mobileprogrammingcoursework/databases/";
    private static final String DB_NAME = "trainstations.s3db";
    private static final String TBL_TRAININFO = "TrainStationInfo";

    public static final String COL_TRAINID= "trainstationID";
    public static final String COL_TRAINNAME = "TrainStation";
    public static final String COL_TRAINADDRESS = "TrainStationAddress";
    public static final String COL_LATITUDE = "latitude";
    public static final String COL_LONGITUDE = "longitude";
    private final Context appContext;
//Constructor
    public databaseInfoDBMgr(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.appContext = context;
    }

//Creating the Deatabase
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRAININFO_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_TRAININFO + "("
                + COL_TRAINID + "INTEGER PRIMARY KEY," + COL_TRAINNAME
                + "TEXT," + COL_TRAINADDRESS + "TEXT,"  + COL_LATITUDE + "FLOAT" + COL_LONGITUDE + "FLOAT" +")";
        db.execSQL(CREATE_TRAININFO_TABLE);
    }
//Updating Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS" + TBL_TRAININFO);
            onCreate(db);
        }
    }
//Copying Database
    public void dbCreate() throws IOException
    {
        boolean dbExist = dbCheck();

        if(!dbExist)
        {
            this.getReadableDatabase();

            try{
                copyDBFromAssets();
            } catch(IOException e){
                throw new Error("Error copying database");
            }
        }
    }
//Checking Database Location
    private boolean dbCheck()
    {
        SQLiteDatabase db = null;

        try{
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);

        }catch(SQLiteException e)
        {
            Log.e("SQLHelper", "Database not Found!");
        }

        if(db != null){
            db.close();
        }
        return db !=null ? true : false;
    }
// Copying Database From Assets
    private void copyDBFromAssets() throws IOException
    {
        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;

        try{
            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0){
                dbOutput.write(buffer, 0, length);
            }

            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch(IOException e)
        {
            throw new Error("Problems copying DB!");
        }
    }
    //Adding Database Information
    public void addTrainInfoInfo(databaseInfo aStarSignInfo)
    {
        ContentValues values = new ContentValues();
        values.put(COL_TRAINNAME, aStarSignInfo.getTrainName());
        values.put(COL_TRAINADDRESS, aStarSignInfo.getTrainAddress());
        values.put(COL_LATITUDE, aStarSignInfo.getLatitude());
        values.put(COL_LONGITUDE, aStarSignInfo.getLongitude());


        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_TRAININFO, null, values);
        db.close();
    }
//Finding Specific Train Staiton
    public databaseInfo findTrainStation(String sTrainInfo)
    {
        String query = "Select * FROM " + TBL_TRAININFO + " WHERE " + COL_TRAINNAME + " =  \"" + sTrainInfo + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        databaseInfo StarSignsInfo = new databaseInfo();

        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            StarSignsInfo.setTrainID(Integer.parseInt(cursor.getString(0)));
            StarSignsInfo.setTrainName(cursor.getString(1));
            StarSignsInfo.setTrainAddress(cursor.getString(2));
            StarSignsInfo.setLatitude(Float.parseFloat(cursor.getString(5)));
            StarSignsInfo.setLongitude(Float.parseFloat(cursor.getString(6)));
            cursor.close();

        }
        else
        {
            StarSignsInfo = null;
        }
        db.close();
        return StarSignsInfo;
    }
// Removing Train Station
    public boolean removeTrainStation(String sStarSign)
    {
        boolean result = false;

        String query = "Select * FROM " + TBL_TRAININFO + " WHERE " + COL_TRAINNAME + " =  \"" + sStarSign + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        databaseInfo StarSignsInfo = new databaseInfo();

        if(cursor.moveToFirst())
        {
            StarSignsInfo.setTrainID(Integer.parseInt(cursor.getString(0)));
            db.delete(TBL_TRAININFO, COL_TRAINID + " = ?",
                    new String[] {String.valueOf(StarSignsInfo.getTrainID())} );
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
//Creating an array to store all Database Information
    public List<databaseInfo> allMapData()
    {
        String query = "Select * FROM " + TBL_TRAININFO;
        List<databaseInfo> mcMapDataList = new ArrayList<databaseInfo>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                databaseInfo StarSignsInfo = new databaseInfo();

                StarSignsInfo.setTrainID(Integer.parseInt(cursor.getString(0)));
                StarSignsInfo.setTrainName(cursor.getString(1));
                StarSignsInfo.setTrainAddress(cursor.getString(2));
               StarSignsInfo.setLatitude(Float.parseFloat(cursor.getString(3)));
                StarSignsInfo.setLongitude(Float.parseFloat(cursor.getString(4)));
                mcMapDataList.add(StarSignsInfo);
                cursor.moveToNext();
            }
        } else {
            mcMapDataList.add(null);
        }
        db.close();
        return mcMapDataList;
    }



}
