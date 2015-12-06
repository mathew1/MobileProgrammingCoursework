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
import java.util.Locale;

/**
 * Created by Mathew on 05/12/2015.
 */
public class databaseInfoDBMgr extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_PATH = "data/data/com.example.mathew.mobileprogrammingcoursework/databases/";
    private static final String DB_NAME = "trainstations.s3db";
    private static final String TBL_TRAININFO = "TrainStationInfo";

    public static final String COL_TRAINID= "trainstationID";
    public static final String COL_TRAINNAME = "TrainStation";
    public static final String COL_TRAINADDRESS = "TrainStationAddress";
    public static final String COL_TRAINIMAGE = "TrainStationImage";
    private final Context appContext;

    public databaseInfoDBMgr(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.appContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STARSIGNSINFO_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_TRAININFO + "("
                + COL_TRAINID + "INTEGER PRIMARY KEY," + COL_TRAINNAME
                + "TEXT," + COL_TRAINIMAGE+ "TEXT," + COL_TRAINADDRESS + "TEXT,"
                 + ")";
        db.execSQL(CREATE_STARSIGNSINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS" + TBL_TRAININFO);
            onCreate(db);
        }
    }

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
    public void addTrainInfoInfo(databaseInfo aStarSignInfo)
    {
        ContentValues values = new ContentValues();
        values.put(COL_TRAINNAME, aStarSignInfo.getTrainName());
        values.put(COL_TRAINADDRESS, aStarSignInfo.getTrainAddress());
        values.put(COL_TRAINIMAGE, aStarSignInfo.getTrainImage());


        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_TRAININFO, null, values);
        db.close();
    }

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
            StarSignsInfo.setTrainImage(cursor.getString(3));

            cursor.close();

        }
        else
        {
            StarSignsInfo = null;
        }
        db.close();
        return StarSignsInfo;
    }

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



}
