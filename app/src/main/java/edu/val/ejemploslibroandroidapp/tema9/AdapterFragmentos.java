package edu.val.ejemploslibroandroidapp.tema9;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdapterFragmentos extends FragmentStateAdapter {


    private int[] array_datos = {1, 2, 3};

    public AdapterFragmentos(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;

             fragment = new MiFragmento();
             Bundle bundle = new Bundle();
             bundle.putInt("VALOR", array_datos[position]);
             fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return array_datos.length;
    }
}
