package com.example.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, nim TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertuserdata(String name, String contact, String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("nim", nim);
        long result =db.insert("Userdetails", null, contentValues);
        if (result == 1 ){
            return false;
        } else {
            return true;
        }
    }
    public boolean updateuserdata(String name, String contact, String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("nim", nim);
        Cursor cursor = db.rawQuery("Select * from Userdetails where name = ? ", new String[] {name});
        if (cursor.getCount()>0){

        long result =db.update("Userdetails", contentValues, "name=?", new String[] {name});
        if (result == 1 ){
            return false;
        } else {
            return true;
        }
        } else {
            return false;
        }
    }
    public boolean deleteuserdata(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails where name = ? ", new String[] {name});
        if (cursor.getCount()>0){

            long result =db.delete("Userdetails", "name=?", new String[] {name});
            if (result == 1 ){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor viewuserdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails", null);
        return cursor;
    }

    public ArrayList<Model> getAllData(String orderBy){

        ArrayList<Model> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails order by"+orderBy, null);

        if (cursor.moveToNext()){
            do {
                Model model = new Model (
                        ""+cursor.getString(cursor.getColumnIndex("name")),
                        ""+cursor.getString(cursor.getColumnIndex("price")),
                        ""+cursor.getString(cursor.getColumnIndex("desc"))
                );
                arrayList.add(model);
            }while(cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

}
