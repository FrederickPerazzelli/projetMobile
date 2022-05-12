/****************************************
 Fichier : DBManager.java
 Auteur : Jean-Nyckolas Roy
 Fonctionnalité : ---
 Date : 2022-05-03
 ****************************************/
package com.example.projetmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigInteger;

/**
 * Classe permettant de gérer l'interaction avec la BD locale
 */
public class DBManager {
    /**
     * Gestionnaire de base de données
     */
    private SQLiteDatabase mydb;
    /**
     * Contexte du DBManager
     */
    private Context DBcontext;

    /**
     * Initialise la connection à la BD
     * @param context est le contexte ou le gestionnaire est instancié
     */
    public void setupDBConnection(Context context) {
        this.DBcontext = context;
        this.mydb = context.openOrCreateDatabase("projetMobile", Context.MODE_PRIVATE, null);
        this.mydb.execSQL("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY, email VARCHAR(128), firstName VARCHAR(255), lastName VARCHAR(255), intitution VARCHAR(255), field VARCHAR(255), phone BIGINT, birthdate DATE, image BLOB);");
        this.mydb.execSQL("CREATE TABLE IF NOT EXISTS Meeting(id INTEGER PRIMARY KEY, tutor VARCHAR(255), status VARCHAR(255), motive VARCHAR(255), meetingTime DATETIME, location VARCHAR(255), comments VARCHAR(255));");
    }

    /**
     * Permet de modifier l'utilisateur courant dans la base de données
     * @param user est l'utilisateur connecté
     */
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

    /**
     * Retourne l'utilisateur présentement connecté
     * @return l'utilisateur qui est connecté
     */
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
}