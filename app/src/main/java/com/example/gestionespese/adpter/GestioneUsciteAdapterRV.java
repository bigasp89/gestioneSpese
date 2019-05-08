package com.example.gestionespese.adpter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionespese.database.DataBaseHelper;
import com.example.gestionespeses.R;
import java.util.ArrayList;

public class GestioneUsciteAdapterRV extends RecyclerView.Adapter<GestioneUsciteAdapterRV.MyViewHolder> {
    Context context;
    private ArrayList<String> idEntrata = new ArrayList<String>();
    private ArrayList<String> nomeEntrata = new ArrayList<String>();
    private ArrayList<String> importo = new ArrayList<String>();
    private ArrayList<String> descrizione = new ArrayList<String>();
    private ArrayList<String> dataEsatta = new ArrayList<String>();
    private ArrayList<String> oraEsatta = new ArrayList<String>();
    int[] iconaCestino;
    int [] infoIcon;
    int [] iconaEdit;
    LayoutInflater inflater;
    DataBaseHelper myDb;
    String descrizioneItem;

    public GestioneUsciteAdapterRV(Context context, ArrayList<String> idEntrata, ArrayList<String> nomeEntrata,
                                   ArrayList<String> importo, ArrayList<String> descrizione, int[] iconaCestino,
                                   int [] infoIcon, int[] iconaEdit, ArrayList<String> dataEsatta, ArrayList<String> oraEsatta) {
        this.context = context;
        this.idEntrata = idEntrata;
        this.nomeEntrata = nomeEntrata;
        this.importo = importo;
        this.descrizione=descrizione;
        this.dataEsatta = dataEsatta;
        this.oraEsatta = oraEsatta;
        this.iconaCestino = iconaCestino;
        this.infoIcon = infoIcon;
        this.iconaEdit = iconaEdit;
        inflater = (LayoutInflater.from(context));
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_uscite,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.nomeEntrata.setText(nomeEntrata.get(position));
        holder.importo.setText(importo.get(position));
        holder.dataEsatta.setText(dataEsatta.get(position));
        holder.oraEsatta.setText(oraEsatta.get(position));
        holder.iconaCestino.setImageResource(R.drawable.cestino);
        holder.infoIcon.setImageResource(R.drawable.info_icon);
        holder.iconaEdit.setImageResource(R.drawable.edit);

        //DELETE ITEM
        holder.iconaCestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder EditDialogBuilder = new AlertDialog.Builder(view.getRootView().getContext());
                final View myView = inflater.inflate(R.layout.pop_up_layout_delete,null);
                final Button btn_remove = (Button) myView.findViewById(R.id.btn_remove);
                final Button btn_annullaModifica = (Button) myView.findViewById(R.id.btn_annullaRemove);
                EditDialogBuilder.setView(myView);
                final AlertDialog dialog = EditDialogBuilder.create();
                dialog.show();
                btn_annullaModifica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                btn_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    myDb = new DataBaseHelper(context.getApplicationContext());
                    int id = Integer.parseInt(idEntrata.get(position));
                        boolean removeItem = myDb.deleteRawEntrate(id);
                        if(removeItem){
                            Toast.makeText(context.getApplicationContext(),"eliminazione avvenuta con successo",Toast.LENGTH_LONG).show();
                            idEntrata.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,getItemCount());
                        }
                        else{
                            Toast.makeText(context.getApplicationContext(),"ops!!! qualcosa è andato storto",Toast.LENGTH_LONG).show();
                        }
                        dialog.cancel();
                    }
                });
            }
        });

        //DESCRIZIONE ITEM
        holder.infoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb = new DataBaseHelper(context.getApplicationContext());
                int id = Integer.parseInt(idEntrata.get(position));
                String descrizione = myDb.getDescrizioneFromId(id);

                AlertDialog.Builder EditDialogBuilder = new AlertDialog.Builder(view.getRootView().getContext());
                final View myView = inflater.inflate(R.layout.pop_up_layout_description,null);
                final TextView fullDescription = (TextView) myView.findViewById(R.id.descriptionContenent);
                fullDescription.setText(descrizione);
                EditDialogBuilder.setView(myView);
                final AlertDialog dialog = EditDialogBuilder.create();
                dialog.show();
            }
        });

        //MODIFICA ITEM
        holder.iconaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder EditDialogBuilder = new AlertDialog.Builder(view.getRootView().getContext());
                final View myView = inflater.inflate(R.layout.pop_up_layout_modfica,null);

                final TextView titoloCategoriaModify = (TextView) myView.findViewById(R.id.titoloCategoriaModify);
                ImageView iconaCategoria = (ImageView) myView.findViewById(R.id.iconaSpesa);
                final EditText importoModify = (EditText) myView.findViewById(R.id.et_modificaImporto);
                final EditText descrizioneModify = (EditText) myView.findViewById(R.id.descrizioneEdiText);
                final Button btn_modifica = (Button) myView.findViewById(R.id.btn_modifica);
                final Button btn_annullaModifica = (Button) myView.findViewById(R.id.btn_annullaModifica);
                //settiamo i valori all'interno del popUp
                //dobbiamo passare, icona titolo
                myDb = new DataBaseHelper(context.getApplicationContext());
                int id = Integer.parseInt(idEntrata.get(position));
                String descrizione = myDb.getDescrizioneFromId(id);
                int importo = myDb.getImportoFromId(id);
                String importoToString = String.valueOf(importo);
                titoloCategoriaModify.setText(nomeEntrata.get(position));
                importoModify.setText(importoToString);
                if(descrizione.equals("nessuna descrizione presente")){
                    descrizioneModify.setText("");
                }else{
                    descrizioneModify.setText(descrizione);
                }

                EditDialogBuilder.setView(myView);
                final AlertDialog dialog = EditDialogBuilder.create();
                dialog.show();
                btn_modifica.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(importoModify.getText().toString().isEmpty()){
                            Toast.makeText(view.getRootView().getContext(),"Inserisci un Importo",Toast.LENGTH_LONG).show();
                        }else{
                            final int importoInseritoModify = Integer.parseInt(importoModify.getText().toString());
                            String descrizioneInsertModify = descrizioneModify.getText().toString();
                            if(descrizioneInsertModify.isEmpty()){
                                descrizioneInsertModify="nessuna Descrizione presente";
                            }
                            myDb = new DataBaseHelper(context.getApplicationContext());
                            boolean updateData =  myDb.updateDataValue(idEntrata.get(position),importoInseritoModify,descrizioneInsertModify);
                            if(updateData = true){
                                holder.importo.setText(String.valueOf(importoInseritoModify));
                                holder.importo.invalidate();
                                dialog.cancel();
                            }
                        }
                    }
                });
                btn_annullaModifica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

            }
        });


    }
    @Override
    public int getItemCount() {
        return idEntrata.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nomeEntrata;
        TextView importo;
        TextView dataEsatta;
        TextView oraEsatta;
        ImageView iconaCestino;
        ImageView infoIcon;
        ImageView iconaEdit;


        public MyViewHolder(View itemView) {
            super(itemView);
            nomeEntrata = (TextView) itemView.findViewById(R.id.idNome);
            importo = (TextView) itemView.findViewById(R.id.idImporto);
            dataEsatta = (TextView) itemView.findViewById(R.id.idDataValue);
            oraEsatta = (TextView) itemView.findViewById(R.id.idOraValue);
            iconaCestino = (ImageView) itemView.findViewById(R.id.cestino);
            infoIcon = (ImageView) itemView.findViewById(R.id.idInfoImage);
            iconaEdit = (ImageView) itemView.findViewById(R.id.editImage);
        }
    }



}
