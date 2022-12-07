package edu.val.ejemploslibroandroidapp.tema12;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ServiceNumAletorio extends Service {

    private int numero_aleatorio;

    public ServiceNumAletorio() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(MainActivity.ETIQUETA_LOG, "Inicio servicio n aleatorio");

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        FinServicioReceiver finServicioReceiver = new FinServicioReceiver();
        IntentFilter intentFilter = new IntentFilter("SERV_ALEAOTORIO_FINAL");
        localBroadcastManager.registerReceiver(finServicioReceiver, intentFilter);

        Notification n = Notificaciones.crearNotificacionSegundoPlano(this);
        startForeground(66, n);

        //ejecuto la función del servicio
        try {
            Thread.sleep(5000);
            numero_aleatorio = (int)(Math.random()*100+1);//obtengo un numero aleatorio en tre 1 y 100

        } catch (Throwable t)
        {
            Log.e(MainActivity.ETIQUETA_LOG, "Fallo en la ejecución del servicio", t);
        }
        stopForeground(false);
        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(MainActivity.ETIQUETA_LOG, "FINAL servicio n aleatorio");
        //emitir la señal del final de servicio finalizado

        Intent intent_fin = new Intent("SERV_ALEAOTORIO_FINAL");
        intent_fin.putExtra("NUM_ALEATORIO", this.numero_aleatorio);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(intent_fin);

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}