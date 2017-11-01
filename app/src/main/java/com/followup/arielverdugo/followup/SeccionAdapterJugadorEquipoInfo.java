package com.followup.arielverdugo.followup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.followup.arielverdugo.followup.interfaces.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 4/9/17.
 */

public class SeccionAdapterJugadorEquipoInfo extends RecyclerView.Adapter<SeccionAdapterJugadorEquipoInfo.SeccionJugadorInfoViewHolder> {
    private List<Equipo> equipos;
    private List<Jugador> jugadores;

    private static RecyclerViewClickListener itemListener;
    private Context c;
    List<Jugador>jugadoresTotales =  JugadorRepository.getInstance(this.c).getJugadores();


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class SeccionJugadorInfoViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public ImageView fotoJugador;
        public ImageView escudo;
        public ListView nombreApellidoJugador;
        public TextView nombreEquipo;
        public CardView cv;


        public SeccionJugadorInfoViewHolder(View v) {
            super(v);

            //fotoJugador = (ImageView) v.findViewById(R.id.fotoEquipoJugadorInfo);
            escudo = (ImageView) v.findViewById(R.id.escudoEquipoJugadorInfo);
            //nombreApellidoJugador = (ListView) v.findViewById(R.id.nombreApellidoJugadorInfo);
            nombreEquipo = (TextView) v.findViewById(R.id.nombreEquipoJugadorInfo);
            nombreEquipo = (TextView) v.findViewById(R.id.nombreEquipoJugadorInfo);
            cv = (CardView) v.findViewById(R.id.cardViewJugadorEquipoInfo);

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SeccionAdapterJugadorEquipoInfo(List<Equipo> equipos,List<Jugador>jugadores,Context c) {
        this.equipos = equipos;
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

        if (equipos.get(i).getEscudo() != null) {
            Bitmap escudoEquipo = BitmapFactory.decodeByteArray(equipos.get(i).getEscudo(), 0, equipos.get(i).getEscudo().length);
            viewHolder.escudo.setImageBitmap(escudoEquipo);

        } else {
            viewHolder.escudo.setImageResource(R.drawable.sinimagen);
        }
        viewHolder.nombreEquipo.setText(equipos.get(i).getNombre());




        int id = jugadoresTotales.get(i).getId();
        Jugador jugadoresPorEquipo = JugadorRepository.getInstance(this.c).findJugadorById(id);
        //List<Jugador>jugadoresPorEquipo = JugadorEquipoRepository.getInstance(this.c).findJugadorPorEquipoById(id);

        /*if (jugadoresPorEquipo.getFoto() != null) {
            Bitmap fotoJugador = BitmapFactory.decodeByteArray(jugadoresPorEquipo.getFoto(), 0, jugadoresPorEquipo.getFoto().length);
            viewHolder.fotoJugador.setImageBitmap(fotoJugador);

        } else {
            viewHolder.fotoJugador.setImageResource(R.drawable.sinimagen);
        }*/

        String nombreEquipo = equipos.get(i).getNombre();

        ArrayList<String>valores = new ArrayList<>();
        for(int x = 0; x < jugadoresTotales.size(); x++)
        {
            if(nombreEquipo.equals(jugadores.get(x).getEquipo().toString()))
            {
                valores.add(jugadores.get(x).getNombre() + " " +jugadores.get(x).getApellido());
            }
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(this.c,android.R.layout.simple_list_item_1,valores);
            //viewHolder.nombreApellidoJugador.setAdapter(adapter);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return equipos.size();
    }



}