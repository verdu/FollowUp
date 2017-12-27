package com.followup.arielverdugo.followup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 21/12/17.
 */

public class CustomAdapterJugadores extends BaseAdapter implements SpinnerAdapter{

    List<String>nombre;
    List<String> apellido;
    List<byte[]> foto;
    private Context c;
    List<Jugador> jugadoresSeguimiento = new ArrayList<Jugador>();
    LayoutInflater inflter;
    public Resources res;
    TextView dropdown;


    public CustomAdapterJugadores(List<String> nombre,List<String> apellido,List<byte[]> foto,Context c){

        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
        this.c = c;
        jugadoresSeguimiento = JugadorRepository.getInstance(c).getJugadores();
        inflter = (LayoutInflater.from(c));
    }
    @Override
    public int getCount() {
        return jugadoresSeguimiento.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = inflter.inflate(R.layout.custom_spinner_jugadores,null);

        ImageView fotoJugador = (ImageView) view.findViewById(R.id.imagen_perfilJugador);
        TextView nombreJugador = (TextView) view.findViewById(R.id.nombreJugadorSeguimiento);
        TextView apellidoJugador = (TextView) view.findViewById(R.id.apellidoJugadorSeguimiento);


        if(jugadoresSeguimiento.get(position).getFoto() == null)
        {

        }
        else
        {
            Bitmap bitmapFoto = BitmapFactory.decodeByteArray(jugadoresSeguimiento.get(position).getFoto(), 0, jugadoresSeguimiento.get(position).getFoto().length);
            fotoJugador.setImageBitmap(bitmapFoto);
        }

        nombreJugador.setText(jugadoresSeguimiento.get(position).getNombre());
        apellidoJugador.setText(jugadoresSeguimiento.get(position).getApellido());

        return view;
    }


}
