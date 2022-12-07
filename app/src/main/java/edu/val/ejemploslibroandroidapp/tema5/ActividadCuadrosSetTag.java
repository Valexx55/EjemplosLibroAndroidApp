package edu.val.ejemploslibroandroidapp.tema5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadCuadrosSetTag extends AppCompatActivity {


    private LinearLayout caja_naranja;
    private LinearLayout caja_negra;
    private LinearLayout caja_morada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_cuadros_set_tag);

        this.caja_naranja = findViewById(R.id.cajanaranja);
        this.caja_negra = findViewById(R.id.cajanegra);
        this.caja_morada = findViewById(R.id.cajamorada);

    }


    public void cajaTocada(View view) {
        Object o = null;
        int contador = 0;

        Log.d(MainActivity.ETIQUETA_LOG, "Caja tocada");
        o = view.getTag();
        if (o !=null)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "Ya teníamos un contador. Tocada siguientes veces");
            contador = (int)o;
        }else {
            Log.d(MainActivity.ETIQUETA_LOG, "Primera vez");
        }

        contador = contador + 1;
        view.setTag(contador);


    }

    @Override
    protected void onStop() {


        Log.d(MainActivity.ETIQUETA_LOG, "Caja naranja tocada " + this.caja_naranja.getTag() + " veces" );
        Log.d(MainActivity.ETIQUETA_LOG, "Caja morada tocada " + this.caja_morada.getTag() + " veces" );
        Log.d(MainActivity.ETIQUETA_LOG, "Caja negra tocada " + this.caja_negra.getTag() + " veces" );

        super.onStop();
    }
}