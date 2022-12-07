package edu.val.ejemploslibroandroidapp.tema12;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasInicio {

    private static final String NOMBRE_ARCHIVO_PREFS_IA = "prefsinicio";

    //1 guardar el nombre
    public static void guardarInicioAutomatico (boolean valor, Context context)
    {

        SharedPreferences fichero = context.getSharedPreferences(NOMBRE_ARCHIVO_PREFS_IA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = fichero.edit();
        editor.putBoolean("INICIO_AUTOMATICO", valor);
        editor.commit();

    }

    //2 leer el nombre guardado

    /**
     *
     * @param context
     * @return null si no existe nombre, el valor en caso contrario
     */
    public static boolean inicioAutomaticoPedido (Context context) {
        boolean inicio_automatico = false;

            SharedPreferences fichero = context.getSharedPreferences(NOMBRE_ARCHIVO_PREFS_IA, Context.MODE_PRIVATE);
            inicio_automatico  = fichero.getBoolean("INICIO_AUTOMATICO", false);

        return inicio_automatico;

    }
}
