package com.example.gestionespese.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gestionespeses.R;

public class GridViewSpeseAdapter  extends BaseAdapter {
    Context contex;
    String[] nomeCategoriaSpesa;
    int[] iconaCategoriaSpesa;
    LayoutInflater inflater;

    private static class ViewHolder{
        TextView nomeCategoria;
        ImageView iconaCategoria;
    }

    public GridViewSpeseAdapter(Context appContext,String[] nomeCategoriaSpese, int[] iconaCategoriaSpese){
        this.contex = appContext;
        this.nomeCategoriaSpesa = nomeCategoriaSpese;
        this.iconaCategoriaSpesa = iconaCategoriaSpese;
        inflater = (LayoutInflater.from(appContext));

    }

    @Override
    public int getCount() {
        return nomeCategoriaSpesa.length;
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
            viewHolder.nomeCategoria = (TextView)convertView.findViewById(R.id.nomeCategoriaSpese);
            viewHolder.iconaCategoria = (ImageView)convertView.findViewById(R.id.imagesCategoriaSpese);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.nomeCategoria.setText(nomeCategoriaSpesa[position]);
        viewHolder.iconaCategoria.setImageResource(iconaCategoriaSpesa[position]);

        return convertView;
    }

}
