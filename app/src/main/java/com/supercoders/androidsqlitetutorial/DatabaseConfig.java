package com.supercoders.androidsqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConfig extends SQLiteOpenHelper {

    private static final int db_vs=1;
    private static final String  name_db="trip_db";
    private static final String table_trip="trip";
    private static final String id="id";
    private static final String name_trip="name_trip";
    private static final String destination_trip="destination_trip";
    private static final String date_trip="date_trip";
    private static final String require_trip="require_trip";
    private static final String description_trip="description_trip";
    private static final String created_at_trip="created_at_trip";


    public DatabaseConfig(Context context){
        super(context,name_db,null,db_vs);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query="CREATE TABLE if not EXISTS "+name_db+
                "("+
                id+" INTEGER PRIMARY KEY,"+
                name_trip+" TEXT ,"+
                destination_trip+" TEXT ,"+
                date_trip+ " TEXT ,"+
                require_trip+" TEXT ,"+
                description_trip+" TEXT ,"+
                created_at_trip+ " TEXT "+
                ")";
        db.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_trip);
     }

    public void AddTrip(Trip_table_model tripModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(name_trip,tripModel.getTrip_name());
        contentValues.put(destination_trip,tripModel.getTrip_destination());
        contentValues.put(date_trip,tripModel.getTrip_date());
        contentValues.put(require_trip,tripModel.getTrip_require());
        contentValues.put(description_trip,tripModel.getTrip_description());
        contentValues.put(created_at_trip,tripModel.getTrip_created_at());
        db.insert(table_trip,null,contentValues);
        db.close();
    }

    public Trip_table_model getTrip(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(table_trip,new String[]{id,name_trip,destination_trip,date_trip,require_trip,description_trip,created_at_trip},name_trip+" = ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Trip_table_model tripModel=new Trip_table_model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        db.close();
        return tripModel;
    }

    public List<Trip_table_model> getAllTrips(){
        List<Trip_table_model> tripModelList=new ArrayList<>();
        String query="SELECT * from "+table_trip;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Trip_table_model studentModel=new Trip_table_model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(3),cursor.getString(5),cursor.getString(6));
                tripModelList.add(studentModel);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return tripModelList;
    }

    public int updateTrip(Trip_table_model tripModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name_trip,tripModel.getTrip_name());
        contentValues.put(destination_trip,tripModel.getTrip_destination());
        contentValues.put(date_trip,tripModel.getTrip_date());
        contentValues.put(require_trip,tripModel.getTrip_require());
        contentValues.put(description_trip,tripModel.getTrip_description());
        return db.update(table_trip,contentValues,table_trip+"=?",new String[]{String.valueOf(tripModel.getTrip_name())});

    }
    public void deleteTrip(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table_trip,id+"=?",new String[]{id});
        db.close();
    }
}
