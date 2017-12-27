package com.followup.arielverdugo.followup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 19/12/17.
 */

public class InicioSeguimientoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_seguimiento);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        sessionManager = new SessionManager(this);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinerJugadoresSeguimiento = (Spinner) findViewById(R.id.spinnerJugadoresSeguimiento);
        spinerJugadoresSeguimiento.setOnItemSelectedListener(this);

        List<Jugador>jugadoresSeguimiento =  JugadorRepository.getInstance(this).getJugadoresFavoritos();

        ArrayList<String> jugadoresNombre = new ArrayList<>();
        for (int i = 0; i < jugadoresSeguimiento.size(); i++)
        {
            jugadoresNombre.add(jugadoresSeguimiento.get(i).getNombre());
        }
        ArrayList<String> jugadoresApellido = new ArrayList<>();
        for (int i = 0; i < jugadoresSeguimiento.size(); i++)
        {
            jugadoresApellido.add(jugadoresSeguimiento.get(i).getApellido());
        }

        ArrayList<byte[]> jugadoresFotos = new ArrayList<>();
        for (int i = 0; i < jugadoresSeguimiento.size(); i++)
        {
            jugadoresFotos.add(jugadoresSeguimiento.get(i).getFoto());
        }

        CustomAdapterJugadores customAdapter = new CustomAdapterJugadores(jugadoresNombre,jugadoresApellido,jugadoresFotos,getApplicationContext());
        spinerJugadoresSeguimiento.setAdapter(customAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
