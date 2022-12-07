package edu.val.ejemploslibroandroidapp.tema11;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;


public class ActividadSeleccionContactos2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_seleccion_contactos2);
        pedirPermisoAccesoAContactos();



    }




    private void mostrarTelefonos() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri_contactos = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Log.d(MainActivity.ETIQUETA_LOG, "uri_contactos = " + uri_contactos);

        Cursor cursor = contentResolver.query(uri_contactos, null, null, null, null);
        if (cursor.moveToFirst()) {
            int ncol = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String nt_aux = null;
            do {
                nt_aux = cursor.getString(ncol);
                Log.d(MainActivity.ETIQUETA_LOG, "Telefono  = " + nt_aux);

            } while (cursor.moveToNext());
            cursor.close();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.ETIQUETA_LOG, "Permiso acceso a contactos concedido");
            mostrarTelefonos();

        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "Permiso acceso a contactos denegado");
        }
    }

    private void pedirPermisoAccesoAContactos() {
        String[] ap = {Manifest.permission.READ_CONTACTS};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(ap, 987);
        }
    }


    //String[] prefijo = {"M%"};
    //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, null, null, null); //Selecciono todas las columnas, de todos
    //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, null, null, ContactsContract.Contacts.DISPLAY_NAME +" ASC"); //Selecciono todas las columnas, de todos los contactos, ordenando alfabéticamente de menor a mayor
    //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, ContactsContract.Contacts.HAS_PHONE_NUMBER +" = 1" ,null, null); //Selecciono todas las columnas, de los contactos que tengan teléfono
    //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, ContactsContract.Contacts.DISPLAY_NAME +" LIKE ?" ,prefijo, null); //Selecciono todas las columnas, de los contactos que empiecen por M
    //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, ContactsContract.Contacts.DISPLAY_NAME + " = 'Alberto'", null, null); //Selecciono todas las columnas del contacto llamado Alberto
}