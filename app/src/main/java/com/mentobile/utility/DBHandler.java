package com.mentobile.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.internal.app.ToolbarActionBar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Deepak Sharma on 7/28/2015.
 */

public class DBHandler extends SQLiteOpenHelper {

    String TAG = "DBHandler";

    public static final String DATABASE_NAME = "MarriageTies.db";

    public static final String TBL_RELIGION = "tbl_religion";
    public static final String TBL_CASTE = "tbl_caste";
    public static final String TBL_MOTHER_TONGUE = "tbl_mother_tongue";
    public static final String TBL_COUNTRY = "tbl_country";
    public static final String TBL_STATE = "tbl_state";
    public static final String TBL_CITY = "tbl_city";
    public static final String TBL_EDUCATION = "tbl_education";
    public static final String TBL_OCCUPATION = "tbl_occupation";
    public static final String TBL_DESIGNATION = "tbl_designation";

    String patterData = "[$&+,:;=?@#|'<>.^*()%!-]";

    private String getCreateQuery(String tableName) {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName +
                "(ID VARCHAR PRIMARY KEY NOT NULL UNIQUE, NAME VARCHAR, STATUS VARCHAR)";
        return query;
    }

    public DBHandler(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
        onCreate(getWritableDatabase());
        onUpgrade(getWritableDatabase(), 1, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateQuery(TBL_RELIGION));
        db.execSQL(getCreateQuery(TBL_MOTHER_TONGUE));
        db.execSQL(getCreateQuery(TBL_COUNTRY));
        db.execSQL(getCreateQuery(TBL_EDUCATION));
        db.execSQL(getCreateQuery(TBL_OCCUPATION));
        db.execSQL(getCreateQuery(TBL_DESIGNATION));
        db.execSQL(getCreateQuery(TBL_CASTE));
        db.execSQL(getCreateQuery(TBL_STATE));
        db.execSQL(getCreateQuery(TBL_CITY));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void dropTable(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void clearAllData(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM " + tableName);
    }

    public void deleteRowFromTable(String tableName, String emailID) {
        SQLiteDatabase db = getReadableDatabase();
        String delRowQuery = "DELETE FROM " + tableName + " WHERE email=" + "'" + emailID + "'";
        db.execSQL(delRowQuery);
    }

    public void insertData(String tableName, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        String name = values.getAsString("NAME");
        Pattern pattern = Pattern.compile(patterData);
        Matcher matcher = pattern.matcher(name);
        if(matcher.find()){
            String newData = matcher.replaceFirst("");
            values.put("NAME", newData);
        }
        db.insert(tableName, null, values);
    }

    public Cursor getProfileFromDB(String tableName, String emailID) {
        SQLiteDatabase db = getReadableDatabase();
        String selectData = "SELECT * FROM " + tableName + " WHERE email=" + "'" + emailID + "'";
        Cursor cursor = db.rawQuery(selectData, null);
        return cursor;
    }

    public Cursor getDataFromTable(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        String selectData = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(selectData, null);
        Log.d(TAG, "::::::Cursor Value " + cursor.getCount());
        return cursor;
    }

    public List<String> getData(String tableName) {
        List<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectData = "SELECT NAME FROM " + tableName;
        Cursor cursor = db.rawQuery(selectData, null);
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        return arrayList;
    }

    public String getIDUsingName(String tableName, String columnValue) {
        SQLiteDatabase db = getReadableDatabase();
        String data = null;
        String selectData = "SELECT ID FROM " + tableName + " WHERE NAME=" + "'" + columnValue + "'";
        Cursor cursor = db.rawQuery(selectData, null);
        while (cursor.moveToNext()) {
            data = cursor.getString(0);
        }
        return data;
    }


    public boolean isTableEmplty(String tablename) {
        SQLiteDatabase db = getReadableDatabase();
        String selectData = "SELECT *FROM " + tablename;
        Cursor cursor = db.rawQuery(selectData, null);
        if (cursor.getCount() < 1) {
            return true;
        }
        return false;
    }
}
