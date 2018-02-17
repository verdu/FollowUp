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
    List<String> equipo;
    private Context c;
    List<Jugador> jugadoresSeguimiento = new ArrayList<Jugador>();
    LayoutInflater inflter;
    public Resources res;
    TextView dropdown;


    public CustomAdapterJugadores(List<String> nombre,List<String> apellido,List<byte[]> foto, List<String> equipo, Context c){

        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
        this.c = c;
        this.equipo = equipo;
        inflter = (LayoutInflater.from(c));
    }
    @Override
    public int getCount() {
        return nombre.size();
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
        //TextView nombreEquipo = (TextView) view.findViewById(R.id.nombreEquipoSeguimiento);


        if(foto.get(position) == null)
        {

        }
        else
        {
            Bitmap bitmapFoto = BitmapFactory.decodeByteArray(foto.get(position), 0, foto.get(position).length);
            fotoJugador.setImageBitmap(bitmapFoto);
        }

        nombreJugador.setText(nombre.get(position));
        apellidoJugador.setText(apellido.get(position));
        //nombreEquipo.setText(equipo.get(position));

        return view;
    }


}
