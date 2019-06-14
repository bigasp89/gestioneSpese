package com.example.gestionespese.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.gestionespeses.R;

public class GridViewImgAdapter extends BaseAdapter {
    Context contex;
    private int[] idRisorsaImg;
    LayoutInflater inflater;

    private static class ViewHolder{
        ImageView idRisorsaImg;
    }

    public GridViewImgAdapter(Context appContext, int[] idRisorsaImg){
        this.contex = appContext;
        this.idRisorsaImg = idRisorsaImg;
        inflater = (LayoutInflater.from(appContext));

    }

    @Override
    public int getCount() {
        return idRisorsaImg.length;
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
            convertView = inflater.inflate(R.layout.row_data_img_grid,null);
            viewHolder = new ViewHolder();
            viewHolder.idRisorsaImg = (ImageView)convertView.findViewById(R.id.imagesCategoriaSpese);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.idRisorsaImg.setImageResource(idRisorsaImg[position]);

        return convertView;
    }

}
