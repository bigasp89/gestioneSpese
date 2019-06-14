package com.example.gestionespese.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;

import java.util.ArrayList;

public class GestioneCategoryAdapterRV extends RecyclerView.Adapter<GestioneCategoryAdapterRV.MyViewHolder> {
    Context context;
    private ArrayList<String> idCategory = new ArrayList<String>();
    private ArrayList<String> nomeCategory = new ArrayList<String>();
    private ArrayList<String> flagCategory = new ArrayList<String>();
    private ArrayList<Integer> idRisorsa = new ArrayList<Integer>();
    LayoutInflater inflater;
    int[] iconaCestino;
    DataBaseHelper myDb;



    public GestioneCategoryAdapterRV(Context context, ArrayList<String> idCategory, ArrayList<String> nomeCategory,
                                     ArrayList<String> flagCategory,int[] iconaCestino,ArrayList<Integer> idRisorsa) {
        this.context = context;
        this.idCategory = idCategory;
        this.nomeCategory = nomeCategory;
        this.flagCategory = flagCategory;
        this.iconaCestino = iconaCestino;
        this.idRisorsa = idRisorsa;
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
        holder.nomeCategory.setText(nomeCategory.get(position));
        holder.flagCategory.setText(flagCategory.get(position));
        if(holder.flagCategory.getText().equals("Y")){
            holder.selectionState.setChecked(true);
        }
        else{
            holder.selectionState.setChecked(false);
        }
        holder.iconaCestino.setImageResource(R.drawable.cestino);
        holder.idRisorsa.setImageResource(idRisorsa.get(position));

        holder.selectionState.setOnClickListener(new View.OnClickListener() {
            @Override
            //questo metodo funge
            //TODO CAMBIARE LO STATO SU DB AL CLICK
            public void onClick(View view) {
                myDb = new DataBaseHelper(context.getApplicationContext());
                //id,nome,flag,risorsaImg
                String idCat = idCategory.get(position);
                String nomeCat = nomeCategory.get(position);
                int idImg = idRisorsa.get(position);
                String flgVal = "";
                if(((CompoundButton) view).isChecked()){
                    flgVal = "Y";
                    System.out.println("Checked");
                } else {
                    System.out.println("Un-Checked");
                    flgVal = "N";
                }
                boolean updateDataFlag = myDb.updateFlagValueCategory(idCat,nomeCat,flgVal,idImg);
            }
        });
    }


    @Override
    public int getItemCount() {
        return idCategory.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nomeCategory;
        TextView flagCategory;
        ImageView iconaCestino;
        ImageView idRisorsa;
        public CheckBox selectionState;

        public MyViewHolder(View itemView) {
            super(itemView);
            nomeCategory = (TextView) itemView.findViewById(R.id.tv_id_nomeCateogry);
            flagCategory = (TextView) itemView.findViewById(R.id.tv_id_flgCategory);
            iconaCestino = (ImageView) itemView.findViewById(R.id.cestinoCategory);
            idRisorsa = (ImageView) itemView.findViewById(R.id.iconaCategoria);
            selectionState = (CheckBox) itemView.findViewById(R.id.brand_select);
        }
    }
}
