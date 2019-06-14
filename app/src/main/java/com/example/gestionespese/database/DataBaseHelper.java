package com.example.gestionespese.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gestionespeses.R;



public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gestionespese.db";

   //TABLE_NAME_USCITE
    public static String TABLE_NAME_USCITE = "uscite_table";
    public static String COLONNA_1 = "ID";
    public static String NOME_CATEGORIA = "NOME_CATEGORIA";
    public static String IMPORTO = "IMPORTO";
    public static String DESCRIZIONE = "DESCRIZIONE";
    public static String DATA_ESATTA = "DATA_ESATTA";
    public static String ORA_ESATTA = "ORA_ESATTA";

    //TABLE_NAME_CATEGORIA
    public static String TABLE_NAME_CATEGORIA = "category_table";
    public static String ID_CAT = "ID_CAT";
    public static String NOME = "NOME";
    public static String FLAG_SELEZIONE_RAPIDA = "FLAG_SELEZIONE_RAPIDA";
    public static String ID_ICONA = "ID_ICONA";


    //TABLE_NAME_ENTRATE
    public static String TABLE_NAME_ENTRATE = "tabella_entrate";
    public static String ID_ENTRATA = "ID";
    public static String NOME_CATEGORIA_ENTRATA = "NOME_CATEGORIA";
    public static String IMPORTO_ENTRATA = "IMPORTO";
    public static String DESCRIZIONE_ENTRATA = "DESCRIZIONE";
    public static String DATA_ENTRY = "DATA_ENTRY";
    public static String ORA_ENTRY = "ORA_ENTRY";

    public int affitto = R.drawable.home_icon;
    public int caffe = R.drawable.coffe_cup;
    int giochi = R.drawable.joypad;
    int gifcard = R.drawable.gift_card;
    int phone = R.drawable.smartphone;
    int gelato = R.drawable.ice_cream;
    int varie = R.drawable.imgdefault;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create TABLE " + TABLE_NAME_USCITE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME_CATEGORIA TEXT,IMPORTO DOUBLE,DESCRIZIONE STRING,DATA_ESATTA STRING,ORA_ESATTA STRING)");

        db.execSQL(" create TABLE " + TABLE_NAME_ENTRATE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME_CATEGORIA_ENTRATA TEXT,IMPORTO_ENTRATA DOUBLE,DESCRIZIONE_ENTRATA STRING,DATA_ENTRY STRING,ORA_ENTRY STRING)");

        db.execSQL(" create TABLE if not exists " + TABLE_NAME_CATEGORIA + " (ID_CAT INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME TEXT,FLAG_SELEZIONE_RAPIDA STRING,ID_ICONA INT)");


        db.execSQL("INSERT INTO  category_table(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES (?,?,?)", new Object[]{"Affitto", "Y",affitto});
        db.execSQL("INSERT INTO  category_table(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES (?,?,?)", new Object[]{"Caffè", "Y",caffe});
        db.execSQL("INSERT INTO  category_table(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES (?,?,?)", new Object[]{"Giochi", "Y",giochi});
        db.execSQL("INSERT INTO  category_table(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES (?,?,?)", new Object[]{"Regali", "Y",gifcard});
        db.execSQL("INSERT INTO  category_table(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES (?,?,?)", new Object[]{"Telefono", "Y",phone});
        db.execSQL("INSERT INTO  category_table(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES (?,?,?)", new Object[]{"Gelato", "N",gelato});
        db.execSQL("INSERT INTO  category_table(NOME, FLAG_SELEZIONE_RAPIDA,ID_ICONA) VALUES (?,?,?)", new Object[]{"Varie", "N",varie});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USCITE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ENTRATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORIA);
        onCreate(db);
    }

    public boolean insertUscita(String nomeCategoria, Double importo, String descrizione, String dataEsatta, String oraEsatta) {
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


    public boolean updateDataValue(String id, Double importo, String descrizione) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLONNA_1, id);
        contentValues.put(IMPORTO, importo);
        contentValues.put(DESCRIZIONE, descrizione);
        db.update(TABLE_NAME_USCITE, contentValues, "ID = ?", new String[]{id});
        db.close();
        return true;
    }

    public Double getImportoFromId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String table_name = TABLE_NAME_USCITE;
        int idValue = id;
        String selectQuery = "select * from " + table_name + " WHERE ID= " + idValue;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Double importo = cursor.getDouble(cursor.getColumnIndex("IMPORTO"));
        db.close();
        return importo;
    }

    public double getImportoTotaleSpese() {
        double totale = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + IMPORTO + ") FROM " + TABLE_NAME_USCITE, null);
        if (cursor.moveToFirst()) {
            totale = cursor.getDouble(0);
        }
        while (cursor.moveToNext()) ;
        return totale;
    }


    //GESTIONE TABELLA ENTRATE

    public boolean inserisciEntrata(String nomeEntrata, Integer importo, String descrizione, String dataEntry, String oraEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME_CATEGORIA_ENTRATA, nomeEntrata);
        contentValues.put(IMPORTO_ENTRATA, importo);
        contentValues.put(DESCRIZIONE_ENTRATA, descrizione);
        contentValues.put(DATA_ENTRY, dataEntry);
        contentValues.put(ORA_ENTRY, oraEntry);
        long result = db.insert(TABLE_NAME_USCITE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
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


    public boolean insertCategoria(String nomeCategoria,String flgSelezioneRapida, int risorsaImg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME, nomeCategoria);
        contentValues.put(FLAG_SELEZIONE_RAPIDA, flgSelezioneRapida);
        contentValues.put(ID_ICONA, risorsaImg);
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

    public boolean updateFlagValueCategory(String id, String nomeCategory, String flagChange, int risorsaImg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_CAT, id);
        contentValues.put(NOME, nomeCategory);
        contentValues.put(FLAG_SELEZIONE_RAPIDA, flagChange);
        contentValues.put(ID_ICONA, risorsaImg);
        db.update(TABLE_NAME_CATEGORIA, contentValues, "ID_CAT = ?", new String[]{id});
        db.close();
        return true;
    }

}
