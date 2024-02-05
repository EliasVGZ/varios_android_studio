package com.example.listados.adaptadores;

import android.annotation.SuppressLint;
import android.app.Activity;
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

public class AdaptadorPersonalizado_4_Optimizado extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<Planetas> listadoPlanetas;
    private final int layout_personalizado;

    public AdaptadorPersonalizado_4_Optimizado(
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


    //TODO CREAMOS UN METODO VIEWHOLDER QUE LO USAREMOS COMO CONTENEDOR DE DATOS
    //Todo es un wrapper alrededor de una view ue contiene el diseño de un elemento individual de la lista
    private static class ViewHolder {
        TextView tvPlanetas;
        ImageView imgPlanetas;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        //TODO OPTIMIZAMOS EL INFLADO
        if(fila == null){
            //Inflamos nuestro layout personalizado (fila_diferentes_imagenes_y_texto.xml)
            LayoutInflater layoutInflater = context.getLayoutInflater();
            fila = layoutInflater.inflate(layout_personalizado, null);

            holder = new ViewHolder();
            holder.tvPlanetas = fila.findViewById(R.id.tv_planeta);
            holder.imgPlanetas = fila.findViewById(R.id.img_planetas);

            fila.setTag(holder);//Guardamos los atributos dentro del holder


        }else{
            holder = (ViewHolder) fila.getTag();//Llamamos al metodo getTag para recuperar datos.
        }
        holder.tvPlanetas.setText(listadoPlanetas.get(position).getNombre());
        holder.imgPlanetas.setImageResource(listadoPlanetas.get(position).getImagen());



        return fila;
    }

}
