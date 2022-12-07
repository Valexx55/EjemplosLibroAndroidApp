package edu.val.ejemploslibroandroidapp.tema11;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;


public class ActividadSeleccionContactos extends AppCompatActivity {

    private ActivityResultLauncher<Intent> mStartForResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_seleccion_contactos);

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, 444);


        //ESTILO NUEVO --
        // initActivity2();
        //this.mStartForResult.launch(contactPickerIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "La seleccion de contacto fue bien");
            Uri uri_contacto = data.getData();
            Log.d(MainActivity.ETIQUETA_LOG, "uri_contacto "+ uri_contacto);
            Cursor cursor = getContentResolver().query(uri_contacto, null, null, null, null);
            cursor.moveToFirst();
            int ncol = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String n_telfonos = cursor.getString(ncol);
            Log.d(MainActivity.ETIQUETA_LOG, "Teléfono obtenido = " + n_telfonos);
        }
        else {
            Log.d(MainActivity.ETIQUETA_LOG, "La seleccion de contacto fue mal");
        }
    }









    private void initActivity2 ()
    {
        this.mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();

                            Uri uri = intent.getData();
                            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                            cursor.moveToFirst();
                            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            String telf  = cursor.getString(phoneIndex);

                            Log.d(MainActivity.ETIQUETA_LOG, "NUMERO SELECCIOANDO I2 = " +telf);


                        }
                    }
                });

    }
}