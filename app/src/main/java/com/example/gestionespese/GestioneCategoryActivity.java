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
        Cursor cursor = myDb.getCategoryFromCategorieTable();
        idCategory.clear();
        nomeCategory.clear();
        flagCategory.clear();
        if (cursor.moveToFirst()) {
            do {
                idCategory.add(cursor.getString(cursor.getColumnIndex("ID_CAT")));
                nomeCategory.add(cursor.getString(cursor.getColumnIndex("NOME")));
                flagCategory.add(cursor.getString(cursor.getColumnIndex("FLAG_SELEZIONE_RAPIDA")));
            } while (cursor.moveToNext());
        }
        final GestioneCategoryAdapterRV gestioneCategotyAdapterRV = new GestioneCategoryAdapterRV(getApplicationContext(),idCategory, nomeCategory,flagCategory,iconaCestino);
        recyclerView.setAdapter(gestioneCategotyAdapterRV);
        //code to set adapter to populate list
        cursor.close();
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
                Toast.makeText(getApplicationContext(),"Home Page",Toast.LENGTH_LONG).show();
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