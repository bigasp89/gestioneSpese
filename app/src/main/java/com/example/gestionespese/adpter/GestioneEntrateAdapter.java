package com.example.gestionespese.adpter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionespeses.R;

import java.util.ArrayList;

public class GestioneEntrateAdapter extends BaseAdapter {
    Context context;
    SQLiteDatabase myDb;
    private ArrayList<String> idEntrata = new ArrayList<String>();
    private ArrayList<String> nomeEntrata = new ArrayList<String>();
    private ArrayList<String> importo = new ArrayList<String>();
    private ArrayList<String> descrizione = new ArrayList<String>();
    int[] iconaCestino;
    int [] infoIcon;
    LayoutInflater inflater;

    private static class ViewHolder{
        TextView nomeEntrata;
        TextView importo;
        TextView descrizione;
        TextView idEntrata;
        ImageView iconaCestino;
        ImageView iconaInfo;
    }




    public GestioneEntrateAdapter(Context context,ArrayList<String> idEntrata,ArrayList<String> nomeEntrata,
                                  ArrayList<String> importo,ArrayList<String> descrizione, int[] iconaCestino,
                                  int [] infoIcon)
    {
        this.context = context;
        this.idEntrata = idEntrata;
        this.nomeEntrata = nomeEntrata;
        this.importo = importo;
        this.descrizione=descrizione;
        this.iconaCestino = iconaCestino;
        this.infoIcon = infoIcon;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return idEntrata.size();
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(convertView ==null){
            convertView = inflater.inflate(R.layout.item_entrate,null);
            viewHolder = new GestioneEntrateAdapter.ViewHolder();
            viewHolder.idEntrata = (TextView) convertView.findViewById(R.id.idEntrata);
            viewHolder.nomeEntrata = (TextView)convertView.findViewById(R.id.idNome);
            viewHolder.importo = (TextView) convertView.findViewById(R.id.idImporto);
            viewHolder.descrizione = (TextView) convertView.findViewById(R.id.idDescrizione);
            viewHolder.iconaCestino = (ImageView) convertView.findViewById(R.id.cestino);
            viewHolder.iconaInfo = (ImageView) convertView.findViewById(R.id.idInfoImage);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.idEntrata.setText(idEntrata.get(i));
        viewHolder.nomeEntrata.setText(nomeEntrata.get(i));
        viewHolder.importo.setText(importo.get(i));
        viewHolder.descrizione.setText(descrizione.get(i));
        viewHolder.iconaCestino.setImageResource(iconaCestino[0]);
        viewHolder.iconaInfo.setImageResource(infoIcon[0]);
        return convertView;
    }


}
