package edu.val.ejemploslibroandroidapp.tema1y2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.val.ejemploslibroandroidapp.R;


//PANTALLA INICIAL
public class MainActivity extends AppCompatActivity {

    public final static String ETIQUETA_LOG = "AppEjemplos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MÉTODO INICIAL "main"
        Log.d(ETIQUETA_LOG, "Estoy entrado en Oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//cargar la apariencia de la Actividad/Pantalla
        String nombre_version = obtenerVersionAndroid();
        Log.d(ETIQUETA_LOG, "Version Android dispositivo es =  " + nombre_version);
        //finish();//cerrar la pantalla/actividad
        mostrarLayout();

    }

    private void mostrarLayout()
    {
        //mostrar la vistas del layout
        ConstraintLayout vista_padre = findViewById(R.id.vista_padre);
        if (vista_padre instanceof  ViewGroup)
        {
            Log.d(ETIQUETA_LOG, "vista_padre es una Viewgroup");
            View vista_hija = vista_padre.getChildAt(0);
            Log.d(ETIQUETA_LOG, "vista_hija es " + vista_hija.toString());
        }
    }


    private String obtenerVersionAndroid ()
    {
        String nombre_version = null;

            switch (Build.VERSION.SDK_INT)
            {
                case Build.VERSION_CODES.Q:
                        nombre_version = "ANDROID Q - 10";
                    break;
                case Build.VERSION_CODES.R:
                    nombre_version = "ANDROID R - 11";
                    break;
                default:
                    nombre_version = "version anterior a la 10";
            }

        return nombre_version;
    }
}