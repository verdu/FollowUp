package com.followup.arielverdugo.followup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by arielverdugo on 22/1/18.
 */

public class InicioSeguimientoEntrenamientoActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener,DatePickerDialog.OnDateSetListener{
    private SessionManager sessionManager;
    FragmentTabHost mTabHost;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public TabEstadisticaEntrenamientoSimple instanciaLibres = new TabEstadisticaEntrenamientoSimple();
    public Integer algo = 0;
    public Integer total = 0;




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

        FloatingActionsMenu menu = (FloatingActionsMenu) findViewById(R.id.menu_fabEntrenamiento);
        FloatingActionButton save = (FloatingActionButton) menu.findViewById(R.id.accion_saveEntrenamiento);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InicioSeguimientoEntrenamientoActivity.this);
                alertDialogBuilder.setMessage("¿Desea guardar las estadísticas cargadas?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
                                createDialog();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    public void createDialog()
    {
        ViewGroup popupViewDatosEntrenamiento = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_datos_entrenamiento,null);
        Button fecha = (Button) popupViewDatosEntrenamiento.findViewById(R.id.fechaEntrenamiento);
        fecha.setBackgroundResource(R.drawable.calendar);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                InicioSeguimientoEntrenamientoActivity.this, InicioSeguimientoEntrenamientoActivity.this, 2018, 2, 7);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });



        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(InicioSeguimientoEntrenamientoActivity.this)
                        .setTitle("Ingrese los siguientes datos")
                        .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // capturar y gaurdadr en bd

                                int dia = datePickerDialog.getDatePicker().getDayOfMonth();
                                int anio = datePickerDialog.getDatePicker().getYear();
                                int mes = datePickerDialog.getDatePicker().getMonth();
                                Date fecha = new Date(anio,mes,dia);

                                Entrenamiento entrenamiento = new Entrenamiento((java.sql.Date)fecha,getValuesLibres(),getValuesDobles(),getValuesTriples());
                                EntrenamientoRepository.getInstance(InicioSeguimientoEntrenamientoActivity.this).addEntrenamiento(entrenamiento);
                                Toast.makeText(InicioSeguimientoEntrenamientoActivity.this, "Seguimiento generado", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


        alertDialogBuilder.setView(popupViewDatosEntrenamiento);
        AlertDialog alertDialog = alertDialogBuilder.show();


    }


    public Integer getValuesLibres()
    {
        Integer y = 0;

        for(int i = 0; i < EstadisticaEntrenamientoSimpleAdapter.viewsSimple.size();i++)
        {
            String numero = ((ElegantNumberButton)EstadisticaEntrenamientoSimpleAdapter.viewsSimple.get(i)).getNumber();
             y = y + Integer.valueOf(numero);
        }
        return y;
    }

    public Integer getValuesDobles()
    {
        Integer y = 0;

        for(int i = 0; i < EstadisticaEntrenamientoDobleAdapter.viewsDoble.size();i++)
        {
            String numero = ((ElegantNumberButton)EstadisticaEntrenamientoDobleAdapter.viewsDoble.get(i)).getNumber();
            y = y + Integer.valueOf(numero);
        }
        return y;
    }

    public Integer getValuesTriples()
    {
        Integer y = 0;

        for(int i = 0; i < EstadisticaEntrenamientoTripleAdapter.viewsTriple.size();i++)
        {
            String numero = ((ElegantNumberButton)EstadisticaEntrenamientoTripleAdapter.viewsTriple.get(i)).getNumber();
            y = y + Integer.valueOf(numero);
        }
        return y;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}

