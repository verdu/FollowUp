package com.followup.arielverdugo.followup;

import android.app.DatePickerDialog;
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
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arielverdugo on 19/12/17.
 */

public class InicioSeguimientoPartidoActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener,DatePickerDialog.OnDateSetListener{
    private SessionManager sessionManager;
    FragmentTabHost mTabHost;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewGenerales;
    FloatingActionButton guardar;
    String nombreRival;
    public Spinner spinerJugadoresSeguimiento;
    public Integer golesCampoHechos = 0;
    public Integer golesCampoIntentados = 0;
    public Float porcentajeGolesCampo;
    public Integer triplesHechos = 0;
    public Integer trilplesIntentados = 0;
    public Float procentajeTriples;
    public Integer libresHechos = 0;
    public Integer libresIntentados = 0;
    public Float porcenjateLibres;
    public Integer rebotesOfensivos = 0;
    public String minutos = "0";
    public String segundos = "0";
    public String primerNumero = "0";
    public String segundoNumero = "0";
    public Integer rebotes = 0;
    public Integer asistencias = 0;
    public Integer perdidas = 0;
    public Integer valoracion = 0;
    public Integer bloqueos = 0;
    public Integer rebotesDefensivos = 0;
    public Integer robos = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_seguimiento_partido);
        sessionManager = new SessionManager(this);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinerJugadoresSeguimiento = (Spinner) findViewById(R.id.spinnerJugadoresSeguimiento);
        spinerJugadoresSeguimiento.setOnItemSelectedListener(this);
        //context = spinerJugadoresSeguimiento.getContext();
        List<JugadorEquipoFavorito>jugadoresFavoritos =  JugadorEquipoFavoritoRepository.getInstance(this).findWhere("favorito", 1);

        ArrayList<String> jugadoresNombre = new ArrayList<>();
        for (int i = 0; i < jugadoresFavoritos.size(); i++)
        {
            jugadoresNombre.add(jugadoresFavoritos.get(i).jugadorEquipo.jugador.getNombre());
        }
        ArrayList<String> jugadoresApellido = new ArrayList<>();
        for (int i = 0; i < jugadoresFavoritos.size(); i++)
        {
            jugadoresApellido.add(jugadoresFavoritos.get(i).jugadorEquipo.jugador.getApellido());
        }

        ArrayList<byte[]> jugadoresFotos = new ArrayList<>();
        for (int i = 0; i < jugadoresFavoritos.size(); i++)
        {
            jugadoresFotos.add(jugadoresFavoritos.get(i).jugadorEquipo.jugador.getFoto());
        }

        ArrayList<String> jugadoresEquipos = new ArrayList<>();
        for (int i = 0; i < jugadoresFavoritos.size(); i++)
        {
            jugadoresEquipos.add(jugadoresFavoritos.get(i).jugadorEquipo.equipo.getNombre());
        }

        ArrayList<Integer>idJugadores = new ArrayList<>();
        for (int i = 0; i < jugadoresFavoritos.size(); i++)
        {
            idJugadores.add(jugadoresFavoritos.get(i).jugadorEquipo.jugador.getId());
        }

        ArrayList<Integer>idEquipos = new ArrayList<>();
        for (int i = 0; i < jugadoresFavoritos.size(); i++)
        {
            idEquipos.add(jugadoresFavoritos.get(i).jugadorEquipo.equipo.getId());
        }


        CustomAdapterJugadores customAdapter = new CustomAdapterJugadores(jugadoresNombre,jugadoresApellido,jugadoresFotos, jugadoresEquipos,idJugadores ,idEquipos,getApplicationContext());
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InicioSeguimientoPartidoActivity.this);
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



        ViewGroup popupViewDatosPartido = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_datos_partido,null);
        Button fecha = (Button) popupViewDatosPartido.findViewById(R.id.fecha);
        fecha.setBackgroundResource(R.drawable.calendar);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                InicioSeguimientoPartidoActivity.this, InicioSeguimientoPartidoActivity.this, 2018, 2, 7);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
                //datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE,"Aceptar", DatePickerDialog.OnDateSetListener);

            }
        });

        Button rival = (Button) popupViewDatosPartido.findViewById(R.id.rival);

        rival.setBackgroundResource(R.drawable.rival);
        rival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup popupView = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_datos_partido_rival,null);

                AlertDialog.Builder alertDialogBuilder =
                        new AlertDialog.Builder(InicioSeguimientoPartidoActivity.this)
                                .setTitle("Ingrese el nombre")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // capturar y gaurdadr en bd
                                        nombreRival = (((TextView)popupView.findViewById(R.id.nombreRival)).getText().toString());
                                        Rival rival = new Rival(nombreRival);
                                        RivalRepository.getInstance(InicioSeguimientoPartidoActivity.this).addRival(rival);

                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                alertDialogBuilder.setView(popupView);
                AlertDialog alertDialog = alertDialogBuilder.show();
            }
        });


        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(InicioSeguimientoPartidoActivity.this)
                        .setTitle("Ingrese los siguientes datos")
                        .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // capturar y gaurdadr en bd
                                View viewOfensiva = View.inflate(InicioSeguimientoPartidoActivity.this,R.layout.item_recycler_ofensivas_subheader_golescampo_hechos,null);
                                //String datos = (viewOfensiva.findViewById(R.id.elegantNumberGolesCampoHechos)).toString();
                                //Toast.makeText(InicioSeguimientoPartidoActivity.this,"Holi: "+ datos,Toast.LENGTH_LONG);
                                if(EstadisticaOfensivaAdapter.golesCampoHechos.getNumber() == null)
                                {
                                }
                                else{
                                    golesCampoHechos = Integer.valueOf(EstadisticaOfensivaAdapter.golesCampoHechos.getNumber());

                                }
                                if(EstadisticaOfensivaAdapter.golesCampoIntentados.getNumber() == null)
                                {
                                }
                                else {
                                    golesCampoIntentados = Integer.valueOf(EstadisticaOfensivaAdapter.golesCampoIntentados.getNumber());
                                }
                                if (golesCampoHechos == 0 && golesCampoIntentados == 0)
                                {
                                }
                                else{
                                    porcentajeGolesCampo = (float)(golesCampoHechos*100)/golesCampoIntentados;
                                }
                                if(EstadisticaOfensivaAdapter.triplesHechos.getNumber() == null)
                                {
                                }
                                else
                                {
                                    triplesHechos = Integer.valueOf(EstadisticaOfensivaAdapter.triplesHechos.getNumber());
                                }
                                if(EstadisticaOfensivaAdapter.triplesIntentados.getNumber() == null)
                                {
                                }
                                else{
                                    trilplesIntentados = Integer.valueOf(EstadisticaOfensivaAdapter.triplesIntentados.getNumber());
                                }
                                if(triplesHechos == 0 && trilplesIntentados == 0)
                                {
                                }
                                else{
                                    procentajeTriples = (float)(triplesHechos*100)/trilplesIntentados;
                                }
                                if(EstadisticaOfensivaAdapter.libresHechos.getNumber() == null)
                                {
                                }
                                else{
                                    libresHechos = Integer.valueOf(EstadisticaOfensivaAdapter.libresHechos.getNumber());
                                }
                                if(EstadisticaOfensivaAdapter.libresIntentados.getNumber() == null)
                                {
                                }
                                else{
                                    libresIntentados = Integer.valueOf(EstadisticaOfensivaAdapter.libresIntentados.getNumber());
                                }
                                if(libresHechos == 0 && libresIntentados == 0)
                                {
                                }
                                else{
                                    porcenjateLibres = (float)(libresHechos*100)/libresIntentados;
                                }
                                if(EstadisticaOfensivaAdapter.rebotesOfensivos.getNumber() == null)
                                {
                                }
                                else{
                                    rebotesOfensivos = Integer.valueOf(EstadisticaOfensivaAdapter.rebotesOfensivos.getNumber());
                                }
                                if(EstadisticaGeneralAdapter.minutos.getValue() == 0)
                                {
                                }
                                else{
                                    minutos = String.valueOf(EstadisticaGeneralAdapter.minutos.getValue());
                                }
                                if(EstadisticaGeneralAdapter.segundos.getValue() == 0)
                                {
                                }
                                else{
                                    segundos = String.valueOf(EstadisticaGeneralAdapter.segundos.getValue());
                                }
                                if(EstadisticaGeneralAdapter.primerNumero.getValue() == 0)
                                {

                                }
                                else{
                                    primerNumero = String.valueOf(EstadisticaGeneralAdapter.primerNumero.getValue());
                                }
                                if(EstadisticaGeneralAdapter.segundoNumero.getValue() == 0)
                                {

                                }
                                else{
                                    segundoNumero = String.valueOf(EstadisticaGeneralAdapter.segundoNumero.getValue());
                                }
                                if(EstadisticaGeneralAdapter.rebotes.getNumber() == null)
                                {

                                }
                                else {
                                    rebotes = Integer.valueOf(EstadisticaGeneralAdapter.rebotes.getNumber());
                                }
                                if(EstadisticaGeneralAdapter.asistencias.getNumber() == null)
                                {

                                }
                                else{
                                    asistencias = Integer.valueOf(EstadisticaGeneralAdapter.asistencias.getNumber());
                                }
                                if(EstadisticaGeneralAdapter.perdidas.getText().toString() == "")
                                {

                                }
                                else{
                                    perdidas = Integer.valueOf(EstadisticaGeneralAdapter.perdidas.getText().toString());
                                }
                                if(EstadisticaGeneralAdapter.valoracion.getNumber() == null)
                                {

                                }
                                else{
                                    valoracion = Integer.valueOf(EstadisticaGeneralAdapter.valoracion.getNumber());
                                }
                                if(EstadisticaDefensivaAdapter.bloqueos.getNumber() == null)
                                {

                                }
                                else{
                                    bloqueos = Integer.valueOf(EstadisticaDefensivaAdapter.bloqueos.getNumber());
                                }
                                if(EstadisticaDefensivaAdapter.rebotesDefensivos.getNumber() == null)
                                {

                                }
                                else{
                                    rebotesDefensivos = Integer.valueOf(EstadisticaDefensivaAdapter.rebotesDefensivos.getNumber());
                                }
                                if(EstadisticaDefensivaAdapter.robos.getNumber() == null)
                                {

                                }
                                else{
                                    robos = Integer.valueOf(EstadisticaDefensivaAdapter.robos.getNumber());
                                }

                                int dia = datePickerDialog.getDatePicker().getDayOfMonth();
                                int anio = datePickerDialog.getDatePicker().getYear();
                                int mes = datePickerDialog.getDatePicker().getMonth();
                                Date fecha = new Date(anio,mes,dia);
                                List<Rival>rival = RivalRepository.getInstance(InicioSeguimientoPartidoActivity.this).findWhere("nombre",nombreRival);
                                String nombreRival = rival.get(0).getNombre().toString();
                                String minutosSegundosConcatenados = minutos + segundos;
                                Integer minutosSegundos = Integer.parseInt(minutosSegundosConcatenados);
                                String puntosConcatenados = primerNumero + segundoNumero;
                                Integer puntos = Integer.valueOf(puntosConcatenados);

                                java.sql.Date sqlStartDate = new java.sql.Date(fecha.getTime());
                                Partido partido = new Partido(sqlStartDate,nombreRival,minutosSegundos,puntos,golesCampoHechos,golesCampoIntentados,porcentajeGolesCampo,triplesHechos,trilplesIntentados,procentajeTriples,libresIntentados,libresHechos,porcenjateLibres,rebotes,rebotesDefensivos,rebotesOfensivos,asistencias,robos,bloqueos,perdidas,valoracion);
                                PartidoRepository.getInstance(InicioSeguimientoPartidoActivity.this).addPartido(partido);

                                final View jugadorSeguimiento = spinerJugadoresSeguimiento.getSelectedView();
                                String idEquipoSpinner  = (((TextView)jugadorSeguimiento.findViewById(R.id.idEquipoSpinner)).getText().toString());
                                String idJugadorSpinner  = (((TextView)jugadorSeguimiento.findViewById(R.id.idJugadorSpinner)).getText().toString());
                                Map<String,String>paramsBusqueda = new HashMap<String,String>();
                                paramsBusqueda.put("id_equipo",idEquipoSpinner);
                                paramsBusqueda.put("id_jugador",idJugadorSpinner);
                                List<JugadorEquipo> je = JugadorEquipoRepository.getInstance(InicioSeguimientoPartidoActivity.this).findWhere(paramsBusqueda);
                                PartidoEntrenamiento partidoEntrenamiento = new PartidoEntrenamiento(je.get(0),partido,null);
                                PartidoEntrenamientoRepository.getInstance(InicioSeguimientoPartidoActivity.this).addEstadisticaPartidoEntrenamiento(partidoEntrenamiento);

                                Toast.makeText(InicioSeguimientoPartidoActivity.this, "Seguimiento generado", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


        alertDialogBuilder.setView(popupViewDatosPartido);
        AlertDialog alertDialog = alertDialogBuilder.show();


    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int anio = view.getYear();
        int mes = view.getMonth();
        int day = view.getDayOfMonth();
    }
}
