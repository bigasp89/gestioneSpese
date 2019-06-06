package com.example.gestionespese.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;
import static android.view.View.Y;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GestioneSpese.db";
    public static String TABLE_NAME_USCITE = "uscite_table";
    public static String COLONNA_1 = "ID";
    public static String NOME_CATEGORIA = "NOME_CATEGORIA";
    public static String IMPORTO = "IMPORTO";
    public static String DESCRIZIONE = "DESCRIZIONE";
    public static String DATA_ESATTA = "DATA_ESATTA";
    public static String ORA_ESATTA = "ORA_ESATTA";


    public static String TABLE_NAME_CATEGORIA = "category_table";
    public static String ID_CAT = "ID_CAT";
    public static String NOME = "NOME";
    public static String FLAG_SELEZIONE_RAPIDA = "FLAG_SELEZIONE_RAPIDA";
    public static String ID_ICONA = "ID_ICONA";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 16);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create TABLE " + TABLE_NAME_USCITE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME_CATEGORIA TEXT,IMPORTO INTEGER,DESCRIZIONE STRING,DATA_ESATTA STRING,ORA_ESATTA STRING)");

        db.execSQL(" create TABLE if not exists " + TABLE_NAME_CATEGORIA + " (ID_CAT INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME TEXT,FLAG_SELEZIONE_RAPIDA STRING,ID_ICONA INT)");

        db.execSQL("INSERT INTO " + TABLE_NAME_CATEGORIA+ "(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES ('Affitto', 'Y',2131165276)");
        db.execSQL("INSERT INTO " + TABLE_NAME_CATEGORIA+ "(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES ('Caffè', 'Y',2131165270)");
        db.execSQL("INSERT INTO " + TABLE_NAME_CATEGORIA+ "(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES ('Giochi', 'Y',2131165282)");
        db.execSQL("INSERT INTO " + TABLE_NAME_CATEGORIA+ "(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES ('Regali', 'Y',2131165273)");
        db.execSQL("INSERT INTO " + TABLE_NAME_CATEGORIA+ "(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES ('Telefono', 'Y',2131165299)");
        db.execSQL("INSERT INTO " + TABLE_NAME_CATEGORIA+ "(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES ('Telefninoo', 'N',2131165299)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USCITE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORIA);
        onCreate(db);
    }

    public boolean insertEntrata(String nomeCategoria, Integer importo, String descrizione, String dataEsatta, String oraEsatta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME_CATEGORIA, nomeCategoria);
        contentValues.put(IMPORTO, importo);
        contentValues.put(DESCRIZIONE, descrizione);
        contentValues.put(DATA_ESATTA, dataEsatta);
        contentValues.put(ORA_ESATTA, oraEsatta);
        long result = db.insert(TABLE_NAME_USCITE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //METODI TABELLA USCITE
    public Cursor getDataFromUsciteTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME_USCITE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean deleteRawUscite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String table_name = TABLE_NAME_USCITE;
        int numeroRigheEliminate = db.delete(table_name, "ID=" + id, null);
        if (numeroRigheEliminate > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getDescrizioneFromId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String table_name = TABLE_NAME_USCITE;
        int idValue = id;
        String selectQuery = "select * from " + table_name + " WHERE ID= " + idValue;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String descrizioneItem = cursor.getString(cursor.getColumnIndex("DESCRIZIONE"));
        db.close();
        return descrizioneItem;
    }


    public boolean updateDataValue(String id, int importo, String descrizione) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLONNA_1, id);
        contentValues.put(IMPORTO, importo);
        contentValues.put(DESCRIZIONE, descrizione);
        db.update(TABLE_NAME_USCITE, contentValues, "ID = ?", new String[]{id});
        db.close();
        return true;
    }

    public int getImportoFromId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String table_name = TABLE_NAME_USCITE;
        int idValue = id;
        String selectQuery = "select * from " + table_name + " WHERE ID= " + idValue;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int importo = cursor.getInt(cursor.getColumnIndex("IMPORTO"));
        db.close();
        return importo;
    }

    public int getImportoTotaleSpese() {
        int totale = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + IMPORTO + ") FROM " + TABLE_NAME_USCITE, null);
        if (cursor.moveToFirst()) {
            totale = cursor.getInt(0);
        }
        while (cursor.moveToNext()) ;
        return totale;
    }


    //METODI TABELLA CATEGORIE

    public Cursor getAllCategoryFromCategorieTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " select * from " + TABLE_NAME_CATEGORIA ;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor getCategoryFlagged() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " select * from " + TABLE_NAME_CATEGORIA + " WHERE " + FLAG_SELEZIONE_RAPIDA + " = 'Y'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean insertCategoria(String nomeCategoria,String flgSelezioneRapida) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME, nomeCategoria);
        contentValues.put(FLAG_SELEZIONE_RAPIDA, flgSelezioneRapida);
        long result = db.insert(TABLE_NAME_CATEGORIA, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteCategoria(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String table_name = TABLE_NAME_CATEGORIA;
        int numeroRigheEliminate = db.delete(table_name, "ID=" + id, null);
        if (numeroRigheEliminate > 0) {
            return true;
        } else {
            return false;
        }
    }


}
