package edu.val.ejemploslibroandroidapp.tema9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.val.ejemploslibroandroidapp.R;

public class MiFragmento extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = null;

            fragment = inflater.inflate(R.layout.fragment_deslizante, container, false);
            Bundle bundle = getArguments();
            int valor = bundle.getInt("VALOR");
            TextView textView = fragment.findViewById(R.id.textfragment);
            textView.setText("VISTA "+ valor);



        return fragment;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
