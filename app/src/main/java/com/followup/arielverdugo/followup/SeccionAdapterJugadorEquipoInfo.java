package com.followup.arielverdugo.followup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.followup.arielverdugo.followup.interfaces.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 4/9/17.
 */

public class SeccionAdapterJugadorEquipoInfo extends RecyclerView.Adapter<SeccionAdapterJugadorEquipoInfo.SeccionJugadorInfoViewHolder> {
    private List<Jugador> jugadores;
    public Equipo equipo;

    private static RecyclerViewClickListener itemListener;
    private Context c;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class SeccionJugadorInfoViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public ImageView fotoJugador;
        public ImageView escudo;
        public TextView nombreJugador;
        public TextView nombreEquipo;
        public CardView cv;


        public SeccionJugadorInfoViewHolder(View v) {
            super(v);

            //fotoJugador = (ImageView) v.findViewById(R.id.fotoEquipoJugadorInfo);
            escudo = (ImageView) v.findViewById(R.id.escudoEquipoJugadorInfo);
            nombreJugador = (TextView) v.findViewById(R.id.nombreJugadorInfo);
            //nombreEquipo = (TextView) v.findViewById(R.id.nombreEquipoJugadorInfo);
            //nombreEquipo = (TextView) v.findViewById(R.id.nombreEquipoJugadorInfo);
            cv = (CardView) v.findViewById(R.id.cardViewJugadorEquipoInfo);

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SeccionAdapterJugadorEquipoInfo(Equipo e, List<Jugador> jugadores ,Context c) {
        this.equipo = equipo;
        this.jugadores = jugadores;
        this.c=c;

    }


    // Create new views (invoked by the layout manager)
    @Override
    public SeccionAdapterJugadorEquipoInfo.SeccionJugadorInfoViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_jugadorequipoinfo, parent, false);

        return new SeccionJugadorInfoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(SeccionJugadorInfoViewHolder viewHolder, int i) {

//int id = jugadoresTotales.get(i).getId();
        //Jugador jugadoresPorEquipo = JugadorRepository.getInstance(this.c).findJugadorById(id);
        //List<Jugador>jugadoresPorEquipo = JugadorEquipoRepository.getInstance(this.c).findJugadorPorEquipoById(id);

        /*if (jugadoresPorEquipo.getFoto() != null) {
            Bitmap fotoJugador = BitmapFactory.decodeByteArray(jugadoresPorEquipo.getFoto(), 0, jugadoresPorEquipo.getFoto().length);
            viewHolder.fotoJugador.setImageBitmap(fotoJugador);

        } else {
            viewHolder.fotoJugador.setImageResource(R.drawable.sinimagen);
        }*/

        //String nombreEquipo = equiposTotales.get(i).getNombre();
        viewHolder.nombreJugador.setText(jugadores.get(i).getNombre());
            //viewHolder.apellidoJugador.setText(jugadores.get(x).getApellido());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return jugadores.size();
    }

}