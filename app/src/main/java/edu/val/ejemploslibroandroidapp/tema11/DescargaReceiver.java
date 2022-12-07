package edu.val.ejemploslibroandroidapp.tema11;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class DescargaReceiver extends BroadcastReceiver {

    private Context context;
    private long id_descarga;


    public DescargaReceiver ()
    {

    }

    public DescargaReceiver (Context context)
    {
        this.context = context;
    }

    public long getId_descarga() {
        return id_descarga;
    }

    public void setId_descarga(long id_descarga) {
        this.id_descarga = id_descarga;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

       //throw new UnsupportedOperationException("Not yet implemented");
        Log.d(MainActivity.ETIQUETA_LOG, "Descarga finalizada");
        DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query consulta = new DownloadManager.Query();
        consulta.setFilterById(this.id_descarga);
        Cursor cursor = downloadManager.query(consulta);
        cursor.moveToFirst();
        int ncol = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(ncol);
        ((ActividadCancionesDescarga)this.context).actualizarEstadoDescarga(status);
        context.unregisterReceiver(this);



    }
}