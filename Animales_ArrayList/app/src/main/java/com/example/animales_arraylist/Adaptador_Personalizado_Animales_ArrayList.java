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


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        //Inflamos nuestro layout personalizado (fila_diferentes_imagenes_y_texto.xml)
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(layoutPersonalizado, null);

        TextView tv_animales = fila.findViewById(R.id.tv_animales);
        tv_animales.setText(listaAnimales.get(position).getNombreAnimales());

        TextView tv_infoAnimales = fila.findViewById(R.id.tv_infoAnimales);
        tv_infoAnimales.setText(listaAnimales.get(position).getInfo());

        ImageView img_animales = fila.findViewById(R.id.img_animales);
        img_animales.setImageResource(listaAnimales.get(position).getImagen_Animales());

        ImageView img_colores = fila.findViewById(R.id.img_colores);
        img_colores.setImageResource(listaAnimales.get(position).getColores_Animales());

        return fila;


    }























}
