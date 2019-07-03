package com.example.gestionespese;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionespese.adpter.GestioneEntrateAdapter;
import com.example.gestionespese.object.Entrate;
import com.example.gestionespeses.R;

import java.util.LinkedList;
import java.util.List;

public class GestioneEntrateActivity extends AppCompatActivity {

    //todo inserire controllo sulla descrizione che deve essere massimo di 70 caratteri
    //todo inserire controllo sul nome che deve essere al massimo di 22 caratteri
    ListView listViewEntrate;


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

        //gestione lista Uscite
        listViewEntrate = (ListView)findViewById(R.id.listviewEntrate);
        List<Entrate> listaEntrate = new LinkedList<Entrate>();
        int img = R.id.entrata_img;

        listaEntrate.add(new Entrate("Stipendio Gennaio 2018","ho preso poco","14/01/1989","10:14",1050.00));
        listaEntrate.add(new Entrate("Prostituzione","proviamo questa descrizione lunga lunga lunghissima la proviamo ancora la","15/01/1989","10:14",1540.0));
        listaEntrate.add(new Entrate("Stipendio Novembre 2019","Nessuna Descrizione Presente","14/01/1989","10:14",900));

        GestioneEntrateAdapter adapter = new GestioneEntrateAdapter(this, R.layout.item_entrate, listaEntrate);
        listViewEntrate.setAdapter(adapter);
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
                Intent goToGestioneSpeseActivity = new Intent(this, GestioneSpeseActivity.class);
                startActivity(goToGestioneSpeseActivity);
                break;
            case R.id.menu_id_gestEntrate:
                Toast.makeText(getApplicationContext(),"Gestione Uscite",Toast.LENGTH_LONG).show();
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
