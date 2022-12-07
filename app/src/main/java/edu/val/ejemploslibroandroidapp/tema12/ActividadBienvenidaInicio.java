package edu.val.ejemploslibroandroidapp.tema12;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadBienvenidaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_bienvenida_inicio);

        if (PreferenciasInicio.inicioAutomaticoPedido(this))
        {
            Log.d(MainActivity.ETIQUETA_LOG, "INICIO AUTOMATICO YA SOLICITADO");
        }else {
            solicitarInicioAutomático ();
            PreferenciasInicio.guardarInicioAutomatico(true, this);
        }

    }

    private void solicitarInicioAutomático ()
    {
        String manufacturer = android.os.Build.MANUFACTURER;
        try {
            Intent intent = new Intent();
            if ("xiaomi".equalsIgnoreCase(manufacturer))
            {
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            }

            startActivityForResult(intent, 303);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}