package com.example.gestionespese;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionespese.adpter.GestioneUsciteAdapterRV;
import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;

import java.util.ArrayList;

public class GestioneSpeseActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBaseHelper myDb;
    private ArrayList<String> idEntrata = new ArrayList<String>();
    private ArrayList<String> nomeEntrata = new ArrayList<String>();
    private ArrayList<String> importo = new ArrayList<String>();
    private ArrayList<String> descrizione = new ArrayList<String>();
    private ArrayList<String> dataEsatta = new ArrayList<String>();
    private ArrayList<String> oraEsatta = new ArrayList<String>();
    int[] iconaCestino = {R.drawable.cestino};
    int[] infoIcon = {R.drawable.info_icon};
    int [] iconaEdit = {R.drawable.edit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_spese);
        //Start Gestione ToolBar //
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        ImageView logoToolBar = (ImageView) toolbar.findViewById(R.id.toolbar_t_rex_icon);
        TextView mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitleToolBar.setText("Gestione Uscite");
        //End gestione Toolbar//
        //*****************************//
        //Gestione onclick rimozione riga
        myDb = new DataBaseHelper(this);

    }

    @Override
    protected void onResume() {
        myDb = new DataBaseHelper(this);
        displayData();
        super.onResume();
    }

    private void displayData() {
        recyclerView = (RecyclerView) findViewById(R.id.listaSpese);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((getApplicationContext()));
        recyclerView.setLayoutManager(linearLayoutManager);

        Cursor cursor = myDb.getDataFromUsciteTable();
        idEntrata.clear();
        nomeEntrata.clear();
        importo.clear();
        descrizione.clear();
        dataEsatta.clear();
        oraEsatta.clear();
        if (cursor.moveToFirst()) {
            do {
                idEntrata.add(cursor.getString(cursor.getColumnIndex("ID")));
                nomeEntrata.add(cursor.getString(cursor.getColumnIndex("NOME_CATEGORIA")));
                importo.add(cursor.getString(cursor.getColumnIndex("IMPORTO")));
                descrizione.add(cursor.getString(cursor.getColumnIndex("DESCRIZIONE")));
                dataEsatta.add(cursor.getString(cursor.getColumnIndex("DATA_ESATTA")));
                oraEsatta.add(cursor.getString(cursor.getColumnIndex("ORA_ESATTA")));
            } while (cursor.moveToNext());
        }

        final GestioneUsciteAdapterRV gestioneUsciteAdapterRV = new GestioneUsciteAdapterRV(getApplicationContext(),idEntrata, nomeEntrata,importo,descrizione,iconaCestino,infoIcon,iconaEdit,dataEsatta,oraEsatta);
        recyclerView.setAdapter(gestioneUsciteAdapterRV);
        //code to set adapter to populate list
        cursor.close();
    }

    @Override
    public void onBackPressed() {
        Intent goToHomePageActivity = new Intent(this,MainActivity.class);
        startActivity(goToHomePageActivity);
    }

    // Start Gestione Menu e sue voci //
    // Menu icons are inflated just as they were with actionbar
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
                Toast.makeText(getApplicationContext(),"Gestione Spese",Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_id_gestEntrate:
                Intent goToGestioneUsciteActivity = new Intent(this, GestioneEntrateActivity.class);
                startActivity(goToGestioneUsciteActivity);
                break;
            case R.id.menu_id_riepilogo:
                Intent goToRiepilogoActivity = new Intent(this,RiepilogoActivity.class);
                startActivity(goToRiepilogoActivity);
                break;
            case R.id.menu_id_gestCategorie:
                Intent goToGestCategorieActivity = new Intent(this,GestioneCategoryActivity.class);
                startActivity(goToGestCategorieActivity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}