package edu.val.ejemploslibroandroidapp.tema7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadSeleccionFechaYHora extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText caja_fecha;
    private EditText caja_hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_seleccion_fecha_y_hora);

        this.caja_fecha = findViewById(R.id.cajaFecha);
        this.caja_hora = findViewById(R.id.cajaHora);

        this.caja_fecha.setOnFocusChangeListener(this);
        this.caja_hora.setOnFocusChangeListener(this);

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
        {
            switch (v.getId())
            {
                case R.id.cajaFecha:
                    Log.d(MainActivity.ETIQUETA_LOG, "FECHA Toca lanzar el Calendario");
                    DialogFragment fragmento_calendario = new SeleccionCalendario();
                    fragmento_calendario.show(getSupportFragmentManager(), "CALENDARIO");
                    break;
                case R.id.cajaHora:
                    Log.d(MainActivity.ETIQUETA_LOG, "HORA Toca lanzar el Reloj");
                    DialogFragment fragmento_reloj = new SeleccionHora();
                    fragmento_reloj.show(getSupportFragmentManager(), "RELOJ");
                    break;
            }
        }

    }

    public void mostrarFechaSeleccionada (String fecha)
    {
        this.caja_fecha.setText(fecha);
    }

    public void mostrarHoraSeleccionada (String hora)
    {
        this.caja_hora.setText(hora);
    }


}