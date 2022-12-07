package edu.val.ejemploslibroandroidapp.tema11;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class RedUtil {

    public static boolean hayInternet (Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean hay_internet = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            {
                Log.d(MainActivity.ETIQUETA_LOG, "Comprobando internet desde version >= 30");
                Network network = connectivityManager.getActiveNetwork();
                hay_internet = (network != null);

            } else {

                Log.d(MainActivity.ETIQUETA_LOG, "Comprobando internet desde version < 30");
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo!=null)
                {
                    hay_internet = (networkInfo.isAvailable() && networkInfo.isConnected());
                }

            }


        return  hay_internet;
    }



    public static boolean hayWifi (Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean hay_wifi = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "Comprobando internet desde version >= 30");
            Network network = connectivityManager.getActiveNetwork();
            if (network!=null)
            {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                hay_wifi = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            }


        } else {

            Log.d(MainActivity.ETIQUETA_LOG, "Comprobando internet desde version < 30");
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null)
            {
                hay_wifi = (networkInfo.isAvailable() && networkInfo.isConnected()&&(networkInfo.getType()==ConnectivityManager.TYPE_WIFI));
            }

        }


        return  hay_wifi;
    }

    //solución antigua de stackoverflow https://stackoverflow.com/a/34741193/4067559
    public static boolean isWifiAvailable (Context context)
    {
        boolean br = false;
        ConnectivityManager cm = null;
        NetworkInfo ni = null;

            cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            ni = cm.getActiveNetworkInfo();
            br = ((null != ni) && (ni.isConnected()) && (ni.getType() == ConnectivityManager.TYPE_WIFI));

        return br;
    }
}
