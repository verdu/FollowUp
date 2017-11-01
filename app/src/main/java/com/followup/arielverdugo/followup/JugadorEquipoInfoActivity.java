package com.followup.arielverdugo.followup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import java.util.List;

/**
 * Created by arielverdugo on 12/10/17.
 */

public class JugadorEquipoInfoActivity extends FragmentActivity {

    private SessionManager sessionManager;
    List<Jugador> jugadoresTotales =  JugadorRepository.getInstance(this).getJugadores();
    List<Equipo> equiposTotales = EquipoRepository.getInstance(this).getEquipos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugadorinfo);
        sessionManager = new SessionManager(this);
        for(int i = 0; i < equiposTotales.size(); i++)
        {
            FragmentTabHost mTabHost = (FragmentTabHost) findViewById(R.id.tabhostEquipos);
            mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

            mTabHost.addTab(
                    mTabHost.newTabSpec(equiposTotales.get(i).getNombre()).setIndicator(equiposTotales.get(i).getNombre(), null),
                    FragmentJugadorEquipoInfo.class, null);
        }
    }



}
