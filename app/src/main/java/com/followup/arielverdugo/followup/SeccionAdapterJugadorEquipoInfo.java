package com.followup.arielverdugo.followup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.followup.arielverdugo.followup.interfaces.RecyclerViewClickListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.followup.arielverdugo.followup.SeccionAdapterEquipoInfo.SeccionEquipoInfoViewHolder.selectedItems;

/**
 * Created by arielverdugo on 4/9/17.
 */

public class SeccionAdapterJugadorEquipoInfo extends RecyclerView.Adapter<SeccionAdapterJugadorEquipoInfo.SeccionJugadorInfoViewHolder> {
    private List<Jugador> jugadores;
    private List<JugadorEquipo> jugadorEquipo;
    private List<JugadorEquipoFavorito> jugadorEquipoFavorito;
    public Equipo equipo;
    public static List<JugadorEquipoFavorito> jugadoresFavoritos;
    private static RecyclerViewClickListener itemListener;
    private Context c;

    List<Jugador>jugadoresIntermedio = new ArrayList<Jugador>();
    Map<Integer, Boolean> favoritos;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class SeccionJugadorInfoViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public ImageView fotoJugador;
        //public ImageView escudo;
        public TextView nombreJugador;
        public TextView nombreEquipo;
        public TextView posicion;
        public TextView altura;
        public CardView cv;
        public ImageView menu;
        public CheckBox favorito;
        public TextView idJugadorFavorito;
        public TextView idEquipoFavorito;






        public SeccionJugadorInfoViewHolder(View v) {
            super(v);

            //fotoJugador = (ImageView) v.findViewById(R.id.fotoEquipoJugadorInfo);
            fotoJugador = (ImageView) v.findViewById(R.id.fotoJugador);
            nombreJugador = (TextView) v.findViewById(R.id.nombreJugadorInfo);
            posicion = (TextView) v.findViewById(R.id.posicionJugadorEquipoInfo);
            //nombreEquipo = (TextView) v.findViewById(R.id.nombreEquipoJugadorInfo);
            //nombreEquipo = (TextView) v.findViewById(R.id.nombreEquipoJugadorInfo);
            cv = (CardView) v.findViewById(R.id.cardViewJugadorEquipoInfo);
            menu =(ImageView) v.findViewById(R.id.menu);
            favorito = (CheckBox) v.findViewById(R.id.star);
            idJugadorFavorito = (TextView) v.findViewById(R.id.idJugadorFavorito);
            idEquipoFavorito = (TextView) v.findViewById(R.id.idEquipoFavorito);


        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SeccionAdapterJugadorEquipoInfo(Equipo e, List<Jugador> jugadores ,Context c, Map<Integer, Boolean> favoritos) {
        this.equipo = e;
        this.jugadores = jugadores;
        this.c=c;
        this.favoritos = favoritos;

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
    public void onBindViewHolder(final SeccionJugadorInfoViewHolder viewHolder, final int i) {


        if(jugadores.get(i).getFoto() != null){
            Bitmap fotoJugador = BitmapFactory.decodeByteArray(jugadores.get(i).getFoto(), 0, jugadores.get(i).getFoto().length);
            viewHolder.fotoJugador.setImageBitmap(fotoJugador);

        } else {
            viewHolder.fotoJugador.setImageResource(R.drawable.sinimagen);
        }

        viewHolder.idJugadorFavorito.setText(Integer.toString(jugadores.get(i).getId()));
        viewHolder.idEquipoFavorito.setText(Integer.toString(equipo.getId()));
        viewHolder.nombreJugador.setText(jugadores.get(i).getNombre() + " " +jugadores.get(i).getApellido());
        viewHolder.posicion.setText(jugadores.get(i).getPosicion());
        viewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(viewHolder.menu,i,c,jugadores.get(i).getId());
            }
        });


        if(favoritos.size() > 0 && favoritos.get(jugadores.get(i).getId()) != null && favoritos.get(jugadores.get(i).getId()))
        {
            viewHolder.favorito.setChecked(true);
        }
        else
        {
            viewHolder.favorito.setChecked(false);
        }

        viewHolder.favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jugadoresFavoritos = JugadorRepository.;
                if(!viewHolder.favorito.isChecked())
                {

                    Map<String,String>paramsBusqueda = new HashMap<String,String>();
                    paramsBusqueda.put("id_equipo",viewHolder.idEquipoFavorito.getText().toString());
                    paramsBusqueda.put("id_jugador",viewHolder.idJugadorFavorito.getText().toString());
                    List<JugadorEquipo> je = JugadorEquipoRepository.getInstance(c).findWhere(paramsBusqueda);

                    Map<String,String>params = new HashMap<String,String>();
                    params.put("id_jugador_equipo",Integer.toString(je.get(0).getId()));
                    JugadorEquipoFavoritoRepository.getInstance(c).deleteJugadorEquipoFavoritoByParams(params);

                   //SeguimientoActivity.jugadoresFavoritos.remove(i);
                    //jugadoresFavoritos.remove(i);

                    //jugadorEquipoFavoritoActual.setFavorito(0);
                    //JugadorEquipoFavoritoRepository.getInstance(c).updateJugadorEquipoFavorito(jugadorEquipoFavoritoActual);

                }
                else {

                    Map<String,String>params = new HashMap<String,String>();
                    params.put("id_equipo",viewHolder.idEquipoFavorito.getText().toString());
                    params.put("id_jugador",viewHolder.idJugadorFavorito.getText().toString());

                    List<JugadorEquipo> je = JugadorEquipoRepository.getInstance(c).findWhere(params);

                    JugadorEquipoFavorito jef = new JugadorEquipoFavorito();
                    jef.setJugadorEquipo(je.get(0));
                    jef.setFavorito(1);
                    JugadorEquipoFavoritoRepository.getInstance(c).addJugadorEquipoFavorito(jef);


                    //SeguimientoActivity.jugadoresFavoritos.add(jugadorActual);
                    //jugadoresFavoritos.add(jugadorEquipoFavoritoActual);

                    //jugadorEquipoFavoritoActual.setFavorito(1);
                    //JugadorEquipoFavoritoRepository.getInstance(c).updateJugadorEquipoFavorito(jugadorEquipoFavoritoActual);
                }
            }
        });
    }

    private void showPopupMenu(View view,int position,Context context,int id) {
        // inflate menu
        PopupMenu popUp = new PopupMenu(view.getContext(),view);
        MenuInflater inflater = popUp.getMenuInflater();
        inflater.inflate(R.menu.delete_edit_jugadorequipoinfo, popUp.getMenu());
        popUp.setOnMenuItemClickListener(new MyMenuItemClickListener(position,c,id));
        popUp.show();


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return jugadores.size();
    }

}

class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{

    private RecyclerView.Adapter lastmAdapter;
    private int position;
    private Context context;
    private int id;
    private ImageView fotoJugador;
    public static final int INTENT_ELEGIR_IMAGEN = 1;
    public String RECEIVER_KEY = "1";
    private Boolean imageSelected = false;



    public MyMenuItemClickListener(int positon,Context context,int id) {
        this.position=positon;
        this.context = context;
        this.id = id;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.editarJugadorEquipo:
                Intent intento = new Intent(context,EditarJugadorActivity.class);
                intento.putExtra("id",id);
                context.startActivity(intento);
                Equipo e = EquipoRepository.getInstance(context).findEquipoById(JugadorEquipoInfoActivity.idEquipo);
                Map<Integer, Boolean> favoritos = JugadorEquipoRepository.getInstance(context).getFavoritosPorJugadorEquipo(e.getId());
                if(EditarJugadorActivity.editado.equals(true)) {
                    lastmAdapter = new SeccionAdapterJugadorEquipoInfo(e, new ArrayList<Jugador>(e.jugadores), context, favoritos);
                    FragmentJugadorEquipoInfo.mRecyclerViewStatic.setAdapter(lastmAdapter);
                }

                break;


            case R.id.eliminarJugadorEquipo:
                AlertDialog.Builder builderEliminar;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builderEliminar = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builderEliminar = new AlertDialog.Builder(context);
                }
                builderEliminar.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                JugadorRepository.getInstance(context).deleteJugadorById(id);
                                Equipo e = EquipoRepository.getInstance(context).findEquipoById(JugadorEquipoInfoActivity.idEquipo);
                                //para refrescar los equipos
                                Intent i1 = new Intent (context, JugadorEquipoInfoActivity.class);
                                context.startActivity(i1);
                                Map<Integer, Boolean> favoritos = JugadorEquipoRepository.getInstance(context).getFavoritosPorJugadorEquipo(e.getId());
                                lastmAdapter = new SeccionAdapterJugadorEquipoInfo(e,new ArrayList<Jugador>(e.jugadores),context, favoritos);
                                FragmentJugadorEquipoInfo.mRecyclerViewStatic.setAdapter(lastmAdapter);
                                //agregue esto

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            break;
            default:
        }
        return false;
    }


    private  Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o);
        // The new size we want to scale to
        final int REQUIRED_SIZE = 150;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o2);

    }

    public String[] getArrayWithSteps(Integer iMinValue,Integer iMaxValue, Integer iStep)
    {
        double iStepsArray = iMaxValue-iMinValue; //obtengo el largo del array

        String[] arrayValues= new String[(int)iStepsArray]; //creo un array de ese largo
        arrayValues[0] = String.valueOf(iMinValue);

        for(int i = 1; i < iStepsArray; i++)
        {
            arrayValues[i] = String.valueOf(Integer.parseInt(arrayValues[i-1])+iStep);
        }

        return arrayValues;
    }



}