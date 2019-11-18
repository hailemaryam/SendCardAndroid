package com.example.sendcard.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.sendcard.DTO.Phone;
import com.example.sendcard.DTO.Sent;
import com.example.sendcard.Util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public long insertPhone(Phone phone){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_PHONE_ID, phone.getId());
        contentValues.put(Config.COLUMN_PHONE_NUMBER, phone.getPhone());

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_PHONE, null, contentValues);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

    public List<Phone> getAllPhone(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_PHONE, null, null, null, null, null, Config.COLUMN_PHONE_NUMBER, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Phone> studentList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PHONE_ID));
                        String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PHONE_NUMBER));

                        studentList.add(new Phone(id, phone));
                    }   while (cursor.moveToNext());

                    return studentList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();

    }

    public Phone getPhone(String phoneNumber){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Phone phones = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_PHONE, null,
                    Config.COLUMN_PHONE_NUMBER + " = ? ", new String[]{phoneNumber},
                    null, null, null);

            /**
                 // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

                 String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
                 cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PHONE_ID));
                String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PHONE_NUMBER));
                phones = new Phone(id, phone);
            }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return phones;
    }

    public long updatePhone(Phone phone){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_PHONE_ID, phone.getId());
        contentValues.put(Config.COLUMN_PHONE_NUMBER, phone.getPhone());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_PHONE, contentValues,
                    Config.COLUMN_PHONE_ID + " = ? ",
                    new String[] {String.valueOf(phone.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public long deletePhone(String phone) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_PHONE,
                                    Config.COLUMN_PHONE_NUMBER + " = ? ",
                                    new String[]{ phone});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public boolean deleteAllPhone(){
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_PHONE, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_PHONE);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }

    public long getNumberOfPhones(){
        long count = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_PHONE);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }

    public long insertSent(Sent sent){
        long rowId = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SENT_PHONE, sent.getPhone());
        contentValues.put(Config.COLUMN_SENT_REQUEST, sent.getRequest());
        contentValues.put(Config.COLUMN_SENT_RESPONSE, sent.getResponse());

        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_SENT, null, contentValues);
        } catch (SQLiteException e){
            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }

    public Sent getSentById(long sentId){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Sent sent = null;

        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(Config.TABLE_SENT, null,
                    Config.COLUMN_SENT_ID + " = ? ", new String[] {String.valueOf(sentId)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SENT_PHONE));
                String request = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SENT_REQUEST));
                String response = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SENT_RESPONSE));

                sent = new Sent(sentId, phone, request, response);
            }
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return sent;
    }

    public long updateSentInfo(Sent sent){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SENT_PHONE, sent.getPhone());
        contentValues.put(Config.COLUMN_SENT_REQUEST, sent.getRequest());
        contentValues.put(Config.COLUMN_SENT_RESPONSE, sent.getResponse());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_SENT, contentValues,
                    Config.COLUMN_SENT_ID + " = ? ",
                    new String[] {String.valueOf(sent.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public boolean deleteSentById(long subjectId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(Config.TABLE_SENT,
                Config.COLUMN_SENT_ID + " = ? ", new String[]{String.valueOf(subjectId)});

        return row > 0;
    }

    public long getNumberOfSents(){
        long count = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_SENT);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }

    public boolean deleteAllSent(){
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_SENT, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_PHONE);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }

}