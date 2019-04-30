package com.example.gestionespese.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GestioneSpese.db";

    public static  String TABLE_NAME = "uscite_table";
    public static  String COLONNA_1 = "ID";
    public static  String NOME_CATEGORIA = "NOME_CATEGORIA";
    public static  String IMPORTO = "IMPORTO";
    public static  String DESCRIZIONE= "DESCRIZIONE";

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME_CATEGORIA TEXT,IMPORTO INTEGER,DESCRIZIONE STRING)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertEntrata(String nomeCategoria, Integer importo, String descrizione){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME_CATEGORIA,nomeCategoria);
        contentValues.put(IMPORTO,importo);
        contentValues.put(DESCRIZIONE,descrizione);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getDataFromUsciteTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
     }

     public boolean deleteRawEntrate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String table_name = TABLE_NAME;
        int numeroRigheEliminate = db.delete(table_name,"ID=" + id,null);
        if(numeroRigheEliminate>0){
            return true;
        }else{
            return false;
        }
     }
}
