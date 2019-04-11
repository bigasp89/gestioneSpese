package com.example.gestionespese;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.gestionespeses.R;

public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);
        //gestiamo la dimensione del pop_up
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int larghezza = dm.widthPixels;
        int altezza = dm.heightPixels;

        getWindow().setLayout((int)(larghezza*.8), (int) (altezza*.6));
    }
}
