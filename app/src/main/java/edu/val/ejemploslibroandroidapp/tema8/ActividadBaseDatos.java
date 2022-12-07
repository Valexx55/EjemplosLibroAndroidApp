package edu.val.ejemploslibroandroidapp.tema8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.util.List;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;


public class ActividadBaseDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_base_datos);

        BaseDatosCoches baseDatosCoches = new BaseDatosCoches(this, "BDCOCHE", null, 1);

        Persona persona1 = new Persona(1, "Conchi");
        Persona persona2 = new Persona(2, "Manolo");
        Persona persona3 = new Persona(3, "Paco");

        if (!existeBaseDeDatos("BDCOCHE")) {



            baseDatosCoches.insertarPersona(persona1);
            baseDatosCoches.insertarPersona(persona2);
            baseDatosCoches.insertarPersona(persona3);

            Log.d(MainActivity.ETIQUETA_LOG, "La base de datos no existe, la creamos");

            Log.d(MainActivity.ETIQUETA_LOG, "Personas insertadas");

            Coche coche1 = new Coche("FERRARI", persona1);
            Coche coche2 = new Coche("SEAT", persona1);
            Coche coche3 = new Coche("RENAULT", persona2);

            baseDatosCoches.insertarCoche(coche1);
            baseDatosCoches.insertarCoche(coche2);
            baseDatosCoches.insertarCoche(coche3);

            Log.d(MainActivity.ETIQUETA_LOG, "Coches insertados");

        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "Base de datos creada, no insertamos para evitar duplicados");
        }
        //en cualquier caso, consulto
        List<Coche> cocheList = baseDatosCoches.obtenerCochesPersona(persona1);
        if (cocheList != null) {
            Log.d(MainActivity.ETIQUETA_LOG, "La consulta recuperó " + cocheList.size() + " datos");
            for (Coche c : cocheList) {
                Log.d(MainActivity.ETIQUETA_LOG, "Modelo =  " + c.getModelo());
            }

        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "La consulta NO recuperó datos");
        }
    }

    private boolean existeBaseDeDatos(String dbName) {
        boolean existe_bd = false;

            File dbFile = getDatabasePath(dbName);
            existe_bd = dbFile.exists();

        return existe_bd;
    }
}