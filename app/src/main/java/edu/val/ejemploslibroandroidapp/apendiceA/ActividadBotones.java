package edu.val.ejemploslibroandroidapp.apendiceA;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.squareup.picasso.Picasso;

import edu.val.ejemploslibroandroidapp.R;

public class ActividadBotones extends AppCompatActivity {

    TextView lblMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*1ER EJEMPLO */


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_botones);
        lblMensaje = (TextView)findViewById(R.id.LblMensaje);

        GoogleMap googleMap = null;
        Picasso picasso = null;

    }


    public void botonTocado (View v)
    {
        lblMensaje.setText(v.getClass().getCanonicalName());

    }

}