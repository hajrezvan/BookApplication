package com.example.booaplication.Database;

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

    public Database(@Nullable Context context) {
        super(context, InfoDb.DATABASE_NAME, null, InfoDb.DATABASE_VERSION);
        this.context = context;
        isDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void isDatabase() {
        File check = new File(InfoDb.PACKAGE);
        if (!check.exists()) {
            check.mkdirs();
        }
        check = context.getDatabasePath(InfoDb.DATA_NAME);
        if (!check.exists()) {
            copyDataBase();
        }
    }

    public void copyDataBase() {

        try {
            InputStream myInput = context.getAssets().open(InfoDb.DATABASE_SOURCE);

            String outFileName = InfoDb.PACKAGE + InfoDb.DATABASE_NAME;

            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPerson() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Person> data = new ArrayList<>();
        String query = "SELECT * FROM person";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(InfoDb.DATA_ID)));
                person.setCategory(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_CATEGORY)));
                person.setDisc(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_DISC)));
                person.setName(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_NAME)));
                person.setField(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_FIELD)));
                person.setImage(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_IMAGE)));
                person.setFavorite(cursor.getInt(cursor.getColumnIndex(InfoDb.DATA_FAVORITE)));
                data.add(person);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return data;
    }

    public List<Person> getIranPerson() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Person> data = new ArrayList<>();
        String query = "SELECT * FROM person WHERE category = 'iran'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(InfoDb.DATA_ID)));
                person.setCategory(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_CATEGORY)));
                person.setDisc(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_DISC)));
                person.setName(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_NAME)));
                person.setField(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_FIELD)));
                person.setImage(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_IMAGE)));
                person.setFavorite(cursor.getInt(cursor.getColumnIndex(InfoDb.DATA_FAVORITE)));
                data.add(person);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return data;
    }

    public List<Person> getForeignPerson() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Person> data = new ArrayList<>();
        String query = "SELECT * FROM person WHERE category = 'foreign'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(InfoDb.DATA_ID)));
                person.setCategory(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_CATEGORY)));
                person.setDisc(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_DISC)));
                person.setName(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_NAME)));
                person.setField(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_FIELD)));
                person.setImage(cursor.getString(cursor.getColumnIndex(InfoDb.DATA_IMAGE)));
                person.setFavorite(cursor.getInt(cursor.getColumnIndex(InfoDb.DATA_FAVORITE)));
                data.add(person);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return data;
    }

}
