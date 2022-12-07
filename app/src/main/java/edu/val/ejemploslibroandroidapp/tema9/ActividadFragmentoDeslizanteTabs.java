package edu.val.ejemploslibroandroidapp.tema9;


import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import edu.val.ejemploslibroandroidapp.R;


public class ActividadFragmentoDeslizanteTabs extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {


    private ViewPager2 viewPager2;
    private AdapterFragmentos adapterFragmentos;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_fragmento_deslizante_tab);

        this.viewPager2 = findViewById(R.id.vp2);
        this.adapterFragmentos = new AdapterFragmentos(this);
        this.viewPager2.setAdapter(adapterFragmentos);
        this.tabLayout = findViewById(R.id.mitabl);
        //ASOCIO EL TAB CON EL VP
        //this.tabLayout.setupWithViewPager(viewPager2);
        new TabLayoutMediator(tabLayout, viewPager2, this ).attach();


    }


    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        //(tab, position) -> tab.setText("OBJECT " + (position + 1)
        tab.setText("VISTA " + (position+1));
    }
}