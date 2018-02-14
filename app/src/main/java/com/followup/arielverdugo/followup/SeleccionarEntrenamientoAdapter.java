package com.followup.arielverdugo.followup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.github.ivbaranov.mli.MaterialLetterIcon.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by arielverdugo on 24/1/18.
 */

public class SeleccionarEntrenamientoAdapter extends RecyclerView.Adapter<SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder> {


    public static class SeleccionarEntrenamientoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case

        public TextView nombre;
        public final MaterialLetterIcon mIconLibres;
        public final MaterialLetterIcon mIconDobles;
        public final MaterialLetterIcon mIconTriples;
        public ImageButton mImageButton;
        ArrayList<Integer> ids = new ArrayList<Integer>();
        //public int contador = 1;
        public int id = 1;
        public boolean crearEntrenamiento = false;
        public static int contadorLibres = 1;
        public static int contadorDobles = 1;
        public static int contadorTriples = 1;
        public static int flag = 0;
        public static Context contextSeleccionarEnetrenamiento;
        public static boolean crearLibre = false;
        public static boolean crearDoble = false;
        public static boolean crearTriple = false;





        public SeleccionarEntrenamientoViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.pruebaIcono);
            mIconLibres = (MaterialLetterIcon) v.findViewById(R.id.iconLibres);
            mIconDobles= (MaterialLetterIcon) v.findViewById(R.id.iconDobles);
            mIconTriples= (MaterialLetterIcon) v.findViewById(R.id.iconTriples);
            contextSeleccionarEnetrenamiento = mIconLibres.getContext();
            final RelativeLayout relativeEntrenamientos = (RelativeLayout) v.findViewById(R.id.relativeEntrenamientos);
            mImageButton= (ImageButton) v.findViewById(R.id.imageButton);

            mIconLibres.setClickable(true);
            mIconLibres.setOnClickListener(this);
            mIconLibres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImageButton suma;
                    ImageButton resta;

                    LayoutInflater inflater = (LayoutInflater) contextSeleccionarEnetrenamiento.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View popupView = inflater.inflate(R.layout.dialog_entrenamiento_simple,null);
                    suma = (ImageButton) popupView.findViewById(R.id.entrenamientoSuma);
                    resta = (ImageButton)popupView.findViewById(R.id.entrenamientoResta);
                    final TextView series = (TextView) popupView.findViewById(R.id.entrenamientoSeries);
                    final TextView tituloEntrenamiento = (TextView) popupView.findViewById(R.id.tituloEntrenamientoSimple);
                    final EditText cantidadRepeticiones = (EditText) popupView.findViewById(R.id.cantidadRepeticiones);
                    final TextView tituloRepeticiones = (TextView) popupView.findViewById(R.id.tituloRepeticiones);
                    Button okDobles = new Button(contextSeleccionarEnetrenamiento);
                    final Button okTriples = new Button(contextSeleccionarEnetrenamiento);

                    suma.setImageResource(R.drawable.suma);
                    suma.setBackgroundColor(Color.WHITE);
                    resta.setImageResource(R.drawable.resta);
                    resta.setBackgroundColor(Color.WHITE);

                    final AlertDialog.Builder biulder = new AlertDialog.Builder(contextSeleccionarEnetrenamiento).setTitle("Armá tu entrenamiento")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    crearLibre = true;
                                    Button okLibres = new Button(contextSeleccionarEnetrenamiento);
                                    TextView ok = new TextView(contextSeleccionarEnetrenamiento);
                                    ok.setTextSize(30);
                                    ok.setPadding(0,5,0,0);
                                    ok.setText("OK!");
                                    ok.setClickable(true);
                                    //RelativeLayout.LayoutParams paramsButton =  new RelativeLayout.LayoutParams(20, 20);
                                    //okLibres.setLayoutParams(paramsButton);
                                    //okLibres.setBackgroundResource(R.drawable.round_button);
                                    //okLibres.setLayoutParams(new RelativeLayout.LayoutParams(10,10));
                                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                    lp.addRule(RelativeLayout.BELOW,mIconLibres.getId());
                                    lp.addRule(RelativeLayout.ALIGN_START,mIconLibres.getId());
                                    //relativeEntrenamientos.addView(okLibres,lp);
                                    relativeEntrenamientos.addView(ok,lp);
                                    mIconLibres.setShapeColor(Color.GREEN);

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    biulder.setView(popupView);
                    final DialogInterface alertDialog = biulder.show();


                    suma.setOnClickListener(this);
                    suma.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //cuenta las dos views de cada fila,textview y edit text
                            //contador++;
                            contadorLibres ++;
                            if(contadorLibres == 2)
                            {
                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp.addRule(RelativeLayout.BELOW,series.getId());
                                lp.addRule(RelativeLayout.ALIGN_START,tituloEntrenamiento.getId());

                                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                                        250, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp2.addRule(RelativeLayout.BELOW,cantidadRepeticiones.getId());
                                lp2.addRule(RelativeLayout.ALIGN_START,tituloRepeticiones.getId());

                                TextView myText = new TextView(contextSeleccionarEnetrenamiento);
                                EditText editText = new EditText(contextSeleccionarEnetrenamiento);
                                editText.setText("10");
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                myText.setId(id);
                                myText.setTextSize(20);
                                myText.setPadding(0,50,0,0);
                                myText.setText("Serie: " + contadorLibres);
                                //despues de setear el primer id, le sumo 1 al array de ids
                                id++;
                                //ahora el id es un numero mayor que al id anterior,se lo seteo
                                editText.setId(id);
                                id++;
                                //le sumo devuelta para generar un id nuevo para el proximo
                                rela.addView(myText,lp);
                                rela.addView(editText,lp2);

                            }
                            else if(contadorLibres > 2)
                            {
                                int idAnterior = id;
                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                idAnterior--;
                                lp.addRule(RelativeLayout.BELOW,idAnterior);
                                lp.addRule(RelativeLayout.ALIGN_START,tituloEntrenamiento.getId());

                                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                                        250, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp2.addRule(RelativeLayout.BELOW,idAnterior);
                                lp2.addRule(RelativeLayout.ALIGN_START,tituloRepeticiones.getId());

                                TextView myText = new TextView(contextSeleccionarEnetrenamiento);
                                EditText editText = new EditText(contextSeleccionarEnetrenamiento);
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                editText.setText("10");
                                myText.setId(id);
                                myText.setPadding(0,50,0,0);
                                myText.setText("Serie: " + contadorLibres);
                                myText.setTextSize(20);
                                id++;
                                editText.setId(id);
                                id++;
                                rela.addView(myText,lp);
                                rela.addView(editText,lp2);

                            }
                            else{

                            }

                        }
                    });

                    resta.setOnClickListener(this);
                    resta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(contadorLibres <= 1)
                            {

                            }
                            else{

                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                //((ViewGroup)popupView.getParent()).removeView(popupView);
                                //rela.removeView((View) v.getParent());
                                for(int index=0; index<((ViewGroup)rela).getChildCount(); ++index) {
                                    View nextChild = ((ViewGroup)rela).getChildAt(index);
                                    nextChild.getId();
                                }
                                int indexEditText = ((ViewGroup)rela).getChildCount() -1;
                                int indexTextView = ((ViewGroup)rela).getChildCount() -2;
                                View editDelte = ((ViewGroup)rela).getChildAt(indexEditText);
                                View textDelete = ((ViewGroup)rela).getChildAt(indexTextView);
                                rela.removeView(editDelte);
                                rela.removeView(textDelete);
                                contadorLibres--;
                                id--;
                                id--;
                            }
                        }
                    });



                }
            });
            mIconDobles.setClickable(true);
            mIconDobles.setOnClickListener(this);
            mIconDobles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImageButton suma;
                    ImageButton resta;

                    LayoutInflater inflater = (LayoutInflater) contextSeleccionarEnetrenamiento.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View popupView = inflater.inflate(R.layout.dialog_entrenamiento_simple,null);
                    suma = (ImageButton) popupView.findViewById(R.id.entrenamientoSuma);
                    resta = (ImageButton)popupView.findViewById(R.id.entrenamientoResta);
                    final TextView series = (TextView) popupView.findViewById(R.id.entrenamientoSeries);
                    final TextView tituloEntrenamiento = (TextView) popupView.findViewById(R.id.tituloEntrenamientoSimple);
                    final EditText cantidadRepeticiones = (EditText) popupView.findViewById(R.id.cantidadRepeticiones);
                    final TextView tituloRepeticiones = (TextView) popupView.findViewById(R.id.tituloRepeticiones);


                    suma.setImageResource(R.drawable.suma);
                    suma.setBackgroundColor(Color.WHITE);
                    resta.setImageResource(R.drawable.resta);
                    resta.setBackgroundColor(Color.WHITE);

                    AlertDialog.Builder biulder = new AlertDialog.Builder(contextSeleccionarEnetrenamiento).setTitle("Armá tu entrenamiento")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    crearDoble = true;
                                    Button okDobles = new Button(contextSeleccionarEnetrenamiento);
                                    TextView ok = new TextView(contextSeleccionarEnetrenamiento);
                                    ok.setTextSize(30);
                                    ok.setPadding(0,5,0,0);
                                    ok.setText("OK!");
                                    ok.setClickable(true);
                                    //RelativeLayout.LayoutParams paramsButton =  new RelativeLayout.LayoutParams(20, 20);
                                    //okLibres.setLayoutParams(paramsButton);
                                    //okLibres.setBackgroundResource(R.drawable.round_button);
                                    //okLibres.setLayoutParams(new RelativeLayout.LayoutParams(10,10));
                                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                    lp.addRule(RelativeLayout.BELOW,mIconDobles.getId());
                                    lp.addRule(RelativeLayout.ALIGN_START,mIconDobles.getId());
                                    //relativeEntrenamientos.addView(okLibres,lp);
                                    relativeEntrenamientos.addView(ok,lp);
                                    mIconDobles.setShapeColor(Color.GREEN);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    biulder.setView(popupView);
                    final DialogInterface alertDialog = biulder.show();


                    suma.setOnClickListener(this);
                    suma.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //cuenta las dos views de cada fila,textview y edit text
                            contadorDobles++;
                            //contadorDobles = contador;
                            if(contadorDobles == 2)
                            {
                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp.addRule(RelativeLayout.BELOW,series.getId());
                                lp.addRule(RelativeLayout.ALIGN_START,tituloEntrenamiento.getId());

                                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                                        250, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp2.addRule(RelativeLayout.BELOW,cantidadRepeticiones.getId());
                                lp2.addRule(RelativeLayout.ALIGN_START,tituloRepeticiones.getId());

                                TextView myText = new TextView(contextSeleccionarEnetrenamiento);
                                EditText editText = new EditText(contextSeleccionarEnetrenamiento);
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                //myText.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                //myText.setId(contador);
                                myText.setId(id);
                                myText.setTextSize(20);
                                myText.setPadding(0,50,0,0);
                                myText.setText("Serie: " + contadorDobles);
                                //despues de setear el primer id, le sumo 1 al array de ids
                                id++;
                                //ahora el id es un numero mayor que al id anterior,se lo seteo
                                editText.setId(id);
                                id++;
                                //le sumo devuelta para generar un id nuevo para el proximo
                                rela.addView(myText,lp);
                                rela.addView(editText,lp2);

                            }
                            else if(contadorDobles > 2)
                            {
                                int idAnterior = id;
                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                idAnterior--;
                                lp.addRule(RelativeLayout.BELOW,idAnterior);
                                lp.addRule(RelativeLayout.ALIGN_START,tituloEntrenamiento.getId());

                                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                                        250, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp2.addRule(RelativeLayout.BELOW,idAnterior);
                                lp2.addRule(RelativeLayout.ALIGN_START,tituloRepeticiones.getId());

                                TextView myText = new TextView(contextSeleccionarEnetrenamiento);
                                EditText editText = new EditText(contextSeleccionarEnetrenamiento);
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                //myText.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                myText.setId(id);
                                myText.setPadding(0,50,0,0);
                                myText.setText("Serie: " + contadorDobles);
                                myText.setTextSize(20);
                                id++;
                                editText.setId(id);
                                id++;
                                rela.addView(myText,lp);
                                rela.addView(editText,lp2);

                            }
                            else{

                            }

                        }
                    });

                    resta.setOnClickListener(this);
                    resta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(contadorDobles <= 1)
                            {

                            }
                            else{

                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                //((ViewGroup)popupView.getParent()).removeView(popupView);
                                //rela.removeView((View) v.getParent());
                                for(int index=0; index<((ViewGroup)rela).getChildCount(); ++index) {
                                    View nextChild = ((ViewGroup)rela).getChildAt(index);
                                    nextChild.getId();
                                }
                                int indexEditText = ((ViewGroup)rela).getChildCount() -1;
                                int indexTextView = ((ViewGroup)rela).getChildCount() -2;
                                View editDelte = ((ViewGroup)rela).getChildAt(indexEditText);
                                View textDelete = ((ViewGroup)rela).getChildAt(indexTextView);
                                rela.removeView(editDelte);
                                rela.removeView(textDelete);
                                contadorDobles--;
                                id--;
                                id--;
                            }
                        }
                    });



                }
            });
            mIconTriples.setClickable(true);
            mIconTriples.setOnClickListener(this);
            mIconTriples.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImageButton suma;
                    ImageButton resta;

                    LayoutInflater inflater = (LayoutInflater) contextSeleccionarEnetrenamiento.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View popupView = inflater.inflate(R.layout.dialog_entrenamiento_simple,null);
                    suma = (ImageButton) popupView.findViewById(R.id.entrenamientoSuma);
                    resta = (ImageButton)popupView.findViewById(R.id.entrenamientoResta);
                    final TextView series = (TextView) popupView.findViewById(R.id.entrenamientoSeries);
                    final TextView tituloEntrenamiento = (TextView) popupView.findViewById(R.id.tituloEntrenamientoSimple);
                    final EditText cantidadRepeticiones = (EditText) popupView.findViewById(R.id.cantidadRepeticiones);
                    final TextView tituloRepeticiones = (TextView) popupView.findViewById(R.id.tituloRepeticiones);


                    suma.setImageResource(R.drawable.suma);
                    suma.setBackgroundColor(Color.WHITE);
                    resta.setImageResource(R.drawable.resta);
                    resta.setBackgroundColor(Color.WHITE);

                    AlertDialog.Builder biulder = new AlertDialog.Builder(contextSeleccionarEnetrenamiento).setTitle("Armá tu entrenamiento")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    crearTriple = true;
                                    Button okTriples = new Button(contextSeleccionarEnetrenamiento);
                                    TextView ok = new TextView(contextSeleccionarEnetrenamiento);
                                    ok.setTextSize(30);
                                    ok.setPadding(0,5,0,0);
                                    ok.setText("OK!");
                                    ok.setClickable(true);
                                    //RelativeLayout.LayoutParams paramsButton =  new RelativeLayout.LayoutParams(20, 20);
                                    //okLibres.setLayoutParams(paramsButton);
                                    //okLibres.setBackgroundResource(R.drawable.round_button);
                                    //okLibres.setLayoutParams(new RelativeLayout.LayoutParams(10,10));
                                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                    lp.addRule(RelativeLayout.BELOW,mIconTriples.getId());
                                    lp.addRule(RelativeLayout.ALIGN_START,mIconTriples.getId());
                                    //relativeEntrenamientos.addView(okLibres,lp);
                                    relativeEntrenamientos.addView(ok,lp);
                                    mIconTriples.setShapeColor(Color.GREEN);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    biulder.setView(popupView);
                    final DialogInterface alertDialog = biulder.show();


                    suma.setOnClickListener(this);
                    suma.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //cuenta las dos views de cada fila,textview y edit text
                            contadorTriples++;
                            //contadorTriples = contador;
                            if(contadorTriples == 2)
                            {
                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp.addRule(RelativeLayout.BELOW,series.getId());
                                lp.addRule(RelativeLayout.ALIGN_START,tituloEntrenamiento.getId());

                                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                                        250, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp2.addRule(RelativeLayout.BELOW,cantidadRepeticiones.getId());
                                lp2.addRule(RelativeLayout.ALIGN_START,tituloRepeticiones.getId());

                                TextView myText = new TextView(contextSeleccionarEnetrenamiento);
                                EditText editText = new EditText(contextSeleccionarEnetrenamiento);
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                //myText.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                //myText.setId(contador);
                                myText.setId(id);
                                myText.setTextSize(20);
                                myText.setPadding(0,50,0,0);
                                myText.setText("Serie: " + contadorTriples);
                                //despues de setear el primer id, le sumo 1 al array de ids
                                id++;
                                //ahora el id es un numero mayor que al id anterior,se lo seteo
                                editText.setId(id);
                                id++;
                                //le sumo devuelta para generar un id nuevo para el proximo
                                rela.addView(myText,lp);
                                rela.addView(editText,lp2);

                            }
                            else if(contadorTriples > 2)
                            {
                                int idAnterior = id;
                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                idAnterior--;
                                lp.addRule(RelativeLayout.BELOW,idAnterior);
                                lp.addRule(RelativeLayout.ALIGN_START,tituloEntrenamiento.getId());

                                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                                        250, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                lp2.addRule(RelativeLayout.BELOW,idAnterior);
                                lp2.addRule(RelativeLayout.ALIGN_START,tituloRepeticiones.getId());

                                TextView myText = new TextView(contextSeleccionarEnetrenamiento);
                                EditText editText = new EditText(contextSeleccionarEnetrenamiento);
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                //myText.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                myText.setId(id);
                                myText.setPadding(0,50,0,0);
                                myText.setText("Serie: " + contadorTriples);
                                myText.setTextSize(20);
                                id++;
                                editText.setId(id);
                                id++;
                                rela.addView(myText,lp);
                                rela.addView(editText,lp2);

                            }
                            else{

                            }

                        }
                    });

                    resta.setOnClickListener(this);
                    resta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(contadorTriples <= 1)
                            {

                            }
                            else{

                                RelativeLayout rela = (RelativeLayout) popupView.findViewById(R.id.relativeDialogSimple);
                                //((ViewGroup)popupView.getParent()).removeView(popupView);
                                //rela.removeView((View) v.getParent());
                                for(int index=0; index<((ViewGroup)rela).getChildCount(); ++index) {
                                    View nextChild = ((ViewGroup)rela).getChildAt(index);
                                    nextChild.getId();
                                }
                                int indexEditText = ((ViewGroup)rela).getChildCount() -1;
                                int indexTextView = ((ViewGroup)rela).getChildCount() -2;
                                View editDelte = ((ViewGroup)rela).getChildAt(indexEditText);
                                View textDelete = ((ViewGroup)rela).getChildAt(indexTextView);
                                rela.removeView(editDelte);
                                rela.removeView(textDelete);
                                contadorTriples--;
                                id--;
                                id--;
                            }
                        }
                    });

                }
            });

        }

        @Override
        public void onClick(View v) {

        }

    }




    // Provide a suitable constructor (depends on the kind of dataset)
    public SeleccionarEntrenamientoAdapter() {


    }

    // Create new views (invoked by the layout manager)
    @Override
    public SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_seleccionar_entrenamiento, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder viewHolder,final int i) {

        viewHolder.mIconLibres.setLetter("L");
        viewHolder.mIconLibres.setLetterSize(20);
        viewHolder.mIconLibres.setShapeType(Shape.CIRCLE);
        viewHolder.mIconLibres.setClickable(true);

        viewHolder.mIconDobles.setLetter("D");
        viewHolder.mIconDobles.setLetterSize(20);
        viewHolder.mIconDobles.setShapeType(Shape.CIRCLE);

        viewHolder.mIconTriples.setLetter("T");
        viewHolder.mIconTriples.setLetterSize(20);
        viewHolder.mIconTriples.setShapeType(Shape.CIRCLE);

        viewHolder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view,SeleccionarEntrenamientoViewHolder.contextSeleccionarEnetrenamiento,i, SeleccionarEntrenamientoViewHolder.contadorLibres,SeleccionarEntrenamientoViewHolder.contadorDobles,SeleccionarEntrenamientoViewHolder.contadorTriples);
            }
        });


    }
    private void showPopupMenu(View view,Context context,int position,int contadorLibres,int contadorDobles,int contadorTriples) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.generar_entrenamiento, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuEntrenamientoItemClickListener(view,context,position,contadorLibres,contadorDobles,contadorTriples));
        popup.show();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 5;
    }
}

  class MyMenuEntrenamientoItemClickListener implements PopupMenu.OnMenuItemClickListener {

    private int position;
    private Context context;
    private Intent intent;
    public MyMenuEntrenamientoItemClickListener(View view,Context context,int positon,int contadorLibres,int contadorDobles,int contadorTriples) {
        this.position=positon;
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.generarEntrenamiento:
               //llamar al activity para crear el tabhost
                if(context instanceof InicioSeguimientoEntrenamientoActivity)
                {
                    intent = new Intent("entrenamiento");
                    intent.putExtra("libres",SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorLibres);
                    intent.putExtra("dobles",SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorDobles);
                    intent.putExtra("triples",SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorTriples);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    Intent intento2 = new Intent(context,InicioSeguimientoEntrenamientoActivity.class);
                    //((InicioSeguimientoEntrenamientoActivity)context).onCreate();
                    SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.flag = 1;
                    context.startActivity(intento2);

                }

                return true;
            case R.id.reestablecerEntrenamiento:

                SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorLibres = 1;
                SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorDobles = 1;
                SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorTriples = 1;
                Intent intento2 = new Intent(context,InicioSeguimientoEntrenamientoActivity.class);
                //((InicioSeguimientoEntrenamientoActivity)context).onCreate();
                SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.flag = 0;
                EstadisticaEntrenamientoSimpleAdapter.contadorSimple = 0;
                EstadisticaEntrenamientoSimpleAdapter.tagsEntrenamientoSimple.clear();
                EstadisticaEntrenamientoSimpleAdapter.viewsSimple.clear();
                EstadisticaEntrenamientoDobleAdapter.contadorDoble = 0;
                EstadisticaEntrenamientoDobleAdapter.tagsEntrenamientoDoble.clear();
                EstadisticaEntrenamientoDobleAdapter.viewsDoble.clear();
                EstadisticaEntrenamientoTripleAdapter.contadorTriple = 0;
                EstadisticaEntrenamientoTripleAdapter.tagsEntrenamientoTriple.clear();
                EstadisticaEntrenamientoTripleAdapter.viewsTriple.clear();
                context.startActivity(intento2);

                return true;

            default:
        }
        return false;
    }
}
