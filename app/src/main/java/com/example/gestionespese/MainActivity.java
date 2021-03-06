package com.example.gestionespese;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gestionespese.adpter.GridViewSpeseAdapter;
import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    GridView gridViewSpese;
    final String TAG = " Activity = Home page";
    DataBaseHelper myDb;
    EditText importoSpesa;

    private ArrayList<String> nomeCategory = new ArrayList<String>();
    private ArrayList<Integer> idRisorsa = new ArrayList<Integer>();
    //sorgente d dati gridView
    //inserimento valori in griglia spese e icone

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);
        myDb = new DataBaseHelper(this);

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
        SimpleDateFormat df = new SimpleDateFormat("EEEE dd");
        SimpleDateFormat meseAnno = new SimpleDateFormat("MMMM yyyy");
        SimpleDateFormat formatoDataPerDb = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoOraPerDb = new SimpleDateFormat("HH:mm");
        String giornoNumeroMese = df.format(c.getTime());
        String meseFormattato = meseAnno.format(c.getTime());
        TextView date  = (TextView) findViewById(R.id.giorno_corrente);
        date.setText(giornoNumeroMese);
        TextView mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitleToolBar.setText(meseFormattato);
        final String dataEsattaDb = formatoDataPerDb.format(c.getTime());
        final String oraEsattaDb = formatoOraPerDb.format(c.getTime());

        //gestione importi
        double totUscite = myDb.getImportoTotaleSpese();
        DecimalFormat dfImport = new DecimalFormat("#.##"); // 2 cifre decimali
        TextView importoUscite = (TextView) findViewById(R.id.tv_uscite_value_home_page);
        String TotSpese = dfImport.format(totUscite);
        importoUscite.setText(TotSpese +"€");

        //GESTIONE CATEGORY HOME PAGE
        Cursor cursor = myDb.getCategoryFlagged();
        nomeCategory.clear();
        idRisorsa.clear();
        if (cursor.moveToFirst()) {
            do {
                nomeCategory.add(cursor.getString(cursor.getColumnIndex("NOME")));
                idRisorsa.add(cursor.getInt(cursor.getColumnIndex("ID_ICONA")));
            } while (cursor.moveToNext());
        }


        //Gestione Griglia spese
        gridViewSpese = findViewById(R.id.gridViewSpese);
        GridViewSpeseAdapter gridViewSpeseAdapter = new GridViewSpeseAdapter(getApplicationContext(),nomeCategory,idRisorsa);
        gridViewSpese.setAdapter(gridViewSpeseAdapter);
        gridViewSpese.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                //*******************POP_UP INSERIMETO SPESA *********************//
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                //infalte layout
                final View myView = getLayoutInflater().inflate(R.layout.pop_up_layout_inserisci,null);
                final TextView titoloSpesa = (TextView) myView.findViewById(R.id.titoloCatergoriaPassed);
                ImageView iconaCategoria = (ImageView) myView.findViewById(R.id.iconaSpesa);
                final EditText importo = (EditText) myView.findViewById(R.id.et_importoInserito);
                final EditText descrizione = (EditText) myView.findViewById(R.id.descrizioneEdiText);
                final Button bt_conferma = (Button) myView.findViewById(R.id.btn_conferma);
                final Button bt_annulla = (Button) myView.findViewById(R.id.btn_annulla);

                //settiamo i valori all'interno del popUp
                //dobbiamo passare, icona titolo
                titoloSpesa.setText(nomeCategory.get(i));
                iconaCategoria.setImageResource(idRisorsa.get(i));
                mBuilder.setView(myView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                bt_conferma.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(importo.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"Inserisci un Importo",Toast.LENGTH_LONG).show();
                        }
                        else{
                            final double importoInserito = Double.parseDouble(importo.getText().toString());
                            String descrizioneInsert = descrizione.getText().toString();
                            if(descrizioneInsert.isEmpty()){
                                descrizioneInsert = "nessuna descrizione presente";
                            }
                            boolean isInsert =  myDb.insertUscita(titoloSpesa.getText().toString(),importoInserito,descrizioneInsert,dataEsattaDb,oraEsattaDb);
                            if(isInsert = true){
                                Toast.makeText(getApplicationContext(),"inserimento riuscito",Toast.LENGTH_LONG).show();
                                double totUscite = myDb.getImportoTotaleSpese();

                                //gestione cifre decimali
                                DecimalFormat df = new DecimalFormat("#.##"); // 2 cifre decimali
                                String totaleSpeseFormattato = df.format(totUscite);
                                TextView importoUscite = (TextView) findViewById(R.id.tv_uscite_value_home_page);
                                importoUscite.setText(totaleSpeseFormattato+"€");
                                dialog.cancel();
                            }
                        }

                    }
                });

                bt_annulla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

            }
        });
    }


    public void insertUscitaButton(View view) {
        Toast.makeText(getApplicationContext(),"insertUscita",Toast.LENGTH_LONG).show();

    }

    public void inserisciEntrataButton(View view) {
        Toast.makeText(getApplicationContext(),"InsetisciEntrata",Toast.LENGTH_LONG).show();
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
                Intent goToGestCategorieActivity = new Intent(this,GestioneCategoryActivity.class);
                startActivity(goToGestCategorieActivity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}

