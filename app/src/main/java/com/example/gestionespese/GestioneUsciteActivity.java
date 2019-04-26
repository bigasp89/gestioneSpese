package com.example.gestionespese;

import android.content.Intent;
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

import com.example.gestionespese.adpter.GestioneSpeseAdapterRV;
import com.example.gestionespeses.R;

public class GestioneUsciteActivity extends AppCompatActivity {

//    ListView listViewUscite;
    RecyclerView recyclerView;
    //sorgente di dati

    String[] nomeCateogriaSpesa = {"caffe","gelato"};
    int [] iconCategoriaSpese = {R.drawable.coffe_cup,R.drawable.ice_cream};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_uscite);
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

        //gestione lista Uscite
//        listViewUscite = (ListView) findViewById(R.id.listUscite);
        recyclerView = (RecyclerView) findViewById(R.id.rvUscite);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((getApplicationContext()));
        recyclerView.setLayoutManager(linearLayoutManager);

        GestioneSpeseAdapterRV gestioneSpeseAdapterRV = new GestioneSpeseAdapterRV(getApplicationContext(),nomeCateogriaSpesa,iconCategoriaSpese);
        //collegare arrayAdapter alla listview
        recyclerView.setAdapter(gestioneSpeseAdapterRV);


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
                Toast.makeText(getApplicationContext(),"Gestione Uscite",Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_id_gestEntrate:
                Intent goToGestioneEntrateActivity = new Intent(this,GestioneEntrateActivity.class);
                startActivity(goToGestioneEntrateActivity);
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
