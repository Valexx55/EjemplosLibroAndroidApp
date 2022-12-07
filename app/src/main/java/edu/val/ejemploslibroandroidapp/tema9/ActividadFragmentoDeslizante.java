package edu.val.ejemploslibroandroidapp.tema9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import edu.val.ejemploslibroandroidapp.R;

public class ActividadFragmentoDeslizante extends AppCompatActivity {


    private ViewPager2 viewPager2;
    private AdapterFragmentos adapterFragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_fragmento_deslizante);
        this.viewPager2 = findViewById(R.id.vp2);
        this.adapterFragmentos = new AdapterFragmentos(this);
        this.viewPager2.setAdapter(adapterFragmentos);


    }
}