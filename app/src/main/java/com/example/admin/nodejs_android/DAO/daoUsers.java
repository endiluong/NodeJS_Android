package com.example.admin.nodejs_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.admin.nodejs_android.Models.User;
import com.example.admin.nodejs_android.Services.SQLiteHelper;

import java.util.ArrayList;

public class daoUsers {
    private SQLiteDatabase db;
    private static daoUsers instance;

    public daoUsers(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }
    // GET ONE ITEM
    //
    //
    public ArrayList<User> getDataModels(String sql, String... selectionArgs) {
        ArrayList<User> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        User temp;
        while (c.moveToNext()) {
            temp = new User();
            temp.setId(c.getString(c.getColumnIndex(SQLiteHelper.USER_ID)));
            temp.setUsername(c.getString(c.getColumnIndex(SQLiteHelper.USER_UN)));
            temp.setPassword(c.getString(c.getColumnIndex(SQLiteHelper.USER_PW)));
            result.add(temp);
        }
        return result;
    }
    //Get All List
    public ArrayList<User> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_USER_NAME;
        return getDataModels(sql);
    }
    //get By Id
    public User getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_USER_NAME + " WHERE ID=? ";
        ArrayList<User> list = getDataModels(sql, Id);
        return list.get(0);
    }
    // Add
    public long insertUser(User datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.USER_ID, datamodel.getId());
        values.put(SQLiteHelper.USER_UN, datamodel.getUsername());
        values.put(SQLiteHelper.USER_PW, datamodel.getPassword());

        return db.insert(SQLiteHelper.TABLE_USER_NAME, null, values);
    }
    //Update
    public int updateUser(User datamodel) {
        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.USER_ID, datamodel.getId());
        values.put(SQLiteHelper.USER_UN, datamodel.getUsername());
        values.put(SQLiteHelper.USER_PW, datamodel.getPassword());
        return db.update(SQLiteHelper.TABLE_USER_NAME, values, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }
    //Delete by Id
//    public int deleteUser(int id) {
//        return db.delete(SQLiteHelper.TABLE_USER_NAME, "id=?", new String[]{String.valueOf(id)});
//    }
    ///--Sua int id --> User user
    public int deleteUser(User users) {
        Log.i(">>>>>>>",users.getId()+" ");
        return db.delete(SQLiteHelper.TABLE_USER_NAME, "Id=?", new String[]{String.valueOf(users.getId())});
    }
    public int deleteUserbyUN(User users) {
        return db.delete(SQLiteHelper.TABLE_USER_NAME, "Username=?", new String[]{String.valueOf(users.getUsername())});
    }


    //////////--------------////////////
    public int changePassWord(User users){
        ContentValues values = new ContentValues();
        values.put("username",users.getUsername());
        values.put("password",users.getPassword());
        int result = db.update(SQLiteHelper.TABLE_USER_NAME,values,"username=?", new
                String[]{users.getUsername()});
        if(result==0){
            return -1;
        }
        return 1;
    }




    ///////////////////////////////////////////////////
    // CREATE INSTANCE
    public static daoUsers getInstance(Context context) {
        if (instance == null) {
            instance = new daoUsers(context);
        }
        return instance;
    }

}
