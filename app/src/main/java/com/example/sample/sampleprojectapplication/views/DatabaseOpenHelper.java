package com.example.sample.sampleprojectapplication.views;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sample.sampleprojectapplication.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azimmemon on 03/07/19.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "USERS_DB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "USERS";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String NODE_ID = "NODE_ID";
    private static final String IMAGE_URL = "IMAGE_URL";

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + IMAGE_URL + " TEXT,"
                + NODE_ID + " TEXT"
                + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }


    public void insertUsersData(List<UserModel> usersList){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        for (UserModel model: usersList){
            values.put(NAME, model.getName());
            values.put(NODE_ID, model.getNodeId());
            values.put(IMAGE_URL, model.getImageUrl());
            database.insert(TABLE_NAME, null, values);
        }

        database.close();
    }

    public List<UserModel> getUsersData(){
        List<UserModel> usersList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            UserModel model = new UserModel();
            model.setName(cursor.getString(1));
            model.setImageUrl(cursor.getString(2));
            model.setNodeId(cursor.getString(3));

            usersList.add(model);
            cursor.moveToNext();
        }

        cursor.close();
        database.close();
        return usersList;
    }
}
