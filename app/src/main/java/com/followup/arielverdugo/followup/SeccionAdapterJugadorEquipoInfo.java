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
import java.util.List;

import static com.followup.arielverdugo.followup.SeccionAdapterEquipoInfo.SeccionEquipoInfoViewHolder.selectedItems;

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
        //public ImageView escudo;
        public TextView nombreJugador;
        public TextView nombreEquipo;
        public TextView posicion;
        public TextView altura;
        public CardView cv;
        public ImageView menu;


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
            final CheckBox checkBox = (CheckBox) v.findViewById(R.id.star);




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
    public void onBindViewHolder(final SeccionJugadorInfoViewHolder viewHolder, final int i) {


        final int id = jugadores.get(i).getId();
        if(jugadores.get(i).getFoto() != null){
            Bitmap fotoJugador = BitmapFactory.decodeByteArray(jugadores.get(i).getFoto(), 0, jugadores.get(i).getFoto().length);
            viewHolder.fotoJugador.setImageBitmap(fotoJugador);

        } else {
            viewHolder.fotoJugador.setImageResource(R.drawable.sinimagen);
        }


        viewHolder.nombreJugador.setText(jugadores.get(i).getNombre() + " " +jugadores.get(i).getApellido());
        viewHolder.posicion.setText(jugadores.get(i).getPosicion());
        viewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(viewHolder.menu,i,c,id);
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
                final Jugador jugadorEditar = JugadorRepository.getInstance(context).findJugadorById(id);

                LayoutInflater inflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE);
                final ViewGroup popupViewEditar = (ViewGroup) inflater.inflate(R.layout.dialog_editar_jugador, null);
                String nombreAnterior = jugadorEditar.getNombre();
                String apellidoAnterior = jugadorEditar.getApellido();
                byte[] fotoAnterior = jugadorEditar.getFoto();


                EditText nombre = (EditText) popupViewEditar.findViewById(R.id.editarNombreJugador);
                nombre.setText(nombreAnterior);
                EditText apellido = (EditText) popupViewEditar.findViewById(R.id.editarApellidoJugador);
                apellido.setText(apellidoAnterior);


                if (fotoAnterior == null)
                {
                    fotoJugador = (ImageView) popupViewEditar.findViewById(R.id.editarFotoJugador);
                    fotoJugador.setImageResource(R.drawable.anadirimagen);
                    fotoJugador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //para ir a la galeria
                            /*Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                            getIntent.setType("image/*");

                            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            pickIntent.setType("image/*");

                            Intent chooserIntent = Intent.createChooser(getIntent, "Elegir imagen");
                            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                            //utiliza la constante INTENT_ELEGIR_IMAGEN en onActivityResult
                            ((Activity) context).startActivityForResult(chooserIntent, INTENT_ELEGIR_IMAGEN);*/
                            /*
                            Intent pickImage = new Intent(context, PickImageActivity.class);
                            pickImage.putExtra(RECEIVER_KEY, new ResultReceiver(null) {
                                public void onReceive(int resultCode, Bundle data) {
                                    System.out.println(data.get("img"));
                                    Drawable d = new BitmapDrawable(context.getResources(), (Bitmap)data.get("img"));

                                    fotoJugador.setImageDrawable(d);
                                }
                            });
                            context.startActivity(pickImage);*/

                        }
                    });

                }
                else
                {
                    fotoJugador = (ImageView) popupViewEditar.findViewById(R.id.editarFotoJugador);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(fotoAnterior, 0, fotoAnterior.length);
                    fotoJugador.setImageBitmap(bitmap);

                    fotoJugador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //para ir a la galeria
                            /*Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                            getIntent.setType("image/*");

                            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            pickIntent.setType("image/*");

                            Intent chooserIntent = Intent.createChooser(getIntent, "Elegir imagen");
                            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                            //utiliza la constante INTENT_ELEGIR_IMAGEN en onActivityResult
                            ((Activity) context).startActivityForResult(chooserIntent, INTENT_ELEGIR_IMAGEN);*/


                            /*Intent pickImage = new Intent(context, PickImageActivity.class);
                            Handler handler = null;
                            pickImage.putExtra(RECEIVER_KEY, new ResultReceiver(handler) {

                                public void onReceive(int resultCode, Bundle data) {

                                }
                            });
                            Bundle things = new Bundle();

                            context.startActivity(pickImage);*/


                        }
                    });
                }
                //se crea y llena el spiner de equipos
                List<Equipo> equipos = EquipoRepository.getInstance(context).getEquipos();
                ArrayList<String> equiposNombre = new ArrayList<>();
                for (int i = 0; i < equipos.size(); i++)
                {
                    equiposNombre.add(equipos.get(i).getNombre());
                }

                final Spinner spinnerEquipos = (Spinner) popupViewEditar.findViewById(R.id.editarEquipoJugador);
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(context,R.layout.spinner_equipos, equiposNombre);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Cree una clase NothingSelectedSpinnerAdapter y un xml Spinner_row_selected
                spinnerEquipos.setAdapter(adapter);
                String compareValue = jugadorEditar.getEquipo().getNombre();

                if (!compareValue.equals(null)) {
                    int spinnerPosition = adapter.getPosition(compareValue);
                    spinnerEquipos.setSelection(spinnerPosition);
                }


                //se crea y llena el spinner de posciones
                ArrayList<String> posicionesJugadores = new ArrayList<>();
                posicionesJugadores.add("Base");
                posicionesJugadores.add("Ayuda Base");
                posicionesJugadores.add("Alero");
                posicionesJugadores.add("Ala Pivot");
                posicionesJugadores.add("Pivot");

                final Spinner spinnerPosiciones = (Spinner) popupViewEditar.findViewById(R.id.editarPosicionJugador);
                ArrayAdapter<String>adapterPosiciones;
                adapterPosiciones = new ArrayAdapter<String>(context,R.layout.spinner_posicion, posicionesJugadores);
                adapterPosiciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPosiciones.setAdapter(adapterPosiciones);
                String compareValuePosicion = jugadorEditar.getPosicion();

                if (!compareValuePosicion.equals(null)) {
                    int spinnerPosition = adapterPosiciones.getPosition(compareValuePosicion);
                    spinnerPosiciones.setSelection(spinnerPosition);
                }

                //se crea y llena el spinner de altura

                Integer alturaMinima = 160;
                Integer alturaMaxima = 221;
                Integer step = 1;
                String[] myValues = getArrayWithSteps(alturaMinima, alturaMaxima, step);
                NumberPicker picker = new NumberPicker(context);
                picker.setDisplayedValues(myValues);
                picker.setWrapSelectorWheel(false);

                final Spinner spinnerAlturas = (Spinner) popupViewEditar.findViewById(R.id.editarAlturaJugador);
                ArrayAdapter<String> adapterAltura;
                adapterAltura = new ArrayAdapter<String>(context,R.layout.spinner_altura, myValues);
                adapterAltura.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerAlturas.setAdapter(adapterAltura);
                int alturaJugador = jugadorEditar.getAltura();
                String compareValueAltura = Integer.toString(alturaJugador);

                if (!compareValueAltura.equals(null)) {
                    int spinnerPosition = adapterAltura.getPosition(compareValueAltura);
                    spinnerAlturas.setSelection(spinnerPosition);
                }



                AlertDialog.Builder builderEditar =
                        new AlertDialog.Builder(context)
                                .setTitle("Editar Jugador")
                                .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // capturar y gaurdadr en bd
                                        final String NOMBRE = (((TextView) popupViewEditar.findViewById(R.id.editarNombreJugador)).getText().toString());
                                        final String APELLIDO = (((TextView) popupViewEditar.findViewById(R.id.editarApellidoJugador)).getText().toString());
                                        Bitmap FOTO = null;
                                        FOTO = ((BitmapDrawable) ((ImageView) popupViewEditar.findViewById(R.id.editarFotoJugador)).getDrawable()).getBitmap();

                                        jugadorEditar.setNombre(NOMBRE);
                                        jugadorEditar.setApellido(APELLIDO);
                                        jugadorEditar.setFoto(Utils.getByteArrayFromBitmap(FOTO));

                                        JugadorRepository.getInstance(context).updateJugador(jugadorEditar);
                                        Toast.makeText(context, "Jugador editado", Toast.LENGTH_SHORT).show();

                                        //se refresca el cardview

                                        dialog.dismiss();

                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                builderEditar.setView(popupViewEditar);
                builderEditar.show();

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
                                lastmAdapter = new SeccionAdapterJugadorEquipoInfo(e,new ArrayList<Jugador>(e.jugadores),context);
                                FragmentJugadorEquipoInfo.mRecyclerViewStatic.setAdapter(lastmAdapter);
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