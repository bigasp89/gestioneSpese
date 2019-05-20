package com.example.gestionespese.adpter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;

import java.util.ArrayList;

public class GestioneCategoryAdapterRV extends RecyclerView.Adapter<GestioneCategoryAdapterRV.MyViewHolder> {
    Context context;
    private ArrayList<String> idCategory = new ArrayList<String>();
    private ArrayList<String> nomeCategory = new ArrayList<String>();
    private ArrayList<String> flagCategory = new ArrayList<String>();
    LayoutInflater inflater;
    int[] iconaCestino;


    public GestioneCategoryAdapterRV(Context context, ArrayList<String> idCategory, ArrayList<String> nomeCategory,
                                     ArrayList<String> flagCategory,int[] iconaCestino) {
        this.context = context;
        this.idCategory = idCategory;
        this.nomeCategory = nomeCategory;
        this.flagCategory = flagCategory;
        this.iconaCestino = iconaCestino;
        inflater = (LayoutInflater.from(context));
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_category,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.idCategory.setText(idCategory.get(position));
        holder.nomeCategory.setText(nomeCategory.get(position));
        holder.flagCategory.setText(flagCategory.get(position));
        if(holder.flagCategory.getText().equals("Y")){
            holder.selectionState.setChecked(true);
        }
        else{
            holder.selectionState.setChecked(false);
        }
        holder.iconaCestino.setImageResource(R.drawable.cestino);
    }


    @Override
    public int getItemCount() {
        return idCategory.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView idCategory;
        TextView nomeCategory;
        TextView flagCategory;
        ImageView iconaCestino;
        public CheckBox selectionState;

        public MyViewHolder(View itemView) {
            super(itemView);
            idCategory = (TextView) itemView.findViewById(R.id.tv_id_category);
            nomeCategory = (TextView) itemView.findViewById(R.id.tv_id_nomeCateogry);
            flagCategory = (TextView) itemView.findViewById(R.id.tv_id_flgCategory);
            iconaCestino = (ImageView) itemView.findViewById(R.id.cestinoCategory);
            selectionState = (CheckBox) itemView.findViewById(R.id.brand_select);
        }
    }



}
