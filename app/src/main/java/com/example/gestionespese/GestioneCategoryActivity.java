package com.example.gestionespese;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionespese.adpter.GestioneCategoryAdapterRV;
import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;

import java.util.ArrayList;

public class GestioneCategoryActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    RecyclerView recyclerView;
    private ArrayList<String> idCategory = new ArrayList<String>();
    private ArrayList<String> nomeCategory = new ArrayList<String>();
    private ArrayList<String> flagCategory = new ArrayList<String>();
    private ArrayList<Integer> idRisorsa = new ArrayList<Integer>();
    int[] iconaCestino = {R.drawable.cestino};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_category);
        myDb = new DataBaseHelper(this);

        //Start Gestione ToolBar //
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        ImageView logoToolBar = (ImageView) toolbar.findViewById(R.id.toolbar_t_rex_icon);
        TextView mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitleToolBar.setText("Gestione Categorie");
        //End gestione Toolbar//

    }


    @Override
    protected void onResume() {
        myDb = new DataBaseHelper(this);
        displayCategoryPersonali();
        super.onResume();
    }

    private void displayCategoryPersonali() {
        recyclerView = (RecyclerView) findViewById(R.id.listaCategorie);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((getApplicationContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
        Cursor cursor = myDb.getAllCategoryFromCategorieTable();
        idCategory.clear();
        nomeCategory.clear();
        flagCategory.clear();
        idRisorsa.clear();
        if (cursor.moveToFirst()) {
            do {
                idCategory.add(cursor.getString(cursor.getColumnIndex("ID_CAT")));
                nomeCategory.add(cursor.getString(cursor.getColumnIndex("NOME")));
                flagCategory.add(cursor.getString(cursor.getColumnIndex("FLAG_SELEZIONE_RAPIDA")));
                idRisorsa.add(cursor.getInt(cursor.getColumnIndex("ID_ICONA")));
            } while (cursor.moveToNext());
        }
        final GestioneCategoryAdapterRV gestioneCategotyAdapterRV = new GestioneCategoryAdapterRV(getApplicationContext(),idCategory, nomeCategory,flagCategory,iconaCestino,idRisorsa);
        recyclerView.setAdapter(gestioneCategotyAdapterRV);
        //code to set adapter to populate list
        cursor.close();
    }

    //todo gestione inserimento nuova categoria
    public void insertCategory(View view) {
        Toast.makeText(getApplicationContext(),"inserisci nuova categoria",Toast.LENGTH_LONG).show();
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(GestioneCategoryActivity.this);
        //infalte layout
        final View myView = getLayoutInflater().inflate(R.layout.popup_layout_aggiungi_category,null);
//        final TextView titoloSpesa = (TextView) myView.findViewById(R.id.titoloPopUpInsertCategory);
        final EditText nomeCategoryInserito = (EditText) myView.findViewById(R.id.nomeCategoryInsert);
        final Button bt_associa = (Button) myView.findViewById(R.id.btn_associa);
        final Button bt_conferma = (Button) myView.findViewById(R.id.btn_conferma);
        final Button bt_annulla = (Button) myView.findViewById(R.id.btn_annulla);

        //settiamo i valori all'interno del popUp
        //dobbiamo passare, icona titolo
//        titoloSpesa.setText(nomeCategory.get(i));
//        iconaCategoria.setImageResource(idRisorsa.get(i));
        mBuilder.setView(myView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        bt_annulla.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });
        bt_conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"conferma",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent goToHomePageActivity = new Intent(this,MainActivity.class);
        startActivity(goToHomePageActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //metodo che viene invocato quando un Item del menu viene selezionato
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_id_homePage:
                Intent goToHomePageActivity = new Intent(this,MainActivity.class);
                startActivity(goToHomePageActivity);
                break;
            case R.id.menu_id_gestUscite:
                Intent goToGestioneSpeseActivity = new Intent(this,GestioneSpeseActivity.class);
                startActivity(goToGestioneSpeseActivity);
                break;
            case R.id.menu_id_gestEntrate:
                Intent goToGestioneEntrateActivity = new Intent(this, GestioneEntrateActivity.class);
                startActivity(goToGestioneEntrateActivity);
                break;
            case R.id.menu_id_riepilogo:
                Intent goToRiepilogoActivity = new Intent(this,RiepilogoActivity.class);
                startActivity(goToRiepilogoActivity);
                break;
            case R.id.menu_id_gestCategorie:
                Toast.makeText(getApplicationContext(),"Gestione Cateogorie",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
