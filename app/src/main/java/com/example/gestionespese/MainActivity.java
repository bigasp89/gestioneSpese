package com.example.gestionespese;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gestionespeses.R;
import android.widget.GridView;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    final String TAG = " Activity = Home page";
    String[] nomeCategoriaSpese = {"Caffè","Casa"};
    int[] iconeCategorieSpese = {R.drawable.coffe_cup,R.drawable.home_icon};

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
        TextView mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView logoToolBar = (ImageView) toolbar.findViewById(R.id.toolbar_t_rex_icon);
        //End gestione Toolbar//



        //Gestione griglia categoria spese///
        //finding listview
        gridView = findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",nomeCategoriaSpese[i]);
                intent.putExtra("image",iconeCategorieSpese[i]);
                startActivity(intent);
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
        int idVoce =item.getItemId();
        String nome= (String) item.getTitle();

        Log.i(TAG,"clickVoceMenu "+ nome);

        return super.onOptionsItemSelected(item);
    }

     // END Gestione Menu e sue voci //



    //TODO SPOSTARE IN UNA ALTRA CLASSE
    //CUSTOM aDAPTER
    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return nomeCategoriaSpese.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.nomeCategoriaSpese);
            ImageView image = view1.findViewById(R.id.imagesCategoriaSpese);

            name.setText(nomeCategoriaSpese[i]);
            image.setImageResource(iconeCategorieSpese[i]);
            return view1;

        }
    }
}

