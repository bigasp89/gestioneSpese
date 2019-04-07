package com.example.gestionespese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionespeses.R;

public class MainActivity extends AppCompatActivity {

    final String TAG = " Activity = Home page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitleToolBar = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView logoToolBar = (ImageView) toolbar.findViewById(R.id.toolbar_t_rex_icon);

    }

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
}
