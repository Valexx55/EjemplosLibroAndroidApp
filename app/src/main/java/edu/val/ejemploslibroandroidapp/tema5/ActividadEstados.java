package edu.val.ejemploslibroandroidapp.tema5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadEstados extends AppCompatActivity {


    @Override
    protected void onStart() {
        Log.d(MainActivity.ETIQUETA_LOG, "on Start invocado");
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(MainActivity.ETIQUETA_LOG, "on Create invocado");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_estados);
    }

    @Override
    protected void onResume() {
        Log.d(MainActivity.ETIQUETA_LOG, "on Resume invocado");
        //SE TENDRÁ QUE ACTUALIZAR LA POSICIÓN DEL GPS EN LA PANTALLA
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(MainActivity.ETIQUETA_LOG, "on Stop invocado");
        //SE ESTÁ GUARDADNO EL TEXTO QUE ESTABA ESCRIBIENDO EN WASAP
        super.onStop();
    }
}