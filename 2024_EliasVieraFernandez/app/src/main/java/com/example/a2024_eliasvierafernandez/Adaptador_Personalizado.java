package com.example.a2024_eliasvierafernandez;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador_Personalizado extends ArrayAdapter {

    private Activity context;
    private ArrayList<Quimicos> listaQuimicos;
    private int layoutPersonalizado;

    public Adaptador_Personalizado(
            @NonNull Activity context,
            int layoutPersonalizado,
            ArrayList<Quimicos> listaQuimicos) {
        super(context, layoutPersonalizado, listaQuimicos);

        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
        this.listaQuimicos = listaQuimicos;
    }

    private static class ViewHolder {

        TextView tv_nombreCompuesto;
        ImageView iv_imagen;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        //Todo Optimizamos el inflado
        if(fila==null){
            //Inflamos el layout personalizado
            LayoutInflater layoutInflater = context.getLayoutInflater();
            fila = layoutInflater.inflate(layoutPersonalizado, null);

            holder = new ViewHolder();

            holder.iv_imagen = fila.findViewById(R.id.iv_imagen);
            holder.tv_nombreCompuesto = fila.findViewById(R.id.tv_nombreCompuesto);


            fila.setTag(holder);//Guardamos los atributos dentro del holder
        }else{
            holder = (ViewHolder) fila.getTag();
        }


        holder.tv_nombreCompuesto.setText(listaQuimicos.get(position).getCompuesto());
        holder.iv_imagen.setImageResource(listaQuimicos.get(position).getImagen());

        return fila;

    }
}
