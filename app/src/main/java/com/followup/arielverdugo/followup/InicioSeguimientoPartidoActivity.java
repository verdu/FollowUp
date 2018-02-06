package com.followup.arielverdugo.followup;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 19/12/17.
 */

public class InicioSeguimientoPartidoActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener{
    private SessionManager sessionManager;
    FragmentTabHost mTabHost;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewGenerales;
    FloatingActionButton guardar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_seguimiento_partido);
        sessionManager = new SessionManager(this);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinerJugadoresSeguimiento = (Spinner) findViewById(R.id.spinnerJugadoresSeguimiento);
        spinerJugadoresSeguimiento.setOnItemSelectedListener(this);
        //context = spinerJugadoresSeguimiento.getContext();
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


        mTabHost.addTab(mTabHost.newTabSpec("general").setIndicator("",getResources().getDrawable(R.drawable.generales)),
                TabEstadisticaGeneral.class, null);
        mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
        TextView titleGeneral = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        titleGeneral.setTextColor(Color.parseColor("#ffffff"));

        //mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.myrect);
        //ViewCompat.setElevation(mTabHost.getTabWidget().getChildAt(0), 30);
        //mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 223;
        //mTabHost.getTabWidget().getChildAt(0).getLayoutParams().width = 100;


        mTabHost.addTab(mTabHost.newTabSpec("ofensiva").setIndicator("",getResources().getDrawable(R.drawable.ofensivas)),
                TabEstadisticaOfensiva.class, null);
        mTabHost.getTabWidget().getChildAt(1).setBackgroundColor(ContextCompat.getColor(this, R.color.violet));
        TextView titleOfensiva = (TextView) mTabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        titleOfensiva.setTextColor(Color.parseColor("#ffffff"));


        mTabHost.addTab(mTabHost.newTabSpec("defensiva").setIndicator("",getResources().getDrawable(R.drawable.defensivas)),
                TabEstadisticaDefensiva.class, null);
        mTabHost.getTabWidget().getChildAt(2).setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        TextView titleDefensiva = (TextView) mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        titleDefensiva.setTextColor(Color.parseColor("#ffffff"));

        mTabHost.addTab(mTabHost.newTabSpec("situacion").setIndicator("",getResources().getDrawable(R.drawable.situaciones)),
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

        FloatingActionsMenu menu = (FloatingActionsMenu) findViewById(R.id.menu_fab);
        FloatingActionButton save = (FloatingActionButton) menu.findViewById(R.id.accion_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                builder1.setMessage("Write your message here.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
