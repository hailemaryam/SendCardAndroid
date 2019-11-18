package com.example.sendcard.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sendcard.Util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    // Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static DatabaseHelper getInstance(Context context) {
        if(databaseHelper==null){
            synchronized (DatabaseHelper.class) {
                if(databaseHelper==null)
                    databaseHelper = new DatabaseHelper(context);
            }
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + Config.TABLE_PHONE + "("
                + Config.COLUMN_PHONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Config.COLUMN_PHONE_NUMBER + " TEXT NOT NULL UNIQUE  "
                + ")";

        String CREATE_SUBJECT_TABLE = "CREATE TABLE " + Config.TABLE_SENT + "("
                + Config.COLUMN_SENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_SENT_PHONE + " TEXT NOT NULL, "
                + Config.COLUMN_SENT_REQUEST + " TEXT NOT NULL, "
                + Config.COLUMN_SENT_RESPONSE + " TEXT "
                + ")";

        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_SUBJECT_TABLE);

        Logger.d("DB created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_PHONE);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_SENT);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

}
