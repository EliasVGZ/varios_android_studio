package spinners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.listados.R;

public class Spinner_2_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner2);

        Spinner spPlanetas2 = findViewById(R.id.spPlanetas2);

        String[] arrayPlanetas = {"Mercurio", "Venis", "Tierra", "Marte", "Saturno", "Júpiter", "Urano", "Neptuno", "Saturno"};

        //utilizar un adaptador `para coger datos del array.
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrayPlanetas);

        //ASGINAR ADAPTADOR AL SPINNER
        spPlanetas2.setAdapter(adaptador);

        spPlanetas2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = spPlanetas2.getSelectedItem().toString();//CASTEAMOS A STRING
                //PODEMOS ACCEDER AL ELEMENTO SELECCIONADO DESDE EL ADAPTADOR
                String elementoSeleccionado2 = parent.getItemAtPosition(position).toString(); //ELIGE EL  ITEM SELECCIONADO
                Toast.makeText(Spinner_2_Activity.this, "Has elegido "+elementoSeleccionado+ "\n"+elementoSeleccionado2, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //no tiene interes para nosotros

            }
        });
    }
}