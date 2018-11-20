package com.example.masum_pc.sqlitedemo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private  static  final  String DATABASE_NAME = "country.db";
    private static  final  int DATABASE_VERSION = 4;
//    private static  final  int DATABASE_VERSION2 = 2;
    private  static  final  String TABLE_NAME = "country_details";
    private  static  final  String COL_ID = "Id";
    private  static  final  String COL_NAME = "Name";
    private  static  final  String COL_CAPITAL = "Capital";
    private  static  final  String COL_POPULATION = "Population";
    private  static  final  String COL_AREA = "Area";
    private  static  final String CREATE_TABLE= " Create Table if not exists "+TABLE_NAME+"" +
            "( "+COL_ID+" Integer primary key autoincrement,"+COL_NAME+" varchar(255), "+COL_CAPITAL+" varchar(255), "+COL_POPULATION+" integer )  ;";

    private  static  final  String UPGRADE = " Drop Table "+TABLE_NAME+"  ";
    private  static  final  String DOWNGRADE = " ALTER TABLE "+TABLE_NAME+" Add COLUMN "+COL_AREA+"  ";

    private  Context context;

    public MyDatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        try{
            Toast.makeText(context , "On Create Executed",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);

        }
        catch (Exception e){
            Toast.makeText(context , e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


       // if(newVersion>oldVersion){
            Toast.makeText(context , "On UpGrade Executed",Toast.LENGTH_LONG).show();
            db.execSQL(UPGRADE);
            onCreate(db);
       // }
    }

    public  long insertData(String name,String capital, int population){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME , name);
        contentValues.put(COL_CAPITAL , capital);
        contentValues.put(COL_POPULATION ,population);

        long rowId =  database.insert(TABLE_NAME,null,contentValues);

        return rowId;
    }

    public ArrayList<Country> GetAllCountry(){
        ArrayList<Country> countries = new ArrayList<Country>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select * from "+TABLE_NAME+" order by "+COL_ID+" DESC ",null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0) ;
                String name=cursor.getString(1);
                String capital =cursor.getString(2);
                int population= cursor.getInt(3);
                Country aCountry = new Country(id ,name , capital, population);
                countries.add(aCountry);
            }
        }

        return countries;
    }
    public Country getCountry(int id){
        Country country = new Country()  ;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select * from "+TABLE_NAME+"  where  "+COL_ID+" ="+id+"  ",null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                int tid = cursor.getInt(0) ;
                String name=cursor.getString(1);
                String capital =cursor.getString(2);
                int population= cursor.getInt(3);
                country = new Country(tid ,name , capital, population);

            }

        }

        return country;
    }

    public boolean UpdateCountry(Country aCountry){


        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_ID, aCountry.getID());
        contentValues.put(COL_NAME , aCountry.getCountryName());
        contentValues.put(COL_CAPITAL , aCountry.getCapital());
        contentValues.put(COL_POPULATION ,aCountry.getPopulation());
        database.update(TABLE_NAME , contentValues , COL_ID+"=?" , new String[]{String.valueOf(aCountry.getID())});

        return  true;
    }

   public  int deleteCountry(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        int i = 0;

        try{
           i =  db.delete(TABLE_NAME , COL_ID+"=?" , new String[]{id});

        }
        catch (Exception e){

            String  err = e.toString();
            String a = err;
        }


        return i;
    }

}
