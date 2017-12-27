package com.followup.arielverdugo.followup;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;


public class SeguimientoActivity extends AppCompatActivity {

    static List<Jugador>jugadoresFavoritos = new ArrayList<Jugador>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(new SeguimientoPageAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("Inicio");
        navigationTabStrip.setViewPager(viewPager);


        jugadoresFavoritos =  JugadorRepository.getInstance(this).getJugadoresFavoritos();
    }

}