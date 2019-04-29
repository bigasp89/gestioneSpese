package com.example.gestionespese;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionespese.adpter.GestioneEntrateAdapter;
import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;

import java.util.ArrayList;
import java.util.List;

public class GestioneEntrateActivity extends AppCompatActivity {

    ImageButton cestino;
    DataBaseHelper myDb;
    private ArrayList<String> idEntrata = new ArrayList<String>();
    private ArrayList<String> nomeEntrata = new ArrayList<String>();
    private ArrayList<String> importo = new ArrayList<String>();
    private ArrayList<String> descrizione = new ArrayList<String>();
    int[] iconaCestino = {R.drawable.cestino};
    int[] infoIcon = {R.drawable.info_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_entrate);
        //Start Gestione ToolBar //
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        ImageView logoToolBar = (ImageView) toolbar.findViewById(R.id.toolbar_t_rex_icon);
        TextView mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitleToolBar.setText("Gestione Entrate");
        //End gestione Toolbar//
        //*****************************//
        //Gestione onclick rimozione riga




    }


    @Override
    protected void onResume() {
        myDb = new DataBaseHelper(this);
        displayData();
        super.onResume();
    }

    private void displayData() {
        ListView listaEntrate = (ListView) findViewById(R.id.listaprova);
        Cursor cursor = myDb.getDataFromUsciteTable();
        idEntrata.clear();
        nomeEntrata.clear();
        importo.clear();
        descrizione.clear();
        if (cursor.moveToFirst()) {
            do {
                idEntrata.add(cursor.getString(cursor.getColumnIndex("ID")));
                nomeEntrata.add(cursor.getString(cursor.getColumnIndex("NOME_CATEGORIA")));
                importo.add(cursor.getString(cursor.getColumnIndex("IMPORTO")));
                descrizione.add(cursor.getString(cursor.getColumnIndex("DESCRIZIONE")));
            } while (cursor.moveToNext());
        }
        GestioneEntrateAdapter gestioneEntrateAdapter = new GestioneEntrateAdapter(getApplicationContext(),idEntrata, nomeEntrata,importo,descrizione,iconaCestino,infoIcon);
        listaEntrate.setAdapter(gestioneEntrateAdapter);
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
                Intent goToGestioneUsciteActivity = new Intent(this,GestioneUsciteActivity.class);
                startActivity(goToGestioneUsciteActivity);
                break;
            case R.id.menu_id_gestEntrate:
                Toast.makeText(getApplicationContext(),"Gestione Entrate",Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_id_riepilogo:
                Intent goToRiepilogoActivity = new Intent(this,RiepilogoActivity.class);
                startActivity(goToRiepilogoActivity);
                break;
            case R.id.menu_id_gestCategorie:
                Toast.makeText(getApplicationContext(),"non hai ancora creato una Activity",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}