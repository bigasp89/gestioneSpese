package com.example.gestionespese;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionespese.adpter.GridViewSpeseAdapter;
import com.example.gestionespeses.R;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    GridView gridViewSpese;
    final String TAG = " Activity = Home page";

    //sorgente d dati gridView
    //inserimento valori in griglia spese e icone
    String[] nomeCategoriaSpese = {
            "Caffè",
            "Casa",
            "Giochi",
            "Gelato",
            "Telefono",
            "Carta Regalo"};


    int[] iconeCategorieSpese = {R.drawable.coffe_cup,
            R.drawable.home_icon,
            R.drawable.joypad,
            R.drawable.ice_cream,
            R.drawable.smartphone,
            R.drawable.gift_card};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start Gestione ToolBar //
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        ImageView logoToolBar = (ImageView) toolbar.findViewById(R.id.toolbar_t_rex_icon);
        //End gestione Toolbar//

        //gestione Data, insert into toll
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE dd MMMM");
        SimpleDateFormat meseAnno = new SimpleDateFormat("MMMM yyyy");
        String giornoNumeroMese = df.format(c.getTime());
        String meseFormattato = meseAnno.format(c.getTime());
        TextView date  = (TextView) findViewById(R.id.tv_data_title);
        date.setText(giornoNumeroMese);
        TextView mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitleToolBar.setText(meseFormattato);

        //Gestione Griglia spese
        gridViewSpese = findViewById(R.id.gridViewSpese);
        GridViewSpeseAdapter gridViewSpeseAdapter = new GridViewSpeseAdapter(getApplicationContext(),nomeCategoriaSpese,iconeCategorieSpese);
        gridViewSpese.setAdapter(gridViewSpeseAdapter);

        gridViewSpese.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                //infalte layout
                View myView = getLayoutInflater().inflate(R.layout.pop_up_layout_inserisci,null);
                final TextView titoloSpesa = (TextView) myView.findViewById(R.id.titoloCatergoriaPassed);
                ImageView iconaCategoria = (ImageView) myView.findViewById(R.id.iconaSpesa);
                Button bt_conferma = (Button) myView.findViewById(R.id.btn_conferma);
                Button bt_annulla = (Button) myView.findViewById(R.id.btn_annulla);
                //settiamo i valori all'interno del popUp
                //dobbiamo passare, icona titolo
                titoloSpesa.setText(nomeCategoriaSpese[i]);
                iconaCategoria.setImageResource(iconeCategorieSpese[i]);
                bt_conferma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"Conferma",Toast.LENGTH_LONG).show();
                    }
                });

                bt_annulla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"Annulla",Toast.LENGTH_LONG).show();
                    }
                });

                mBuilder.setView(myView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });
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
                Toast.makeText(getApplicationContext(),"Home Page",Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_id_gestUscite:
                Intent goToGestioneUsciteActivity = new Intent(this,GestioneUsciteActivity.class);
                startActivity(goToGestioneUsciteActivity);
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

