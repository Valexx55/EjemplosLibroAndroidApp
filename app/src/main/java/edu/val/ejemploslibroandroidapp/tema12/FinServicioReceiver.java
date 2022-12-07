package edu.val.ejemploslibroandroidapp.tema12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class FinServicioReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(MainActivity.ETIQUETA_LOG, "Servicio finalizado");
        int num_rx = intent.getIntExtra("NUM_ALEATORIO", -1);
        Log.d(MainActivity.ETIQUETA_LOG, "Numero generado = " + num_rx);

        if (num_rx>=60)
        {
            //lanzo notificacion
            Notificaciones.lanzarNotificacion(context);
            Log.d(MainActivity.ETIQUETA_LOG, "Lanzo notificación, numero mayor de 60 ");
        }
        //programo alarma
        GestorAlarma.programarAlarma(context);
        Log.d(MainActivity.ETIQUETA_LOG, "Numero menor de 60, reprogramo alarma ");

        context.unregisterReceiver(this);



    }
}