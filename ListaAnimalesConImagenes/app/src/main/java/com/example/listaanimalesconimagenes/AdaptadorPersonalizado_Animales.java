package com.example.listaanimalesconimagenes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class AdaptadorPersonalizado_Animales extends ArrayAdapter {

    private Activity context;
    private String[] arrayAnimales;
    private String[] animalesInfo;
    private TypedArray colores;
    private TypedArray arrayIdFotosAnimales;
    private int layout_personalizado;

    public AdaptadorPersonalizado_Animales(@NonNull Activity context,
                                           int layout_personalizado,
                                           String[] arrayAnimales,
                                           TypedArray arrayIdFotosAnimales,
                                           String[] animalesInfo,
                                           TypedArray colores) {
        super(context, layout_personalizado, arrayAnimales);
        this.context = context;
        this.layout_personalizado = layout_personalizado;
        this.arrayAnimales = arrayAnimales;
        this.arrayIdFotosAnimales = arrayIdFotosAnimales;
        this.animalesInfo = animalesInfo;
        this.colores = colores;

    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        //Inflamos nuestro layoutpersonalizado
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(layout_personalizado, null);

        //captuaramos los ids

        TextView tvAnimales = fila.findViewById(R.id.tv_animales);
        TextView tvinfo = fila.findViewById(R.id.tv_infoAnimales);
        ImageView imgAnimales = fila.findViewById(R.id.img_animales);
        ImageView imgColores = fila.findViewById(R.id.img_colores);
        LinearLayout linearCambiarFondo = fila.findViewById(R.id.ll_cambiar_color);

        tvAnimales.setText(arrayAnimales[position]);
        imgAnimales.setImageDrawable(arrayIdFotosAnimales.getDrawable(position));
        tvinfo.setText(animalesInfo[position]);

        //imgColores.setImageDrawable(colores.getDrawable(position));

        //Todo cambiar background
        int colorFondo =  position % 2 == 0 ? R.color.celeste : R.color.celesteclaro;
        int colorB = ContextCompat.getColor(context, colorFondo);
        linearCambiarFondo.setBackgroundColor(colorB);

        // Obtener el índice del color de forma cíclica
        int indiceColor = position % colores.length();
        imgColores.setImageDrawable(colores.getDrawable(indiceColor));

        return fila;
    }
}
