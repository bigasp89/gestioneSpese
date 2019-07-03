package com.example.gestionespese.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionespese.object.Entrate;
import com.example.gestionespeses.R;

import java.util.List;

public class GestioneEntrateAdapter extends ArrayAdapter<Entrate> {

    public GestioneEntrateAdapter(Context context, int textViewResourceId,
                                  List<Entrate> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_entrate, null);

        ImageView img = (ImageView)convertView.findViewById(R.id.iv_icon_category);
        TextView titolo = (TextView)convertView.findViewById(R.id.tv_name_category);
        TextView descrizione = (TextView)convertView.findViewById(R.id.tv_descr_category);
        TextView dataInserimento = (TextView)convertView.findViewById(R.id.tv_data);
        TextView oraInserimento = (TextView)convertView.findViewById(R.id.tv_ora);
        TextView importoEntrata = (TextView)convertView.findViewById(R.id.tv_importo_entrata);

        Entrate c = getItem(position);

        img.setImageResource(R.drawable.entrata);
        titolo.setText(c.getTitolo());
        descrizione.setText(c.getDescrizione());
        dataInserimento.setText(c.getData());
        oraInserimento.setText(c.getOra());
        String importoFromDouble = String.valueOf(c.getImporto());
        importoEntrata.setText(importoFromDouble + " €");
        return convertView;
    }

}