package com.example.booaplication.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.booaplication.Model.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private Context context;

    public Database(@Nullable Context context) throws IOException {
        super(context, info_db.DATABASE_NAME, null, info_db.DATABASE_VERSION);
        this.context = context;
        isDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void isDatabase() throws IOException {
        File check = new File(info_db.PACKAGE);
        if (!check.exists()) {
            boolean checker = check.mkdirs();
            System.err.println("Checker is: " + checker);
        }
        check = context.getDatabasePath(info_db.DATA_NAME);
        if (!check.exists()) {
            copyDataBase();
        }
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = context.getAssets().open(info_db.DATABASE_SOURCE);
        String outFileName = info_db.PACKAGE + info_db.DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public List<Person> getAllPerson() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Person> data = new ArrayList<>();
        String query = "SELECT * FROM person";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                createPerson(cursor, data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return data;
    }

    public List<Person> getIranPerson() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Person> data = new ArrayList<>();
        String query = "SELECT * FROM person WHERE category = 'engineering'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                createPerson(cursor, data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return data;
    }

    public void createPerson(Cursor cursor, List<Person> data) {
        Person person = new Person();
        person.setId(cursor.getInt(cursor.getColumnIndex(info_db.DATA_ID)));
        person.setCategory(cursor.getString(cursor.getColumnIndex(info_db.DATA_CATEGORY)));
        person.setName(cursor.getString(cursor.getColumnIndex(info_db.DATA_NAME)));
        person.setField(cursor.getString(cursor.getColumnIndex(info_db.DATA_FIELD)));
        person.setDisc(cursor.getString(cursor.getColumnIndex(info_db.DATA_DISC)));
        person.setImage(cursor.getString(cursor.getColumnIndex(info_db.DATA_IMAGE)));
        person.setFavorite(cursor.getInt(cursor.getColumnIndex(info_db.DATA_FAVORITE)));
        data.add(person);
    }

    public List<Person> getForeignPerson() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Person> data = new ArrayList<>();
        String query = "SELECT * FROM person WHERE category = 'notengineering'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                createPerson(cursor, data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return data;
    }
}
