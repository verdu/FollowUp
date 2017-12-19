package com.followup.arielverdugo.followup;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
        /*if(jugadoresFavoritos.size() != 0)
        for (int w = 0; w < jugadoresFavoritos.size(); w++) {
            for (int z = w + 1; z < jugadoresFavoritos.size(); z++) {
                if (jugadoresFavoritos.get(z).equals(jugadoresFavoritos.get(w))) {
                    repetido++;
                } else  {

                }
            }
        }*/

        jugadoresFavoritos =  JugadorRepository.getInstance(this).getJugadoresFavoritos();
    }
}