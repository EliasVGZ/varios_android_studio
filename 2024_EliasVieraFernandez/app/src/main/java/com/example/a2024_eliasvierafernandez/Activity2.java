package com.example.a2024_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    EditText et_formula;
    String mensajeRecibido, formula;
    private MiClaseParaBBDD miClase;
    private static SQLiteDatabase db;
    private Context mensajeToast;
    LinearLayout ll_principal, ll_radioButton;
    private Button btn_comprobar, btn_continuar;
    private RadioButton rb_si, rb_no;
    String datoRecibido;
    int intentos = 0;
    private static final int NOTIFICACION_1 = 1;
    String formulaStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ll_principal = findViewById(R.id.ll_principal);
        ll_radioButton = findViewById(R.id.ll_radioButton);
        btn_comprobar = findViewById(R.id.btn_comprobar);
        btn_continuar = findViewById(R.id.btn_continuar);
        rb_no = findViewById(R.id.rb_no);
        rb_si = findViewById(R.id.rb_si);
        rb_si.isActivated();

        btn_continuar.setOnClickListener(this);
        btn_comprobar.setOnClickListener(this);

        miClase = new MiClaseParaBBDD(this, "BDQuimicos", null, 1);
        db = miClase.getWritableDatabase();

        et_formula = findViewById(R.id.et_formula);

        Intent myintent = getIntent();
        //EXTRAER EL DATO
        datoRecibido = myintent.getStringExtra("mensaje");

        et_formula.setHint(datoRecibido);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_comprobar:
                formulaStr = et_formula.getText().toString();
                if (!formulaStr.isEmpty()) {
                    et_formula.setText(mensajeRecibido);
                    ll_principal.setVisibility(View.GONE);
                    ll_radioButton.setVisibility(View.VISIBLE);

                    metodoComprobar();

                    et_formula.setText("");

                } else {
                    Toast.makeText(this, "Formula no puede estar vacia", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_continuar:

                if (rb_si.isChecked()) {
                    ll_radioButton.setVisibility(View.GONE);
                    ll_principal.setVisibility(View.VISIBLE);


                } else if (rb_no.isChecked()) {
                    finishAffinity();
                }

                break;


        }//end swtich

    }



    private void metodoComprobar() {


        //TODO ME DDEVUELVE LA FORMULA DEL COMPUESTO QUE LE PASO!!

        // Realizar la consulta en la base de datos con el código ingresado
        Cursor cursor = db.rawQuery("SELECT formula FROM quimicos WHERE compuesto = ?", new String[]{datoRecibido});

        if (cursor.moveToFirst()) {
            // Si hay resultados, obtener el nombre y mostrarlo
            @SuppressLint("Range") String formula = cursor.getString(cursor.getColumnIndex("formula"));

            if(formulaStr.equalsIgnoreCase(formula)){
                Toast.makeText(this, formula + getString(R.string.correcto), Toast.LENGTH_SHORT).show();
            }else{
                intentos ++;
                Log.d("DEBUG", "Valor de intentos: " + intentos);
                Toast.makeText(this, formula + getString(R.string.incorrecto), Toast.LENGTH_SHORT).show();
                if (intentos == 3) {
                    notificacion_barra_estado();

                }
            }

        cursor.close();
        }

    }

    private void notificacion_barra_estado() {

        // 1-Crear Notificacion!
        Notification.Builder notificacion = new Notification.Builder(this);
        // 2-Personalizar notificacion!
        //Barra de estado
        notificacion.setSmallIcon(android.R.drawable.ic_delete); //Setear icono pequeño, aparece en la barra de estado
        //Bandeja del sistema
        //Todo Para poner el icono grande en la bandeja del sistema, tenemos que convertir el drawable en bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flask1);
        notificacion.setLargeIcon(bitmap);

        notificacion.setContentTitle("Tienes mas de 2 errores"); //Título
        notificacion.setContentText("Consulta l web para aprender mas");//Mensaje
        notificacion.setAutoCancel(true);//Deja de aparecer el icono en la barra de estado al clickear la notificacion

        String url = "https://www.formulacionquimica.com";
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        //verificar si hay algun explorado para abrir



        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_IMMUTABLE);
        //Asociar el pendingIntent con la notificación!
        notificacion.setContentIntent(pendingIntent);
        //startActivity(i);

        // 4-Lanzar la notificacion
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //El notification.builder nos ayudo para construir, ahora tenemos que volver a pasarlo a Notification PARA QUE SEA UNA NOTIFICaCION
        Notification notifi = notificacion.build();//
        notificationManager.notify(NOTIFICACION_1, notifi);// El NOTIFICATION_1 es una constante que añadí arriba

    }

}


