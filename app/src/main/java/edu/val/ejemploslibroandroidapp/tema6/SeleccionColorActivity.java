package edu.val.ejemploslibroandroidapp.tema6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class SeleccionColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_color);
    }

    public void colorSeleccionado (View view)
    {
        Log.d(MainActivity.ETIQUETA_LOG, "Color seleccionado " );
        LinearLayout linearLayout =(LinearLayout)view;
        Drawable drawable = linearLayout.getBackground();
        ColorDrawable colorDrawable = (ColorDrawable)drawable;
        int color = colorDrawable.getColor();

        Intent intent_resultado = new Intent();
        intent_resultado.putExtra("COLOR_ELEGIDO", color);

        this.setResult(RESULT_OK, intent_resultado);
        this.finish();




    }
}