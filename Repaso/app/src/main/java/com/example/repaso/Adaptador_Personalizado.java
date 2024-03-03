package com.example.repaso;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador_Personalizado extends ArrayAdapter {

    private Activity context;
    private ArrayList<Alumnos> listaAlumnos;
    private int layoutPersonalizado;


    public Adaptador_Personalizado(
            @NonNull Activity context,
            int layoutPersonalizado,
            ArrayList<Alumnos> listaAlumnos) {
        super(context, layoutPersonalizado, listaAlumnos);

        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
        this.listaAlumnos = listaAlumnos;
    }

    //TODO CREAMOS UN METODO VIEWHOLDER QUE LO USAREMOS COMO CONTENEDOR DE DATOS
    //Todo es un wrapper alrededor de una view ue contiene el diseño de un elemento individual de la lista
    private static class ViewHolder {
        TextView tv_nombre;
        TextView tv_curso;
        TextView tv_ciclo;
        ImageView img_alumnos;

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
            holder.tv_nombre = fila.findViewById(R.id.tv_nombre);
            holder.tv_curso = fila.findViewById(R.id.tv_curso);
            holder.tv_ciclo = fila.findViewById(R.id.tv_ciclo);
            holder.img_alumnos = fila.findViewById(R.id.img_alumnos);


            fila.setTag(holder);//Guardamos los atributos dentro del holder
        }else{
            holder = (ViewHolder) fila.getTag();
        }
        /*Alumnos alumnoActual = listaAlumnos.get(position);
        holder.tv_nombre.setText(alumnoActual.getNombre());
        holder.tv_curso.setText(alumnoActual.getCurso());
        holder.tv_ciclo.setText(alumnoActual.getCiclo());
        holder.img_alumnos.setImageResource(alumnoActual.getImagen());*/
        holder.tv_nombre.setText(listaAlumnos.get(position).getNombre());
        holder.tv_curso.setText(listaAlumnos.get(position).getCurso());
        holder.tv_ciclo.setText(listaAlumnos.get(position).getCiclo());
        holder.img_alumnos.setImageResource(listaAlumnos.get(position).getImagen());

        return fila;

    }
}
