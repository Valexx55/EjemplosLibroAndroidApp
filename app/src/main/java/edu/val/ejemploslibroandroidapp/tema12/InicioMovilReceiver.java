package edu.val.ejemploslibroandroidapp.tema12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class InicioMovilReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(MainActivity.ETIQUETA_LOG, "Dispositivo iniciado");
        //Notificaciones.lanzarNotificacion(context);
        GestorAlarma.programarAlarma(context);
    }
}