package com.example.gestionespese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionespeses.R;


//Receiving and displaying clicked items//
/*
We are going to use intents to send and receive clicked item.
To send data between activities through intent we use
 */

/*
intent.putExtra("name",fruitNames[i]);
intent.putExtra("image",fruitImages[i])
 */
// intent.getStringExtra("name");
// intent.getIntExtra("image",0);

public class GridItemActivity extends AppCompatActivity {

    TextView gridData;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        gridData = findViewById(R.id.griddata);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String receivedName =  intent.getStringExtra("name");
        int receivedImage = intent.getIntExtra("image",0);

        gridData.setText(receivedName);
        imageView.setImageResource(receivedImage);
        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}