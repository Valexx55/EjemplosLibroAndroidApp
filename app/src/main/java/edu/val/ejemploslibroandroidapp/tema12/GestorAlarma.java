package edu.val.ejemploslibroandroidapp.tema12;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class GestorAlarma {


    public static void programarAlarma (Context context)
    {
        Calendar calendar = Calendar.getInstance();
        long tiempo = calendar.getTimeInMillis() + 10000;//la alarma salta en 10 segundos

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent_receptor = new Intent(context, AlarmaReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 55, intent_receptor, PendingIntent.FLAG_UPDATE_CURRENT);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,tiempo,  pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP,tiempo,  pendingIntent);
        }

        Log.d (MainActivity.ETIQUETA_LOG, "Alarma programaada en 10 segundos");

        //mensaje informativo
        SimpleDateFormat dateformatter = new SimpleDateFormat("E dd/MM/yyyy 'a las' hh:mm:ss");
        String mensaje = "Alarma programada para " + dateformatter.format(tiempo);
        Log.d(MainActivity.ETIQUETA_LOG, mensaje);


        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();


    }
}
