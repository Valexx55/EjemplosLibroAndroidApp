package edu.val.ejemploslibroandroidapp.tema8;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasFormulario {

    private static final String NOMBRE_ARCHIVO_PREFS = "prefs";

    //1 guardar el nombre
    public static void guardarNombre (String nombre, Context context)
    {

        SharedPreferences fichero = context.getSharedPreferences(NOMBRE_ARCHIVO_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = fichero.edit();
        editor.putString("NOMBRE", nombre);
        editor.commit();

    }

    //2 leer el nombre guardado

    /**
     *
     * @param context
     * @return null si no existe nombre, el valor en caso contrario
     */
    public static String leerNombre (Context context)
    {
        String nombre = null;

            SharedPreferences fichero = context.getSharedPreferences(NOMBRE_ARCHIVO_PREFS, Context.MODE_PRIVATE);
            nombre = fichero.getString("NOMBRE", null);

        return nombre;
    }
}
