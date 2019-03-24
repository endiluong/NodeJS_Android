package com.example.admin.nodejs_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.admin.nodejs_android.Models.Order;
import com.example.admin.nodejs_android.Services.SQLiteHelper;

import java.util.ArrayList;

public class daoOrder {
    private SQLiteDatabase db;
    private static daoOrder instance;

    public daoOrder(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    // GET ONE ITEM
    //
    //
    public ArrayList<Order> getDataModels(String sql, String... selectionArgs) {
        ArrayList<Order> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        Order temp;
        while (c.moveToNext()) {
            temp = new Order();
            temp.setId(c.getString(c.getColumnIndex(SQLiteHelper.ORDER_ID)));
            temp.setOrderTittle(c.getString(c.getColumnIndex(SQLiteHelper.ORDER_TITTLE)));
            temp.setOrderContent(c.getString(c.getColumnIndex(SQLiteHelper.ORDER_CONTENT)));
            result.add(temp);
        }
        return result;
    }

    //Get All List
    public ArrayList<Order> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_ORDER_NAME;
        return getDataModels(sql);
    }

    //get By Id
    public Order getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_ORDER_NAME + " WHERE ID=? ";
        ArrayList<Order> list = getDataModels(sql, Id);
        return list.get(0);
    }

    // Add
    public long insertCat(Order datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.ORDER_TITTLE, datamodel.getOrderTittle());
        values.put(SQLiteHelper.ORDER_CONTENT, datamodel.getOrderContent());

        return db.insert(SQLiteHelper.TABLE_ORDER_NAME, null, values);
    }

    //Update
    public int updateCat(Order datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.ORDER_ID, datamodel.getId());
        values.put(SQLiteHelper.ORDER_TITTLE, datamodel.getOrderTittle());
        values.put(SQLiteHelper.ORDER_CONTENT, datamodel.getOrderContent());
        return db.update(SQLiteHelper.TABLE_ORDER_NAME, values, "Id=?", new String[]{String.valueOf(datamodel.getId())});
    }

    ////////////////////////////////////
    //chỉnh sửa lại int ==> Order. //
    //Delete by Id
//    public int deleteCat(int id) {
//        return db.delete(SQLiteHelper.TABLE_CATEGORY_NAME, "Id=?", new String[]{String.valueOf(id)});
//    }
    public int deleteCat(Order category) {
        return db.delete(SQLiteHelper.TABLE_ORDER_NAME, "Id=?", new String[]{String.valueOf(category.getId())});
    }

    ///////////////////////////////////////////////////
    // CREATE INSTANCE
    public static daoOrder getInstance(Context context) {
        if (instance == null) {
            instance = new daoOrder(context);
        }
        return instance;
    }
}
