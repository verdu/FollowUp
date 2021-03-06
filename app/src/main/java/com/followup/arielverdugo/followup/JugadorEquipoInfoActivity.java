package com.followup.arielverdugo.followup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import java.util.List;

/**
 * Created by arielverdugo on 12/10/17.
 */

public class JugadorEquipoInfoActivity extends FragmentActivity{

    List<Equipo> equiposTotales = EquipoRepository.getInstance(JugadorEquipoInfoActivity.this).getEquipos();
    FragmentTabHost mTabHost;
    static String nombreTab;
    static boolean change = false;
    static int idEquipo;
    static JugadorEquipoInfoActivity jugadorEquipoActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugadorinfo);

        jugadorEquipoActivity = this;
        for(int i = 0; i < equiposTotales.size(); i++)
        {

            mTabHost = (FragmentTabHost) findViewById(R.id.tabhostEquipos);
            mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

            Bundle b = new Bundle();
            b.putInt("equipoId", equiposTotales.get(i).getId());
            idEquipo = equiposTotales.get(i).getId();
            mTabHost.addTab(
                    mTabHost.newTabSpec(equiposTotales.get(i).getNombre()).setIndicator(equiposTotales.get(i).getNombre(), null),
                    FragmentJugadorEquipoInfo.class, b);

        }

        if (mTabHost != null) {
            mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tabId) {
                    nombreTab = tabId;
                    change = true;
                }
            });
        }
        else
        {

        }


    }

    public JugadorEquipoInfoActivity()
    {

    }

    @Override
    public void onBackPressed() {
        Intent intento = new Intent(JugadorEquipoInfoActivity.this,HomeActivity.class);
        startActivity(intento);
    }



}
