package edu.val.ejemploslibroandroidapp.tema12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class AlarmaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(MainActivity.ETIQUETA_LOG, "La alarma ha sonado");
        //lanzar un servicio
        Intent intent_service = new Intent(context, ServiceNumAletorio.class);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            context.startForegroundService(intent_service);
        } else {
            context.startService(intent_service);
        }

    }
}