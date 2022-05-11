package com.example.projetmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private SQLiteDatabase mydb;
    private Context DBcontext;

    public void setupDBConnection(Context context) {
        this.DBcontext = context;
        this.mydb = context.openOrCreateDatabase("projetMobile", Context.MODE_PRIVATE, null);
        this.mydb.execSQL("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY, email VARCHAR(128), firstName VARCHAR(255), lastName VARCHAR(255), intitution VARCHAR(255), field VARCHAR(255), phone BIGINT, birthdate DATE, image BLOB);");
        this.mydb.execSQL("CREATE TABLE IF NOT EXISTS Meeting(id INTEGER PRIMARY KEY, tutor VARCHAR(255), status VARCHAR(255), motive VARCHAR(255), meetingTime DATETIME, location VARCHAR(255), comments VARCHAR(255));");
    }

    public void setUser(User user) {
        this.mydb.execSQL("DELETE FROM User;");

        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("email", user.getEmail());
        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("intitution", user.getInstitution());
        values.put("field", user.getField());
        values.put("phone", String.valueOf(user.getPhone()));
        values.put("birthdate", user.getBirthdate());

        this.mydb.insert("User", null, values);
    }
}