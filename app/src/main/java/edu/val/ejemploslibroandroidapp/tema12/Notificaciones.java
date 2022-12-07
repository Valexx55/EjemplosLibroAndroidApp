package edu.val.ejemploslibroandroidapp.tema12;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Notificaciones {

    public static final String NOTIFICATION_CHANNEL_ID = "channel_id1";

    //El nombre visible para el usuario en Ajustes
    public static final String CHANNEL_NAME = "Notification Channel1";

    private static NotificationChannel crearCanalNotificacion(Context context, String id_canal, String nombre_canal) {
        NotificationChannel notificationChannel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationChannel = new NotificationChannel(id_canal, nombre_canal, NotificationManager.IMPORTANCE_DEFAULT);


            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(context.getApplicationContext().getColor(R.color.minaranja));
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            notificationChannel.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.snd_noti),audioAttributes);

            //La duración del patrón de vibración {silencio,vibración,silencio,vibración,..}
            notificationChannel.setVibrationPattern(new long[]{
                    500,
                    500,
                    500,
                    500,
                    500
            });

            //Indicamos si la notificación será visible estando la pantalla bloqueada o no
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }


        return notificationChannel;
    }

    public static void lanzarNotificacion(Context context) {

        Log.d(MainActivity.ETIQUETA_LOG, "Lanzando notificación");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder nb = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = crearCanalNotificacion(context, NOTIFICATION_CHANNEL_ID, CHANNEL_NAME);
            notificationManager.createNotificationChannel(nc);
        }

        nb = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);

        nb.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        nb.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        nb.setSmallIcon(R.drawable.ic_baseline_menu_24);//importante blanco y fondo transparente
        nb.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.emoticono_risa));
        nb.setContentTitle("BUENOS DÍAS");
        nb.setSubText("aviso diario");
        nb.setContentText("Eres un campeón");
        nb.setAutoCancel(true);
        nb.setDefaults(Notification.DEFAULT_ALL);

        Intent resultIntent = new Intent(context, ActividadBienvenidaInicio.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

        nb.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(537, nb.build());//537
    }


    public static Notification crearNotificacionSegundoPlano(Context context) {
        Notification segundo_plano = null;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder nb = null;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = crearCanalNotificacion(context, NOTIFICATION_CHANNEL_ID, CHANNEL_NAME);
            notificationManager.createNotificationChannel(nc);//creo nc si ya existe??
        }
        nb = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);

        nb.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        nb.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        nb.setSmallIcon(R.drawable.ic_baseline_menu_24);//importante blanco y fondo transparente
        nb.setContentTitle("Comprobando si hay mensajes");
        nb.setAutoCancel(true);
        nb.setDefaults(Notification.DEFAULT_ALL);
        nb.setTimeoutAfter(5000);


        segundo_plano = nb.build();
        Log.d(MainActivity.ETIQUETA_LOG, "Notificacion segundo plano creada");

        return segundo_plano;


    }
}
