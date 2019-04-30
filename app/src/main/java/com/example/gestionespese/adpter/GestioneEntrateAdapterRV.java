package com.example.gestionespese.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;
import java.util.ArrayList;

public class GestioneEntrateAdapterRV extends RecyclerView.Adapter<GestioneEntrateAdapterRV.MyViewHolder> {
    Context context;
    private ArrayList<String> idEntrata = new ArrayList<String>();
    private ArrayList<String> nomeEntrata = new ArrayList<String>();
    private ArrayList<String> importo = new ArrayList<String>();
    private ArrayList<String> descrizione = new ArrayList<String>();
    int[] iconaCestino;
    int [] infoIcon;
    LayoutInflater inflater;
    DataBaseHelper myDb;

    public GestioneEntrateAdapterRV(Context context,ArrayList<String> idEntrata,ArrayList<String> nomeEntrata,
                                  ArrayList<String> importo,ArrayList<String> descrizione,int[] iconaCestino,int [] infoIcon) {

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_entrate,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.idEntrata.setText(idEntrata.get(position));
        holder.nomeEntrata.setText(nomeEntrata.get(position));
        holder.importo.setText(importo.get(position));
        holder.descrizione.setText(descrizione.get(position));
        holder.iconaCestino.setImageResource(R.drawable.cestino);
        holder.infoIcon.setImageResource(R.drawable.info_icon);


        holder.iconaCestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb = new DataBaseHelper(context.getApplicationContext());
                int id = Integer.parseInt(idEntrata.get(position));
                boolean removeItem = myDb.deleteRawEntrate(id);
                if(removeItem){
                    Toast.makeText(context.getApplicationContext(),"eliminazione avvenuta con successo",Toast.LENGTH_LONG).show();
                    idEntrata.remove(position);
                    notifyDataSetChanged();
                }
                else{
                    Toast.makeText(context.getApplicationContext(),"errore",Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.infoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(),"info",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return idEntrata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView idEntrata;
        TextView nomeEntrata;
        TextView importo;
        TextView descrizione;
        ImageView iconaCestino;
        ImageView infoIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            idEntrata = (TextView) itemView.findViewById(R.id.idEntrata);
            nomeEntrata = (TextView) itemView.findViewById(R.id.idNome);
            importo = (TextView) itemView.findViewById(R.id.idImporto);
            descrizione = (TextView) itemView.findViewById(R.id.idDescrizione);
            iconaCestino = (ImageView) itemView.findViewById(R.id.cestino);
            infoIcon = (ImageView) itemView.findViewById(R.id.idInfoImage);
        }
    }

    private void deleteItem(int position) {

    }
}
