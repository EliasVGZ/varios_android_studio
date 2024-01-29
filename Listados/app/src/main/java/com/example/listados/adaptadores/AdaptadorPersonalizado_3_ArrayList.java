package com.example.listados.adaptadores;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listados.R;
import com.example.listados.clases.Planetas;

import java.util.ArrayList;

public class AdaptadorPersonalizado_3_ArrayList extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<Planetas> listadoPlanetas;
    private final int layout_personalizado;

    public AdaptadorPersonalizado_3_ArrayList(
            @NonNull Activity context,// --> SE USA MAS ABAJOO EN getView
            int layout_personalizado, // --> SE USA MAS ABAJOO EN getView
            ArrayList<Planetas> listadoPlanetas)  // --> SE USA MAS ABAJOO EN getView
    {
        //Constructor defectivo de la clase arrayadapter
        super(context, layout_personalizado, listadoPlanetas);

        this.context = context;
        this.layout_personalizado = layout_personalizado;
        this.listadoPlanetas = listadoPlanetas;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Inflamos nuestro layout personalizado (fila_diferentes_imagenes_y_texto.xml)
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(layout_personalizado, null);

        // Obtenemos referencias a los elementos de la fila
        TextView tvPlanetas = fila.findViewById(R.id.tv_planeta);
        ImageView imgPlanetas = fila.findViewById(R.id.img_planetas);

        // Configuramos los valores en los elementos de la fila
        tvPlanetas.setText(listadoPlanetas.get(position).getNombre());
        imgPlanetas.setImageResource(listadoPlanetas.get(position).getImagen());

        return fila;
    }





}
