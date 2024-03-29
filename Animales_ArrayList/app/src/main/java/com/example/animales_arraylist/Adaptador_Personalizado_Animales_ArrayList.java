package com.example.animales_arraylist;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador_Personalizado_Animales_ArrayList extends ArrayAdapter {


    private Activity context;
    private ArrayList<Animales>  listaAnimales;

    private int layoutPersonalizado;

    public Adaptador_Personalizado_Animales_ArrayList(
            @NonNull Activity context,// --> SE USA MAS ABAJOO EN getView
            int layoutPersonalizado, // --> SE USA MAS ABAJOO EN getView
            ArrayList<Animales> listaAnimales)  // --> SE USA MAS ABAJOO EN getView
    {
        //Constructor defectivo de la clase arrayadapter
        super(context, layoutPersonalizado, listaAnimales);

        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
        this.listaAnimales = listaAnimales;
    }

    //TODO CREAMOS UN METODO VIEWHOLDER QUE LO USAREMOS COMO CONTENEDOR DE DATOS
    //Todo es un wrapper alrededor de una view ue contiene el diseño de un elemento individual de la lista
    private static class ViewHolder {
        TextView tv_animales;
        TextView tv_infoAnimales;
        ImageView img_animales;
        ImageView img_colores;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        //Todo Optimizamos el inflado
        if(fila==null){
            //Inflamos el layout personalizado (layout_personalizado_animales.xml)
            LayoutInflater layoutInflater = context.getLayoutInflater();
            fila = layoutInflater.inflate(layoutPersonalizado, null);

            holder = new ViewHolder();
            holder.tv_animales = fila.findViewById(R.id.tv_animales);
            holder.tv_infoAnimales = fila.findViewById(R.id.tv_infoAnimales);
            holder.img_animales = fila.findViewById(R.id.img_animales);
            holder.img_colores = fila.findViewById(R.id.img_colores);


            fila.setTag(holder);//Guardamos los atributos dentro del holder
        }else{
            holder = (ViewHolder) fila.getTag();
        }
        holder.tv_animales.setText(listaAnimales.get(position).getNombreAnimales());
        holder.tv_infoAnimales.setText(listaAnimales.get(position).getInfo());
        holder.img_animales.setImageResource(listaAnimales.get(position).getImagen_Animales());
        holder.img_colores.setImageResource(listaAnimales.get(position).getColores_Animales());

        return fila;

    }























}
