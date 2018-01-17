package com.followup.arielverdugo.followup;

import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 19/12/17.
 */

public class InicioSeguimientoActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener{
    private SessionManager sessionManager;
    FragmentTabHost mTabHost;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewGenerales;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_seguimiento);
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


        mTabHost = (FragmentTabHost) findViewById(R.id.tabhostSubEstadisticas);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        mTabHost.addTab(mTabHost.newTabSpec("general").setIndicator("G"),
                TabEstadisticaGeneral.class, null);
        mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
        TextView titleGeneral = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        titleGeneral.setTextColor(Color.parseColor("#ffffff"));

        //mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.myrect);
        //ViewCompat.setElevation(mTabHost.getTabWidget().getChildAt(0), 30);
        //mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 223;
        //mTabHost.getTabWidget().getChildAt(0).getLayoutParams().width = 100;


        mTabHost.addTab(mTabHost.newTabSpec("ofensiva").setIndicator("O"),
                TabEstadisticaOfensiva.class, null);
        mTabHost.getTabWidget().getChildAt(1).setBackgroundColor(ContextCompat.getColor(this, R.color.violet));
        TextView titleOfensiva = (TextView) mTabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        titleOfensiva.setTextColor(Color.parseColor("#ffffff"));


        mTabHost.addTab(mTabHost.newTabSpec("defensiva").setIndicator("D"),
                TabEstadisticaDefensiva.class, null);
        mTabHost.getTabWidget().getChildAt(2).setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        TextView titleDefensiva = (TextView) mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        titleDefensiva.setTextColor(Color.parseColor("#ffffff"));

        mTabHost.addTab(mTabHost.newTabSpec("situacion").setIndicator("S"),
                TabEstadisticaSituacion.class, null);
        mTabHost.getTabWidget().getChildAt(3).setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
        TextView titleSituacion = (TextView) mTabHost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
        titleSituacion.setTextColor(Color.parseColor("#ffffff"));


        //mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        /*String[] data = {"1", "2"};
        recyclerViewGenerales = (RecyclerView) findViewById(R.id.recyclerGenerales);
        int numberOfColumns = 2;
        recyclerViewGenerales.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new EstadisticaGeneralAdapter(this,data);
        recyclerViewGenerales.setAdapter(adapter);*/




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
