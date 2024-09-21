package com.example.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    private static final  String DB_NAME = "coursedb";

    private static final  int DB_VERSION = 1;

    private static final  String TABLE_NAME = "mycourses";

    private static final  String ID_COL = "id";

    private static final  String NAME_COL = "name";

    private static final  String DURATION_COL = "duration";

    private static final  String TRACK_COL = "tracks";

    private static final  String DESCRIPTION_COL = "description";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                +DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACK_COL + " TEXT)";
        db.execSQL(query);

    }

    public void addNewCourses(String courseName, String courseDuration, String courseDescription, String courseTracks){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACK_COL, courseTracks);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateCourses(String courseName, String courseDuration, String courseDescription, String courseTracks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACK_COL, courseTracks);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{courseName});
        db.close();

    }

    public String showCourses(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourse= db.rawQuery("Select * From " + TABLE_NAME, null);
        String data="";

        if (cursorCourse.moveToFirst()){
            do {
                data=data+ "\n"+ cursorCourse.getString(1)+ " "+
                        cursorCourse.getString(2)+ " "+
                        cursorCourse.getString(3)+ " "+
                        cursorCourse.getString(4)+ " ";
            }while (cursorCourse.moveToNext());{
            }
        }

        cursorCourse.close();
        return data;
    }

    public void deleteCourses(String courseName){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
