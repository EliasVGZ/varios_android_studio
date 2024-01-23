package com.example.listados.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listados.R;

public class AdaptadorPersonalizado_2_TypedArray extends ArrayAdapter {


    private Activity context;
    private String[] arrayPlanetas;
    private TypedArray arrayIdFotosPlanetas;

    private int layout_personalizado;
    /*
    todo ESTE ES EL METODO DEL ARRAYADAPTER, TENEMOS QUE MODIFICARLO PARA PERSONALIZARLO
    public AdaptadorPersonalizado_1(
            @NonNull Context context,
            int resource,
            int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }*/

    public AdaptadorPersonalizado_2_TypedArray(
            @NonNull Activity context,// --> SE USA MAS ABAJOO EN getView
            int layout_personalizado, // --> SE USA MAS ABAJOO EN getView
            String[] arrayPlanetas, // --> SE USA MAS ABAJOO EN getView
            TypedArray arrayIdFotosPlanetas)  // --> SE USA MAS ABAJOO EN getView
    {
        //Constructor defectivo de la clase arrayadapter
        super(context, layout_personalizado, arrayPlanetas);

        this.context = context;
        this.layout_personalizado = layout_personalizado;
        this.arrayPlanetas = arrayPlanetas;
        this.arrayIdFotosPlanetas = arrayIdFotosPlanetas;
    }// termina la construrccion AdaptadorPersonalizado_1



    //Implementar nuestro metodo getView(), se genera con Control + o y luego buscar getView...etc

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        //Inflamos nuestro layoutpersonalizado(fila_diferentes_imagenes_y_texto.xml)
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(layout_personalizado, null);

        //Capturamos los id de cada componente de nuestro layout personlizado -> img_planetas y tv_planeta
        TextView tvPlanetas = fila.findViewById(R.id.tv_planeta);
        ImageView imgPlanetas = fila.findViewById(R.id.img_planetas);

        //Insertar cada valor (planeta/imagen) en su correspondiente ID
        tvPlanetas.setText(arrayPlanetas[position]);
        imgPlanetas.setImageDrawable(arrayIdFotosPlanetas.getDrawable(position));
        //AHORA YA NO FUNCIONA -> imgPlanetas.setImageResource(arrayIdFotosPlanetas[position]);


        return fila;

    }




}
