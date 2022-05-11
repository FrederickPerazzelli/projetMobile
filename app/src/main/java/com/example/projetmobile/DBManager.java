package com.example.projetmobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigInteger;

public class DBManager {
    private SQLiteDatabase mydb;
    private Context DBcontext;

    public void setupDBConnection(Context context) {
        this.DBcontext = context;
        this.mydb = context.openOrCreateDatabase("projetMobile", Context.MODE_PRIVATE, null);
        this.mydb.execSQL("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY, email VARCHAR(128), firstName VARCHAR(255), lastName VARCHAR(255), intitution VARCHAR(255), field VARCHAR(255), phone BIGINT, birthdate DATE, image BLOB);");
        this.mydb.execSQL("CREATE TABLE IF NOT EXISTS Meeting(id INTEGER PRIMARY KEY, tutor VARCHAR(255), status VARCHAR(255), motive VARCHAR(255), meetingTime DATETIME, location VARCHAR(255), comments VARCHAR(255));");
    }

    public User getCurrentUser() {
        Cursor cursor;

        cursor = mydb.rawQuery("SELECT * FROM User;", null);
        cursor.moveToFirst();
        String[] roles = {"ROLE_USER"};
        User user = new User();
        user.setId(cursor.getInt(0));
        /*user.setFirstName(cursor.getString(2));
        user.setLastName(cursor.getString(3));
        user.setInstitution(cursor.getString(4));
        user.setField(cursor.getString(5));
        user.setPhone(new BigInteger(cursor.getString(6)));*/

        return user;
    }

    public void syncDB() {

    }
}