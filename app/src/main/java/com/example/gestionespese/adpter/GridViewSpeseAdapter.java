package com.example.gestionespese.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gestionespeses.R;

import java.util.ArrayList;

public class GridViewSpeseAdapter  extends BaseAdapter {
    Context contex;
    private ArrayList<String> nomeCategory = new ArrayList<String>();
    private ArrayList<Integer> idRisorsa = new ArrayList<Integer>();
    LayoutInflater inflater;

    private static class ViewHolder{
        TextView nomeCategory;
        ImageView idRisorsa;
    }

    public GridViewSpeseAdapter(Context appContext,ArrayList<String> nomeCategory, ArrayList<Integer> idRisorsa){
        this.contex = appContext;
        this.nomeCategory = nomeCategory;
        this.idRisorsa = idRisorsa;
        inflater = (LayoutInflater.from(appContext));

    }

    @Override
    public int getCount() {
        return nomeCategory .size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView ==null){
            convertView = inflater.inflate(R.layout.row_data,null);
            viewHolder = new ViewHolder();
            viewHolder.nomeCategory = (TextView)convertView.findViewById(R.id.nomeCategoriaSpese);
            viewHolder.idRisorsa = (ImageView)convertView.findViewById(R.id.imagesCategoriaSpese);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.nomeCategory.setText(nomeCategory.get(position));
        viewHolder.idRisorsa.setImageResource(idRisorsa.get(position));

        return convertView;
    }

}
