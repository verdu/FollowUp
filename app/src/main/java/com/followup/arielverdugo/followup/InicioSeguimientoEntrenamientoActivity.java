package com.followup.arielverdugo.followup;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by arielverdugo on 22/1/18.
 */

public class InicioSeguimientoEntrenamientoActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener{
    private SessionManager sessionManager;
    FragmentTabHost mTabHost;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_seguimiento_entrenamiento);
        sessionManager = new SessionManager(this);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinerJugadoresSeguimiento = (Spinner) findViewById(R.id.spinnerJugadoresSeguimientoEntrenemiento);
        spinerJugadoresSeguimiento.setOnItemSelectedListener(this);

        List<Jugador> jugadoresSeguimiento =  JugadorRepository.getInstance(this).getJugadoresFavoritos();


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

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerSeleccionarEntrenamiento);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SeleccionarEntrenamientoAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhostEntrenamiento);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        if(SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.flag == 1) {
            if (SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.crearLibre == true) {
                mTabHost.addTab(mTabHost.newTabSpec("Libres").setIndicator("L"),
                        TabEstadisticaEntrenamientoSimple.class, null);
            }
            else{

            }
            if(SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.crearDoble == true) {
                mTabHost.addTab(mTabHost.newTabSpec("Dobles").setIndicator("D"),
                        TabEstadisticaEntrenamientoDoble.class, null);
            }
            else{

            }

            if(SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.crearTriple == true) {

                mTabHost.addTab(mTabHost.newTabSpec("Triples").setIndicator("T"),
                        TabEstadisticaEntrenamientoTriple.class, null);
            }
            else{

            }

            mTabHost.onTabChanged("tabhostEntrenamiento");
            mAdapter.notifyDataSetChanged();
        }
        else{
            String hola = "no hace nada";
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

