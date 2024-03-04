package listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listados.R;

import com.example.listados.adaptadores.AdaptadorPersonalizado_2_TypedArray;

public class ListView_6_Personalizada2_DiferentesImagenes_TypedArray extends AppCompatActivity {

    private ListView lv_planetas6;
    private TypedArray arrayIdFotoPlanetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view6_personalizada2_diferentes_imagenes_typed_array);
        
        lv_planetas6 = findViewById(R.id.lv_planetas6);
        arrayIdFotoPlanetas = getResources().obtainTypedArray(R.array.fotos_planetas);

        //Crear instancia del adaptador personalizado
        AdaptadorPersonalizado_2_TypedArray adaptador = new AdaptadorPersonalizado_2_TypedArray(
                this,
                R.layout.fila_diferentes_imagenes_y_texto,
                getResources().getStringArray(R.array.planetas),//TEXTO
                arrayIdFotoPlanetas
        );

        lv_planetas6.setAdapter(adaptador);

        lv_planetas6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String planetaSeleccionado = adapterView.getItemAtPosition(i).toString();//DEBEMOS PONER LA i EN POSITION
                Toast.makeText(ListView_6_Personalizada2_DiferentesImagenes_TypedArray.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}