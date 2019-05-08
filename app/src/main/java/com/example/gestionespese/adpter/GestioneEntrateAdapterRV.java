package com.example.gestionespese.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionespeses.R;


public class GestioneEntrateAdapterRV extends RecyclerView.Adapter<GestioneEntrateAdapterRV.MyViewHolder>{

    Context contex;
    String[] nomeCategoriaSpesa;
    int[] iconaCategoriaSpesa;
    LayoutInflater inflater;


    public GestioneEntrateAdapterRV(Context appContext, String[] nomeCategoriaSpesa, int[] iconaCategoriaSpesa){
        this.contex = appContext;
        this.nomeCategoriaSpesa = nomeCategoriaSpesa;
        this.iconaCategoriaSpesa = iconaCategoriaSpesa;
        inflater = (LayoutInflater.from(appContext));
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_entrate,parent,false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nomeCategoriaSpese.setText(nomeCategoriaSpesa[position]);
        holder.iconaCategoriaSpese.setImageResource(iconaCategoriaSpesa[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //toast
            }
        });
    }

    @Override
    public int getItemCount() {
        return nomeCategoriaSpesa.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nomeCategoriaSpese;
        ImageView iconaCategoriaSpese;
        public MyViewHolder(View itemView) {
            super(itemView);
           nomeCategoriaSpese = (TextView)itemView.findViewById(R.id.tv_name_category);
           iconaCategoriaSpese = (ImageView)itemView.findViewById(R.id.iv_icon_category);
        }
    }



}
