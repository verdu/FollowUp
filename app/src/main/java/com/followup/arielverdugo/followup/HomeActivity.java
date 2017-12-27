package com.followup.arielverdugo.followup;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.followup.arielverdugo.followup.R.id.textoSeccion;

public class HomeActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView tituloSeccion;
    private ImageView agregar;
    private ImageView escudo;
    private ImageView foto;
    public static final int INTENT_ELEGIR_IMAGEN = 1;
    public static final int INTENT_ELEGIR_FOTO = 2;
    private Boolean imageSelected = false;
    private Boolean fotoSelected = false;
    private List<Equipo> equipos;
    private Integer equipoSelected;
    private int IS_TRUE = 1;
    private int IS_FALSE = 0;




    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());



                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = new SessionManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        RecyclerTouchListener r = new RecyclerTouchListener(this,
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well

                manageClickEvent(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                manageLongClickEvent(view, position);
            }
        });

        mRecyclerView.addOnItemTouchListener(r);

        //if the content do not change
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Seccion> secciones = new ArrayList<Seccion>();
        secciones.add(new Seccion(R.drawable.basketballteam, "Equipos"));
        secciones.add(new Seccion(R.drawable.basketballplayer, "Jugadores"));
        secciones.add(new Seccion(R.drawable.stadistics, "Seguimientos"));
        secciones.add(new Seccion(R.drawable.basketballfield, "Tips"));

        mAdapter = new SeccionAdapter(secciones);
        mRecyclerView.setAdapter(mAdapter);

        agregar = (ImageView) findViewById(R.id.agregar);
        tituloSeccion = (TextView) findViewById(textoSeccion);
    }

    private void manageClickEvent(View seccion, int posicion) {
        TextView textoSeccion = (TextView) seccion.findViewById(R.id.textoSeccion);

        switch (textoSeccion.getText().toString()) {
            case "Equipos":
                Intent intento = new Intent(HomeActivity.this,EquipoInfoActivity.class);
                startActivity(intento);
                break;
            case "Jugadores":
                Intent intentoDos = new Intent(HomeActivity.this,JugadorEquipoInfoActivity.class);
                startActivity(intentoDos);
                break;
            case "Seguimientos":


                break;
            case "Tips":

                Toast.makeText(HomeActivity.this, "Situación", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void manageLongClickEvent(View seccion, int posicion) {
        TextView textoSeccion = (TextView) seccion.findViewById(R.id.textoSeccion);

        switch (textoSeccion.getText().toString()) {
            case "Equipos":
                Intent intento = new Intent(HomeActivity.this,EquipoInfoActivity.class);
                startActivity(intento);
                break;
            case "Jugadores":
                Intent intentoDos = new Intent(HomeActivity.this,JugadorEquipoInfoActivity.class);
                startActivity(intentoDos);
                break;
            case "Seguimientos":
                Intent intentoCuatro = new Intent(HomeActivity.this,SeguimientoActivity.class);
                startActivity(intentoCuatro);
                break;
            case "Tips":
                Intent intentoTres = new Intent(HomeActivity.this,TipsActivity.class);
                startActivity(intentoTres);
            default:
                break;
        }
    }

    public void onClickAgregar(View v) {
        ViewGroup row = (ViewGroup) v.getParent();
        TextView textoSeccion = (TextView) row.findViewById(R.id.textoSeccion);
        final ViewGroup popupView = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_agregar_equipo,null);
        final ViewGroup popupViewJugadores = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_agregar_jugador,null);

        switch (textoSeccion.getText().toString()) {
            case "Equipos":

                escudo = (ImageView) popupView.findViewById(R.id.escudo);
                escudo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //para ir a la galeria
                        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        getIntent.setType("image/*");

                        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickIntent.setType("image/*");

                        Intent chooserIntent = Intent.createChooser(getIntent, "Elegir imagen");
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                        //utiliza la constante INTENT_ELEGIR_IMAGEN en onActivityResult
                        startActivityForResult(chooserIntent, INTENT_ELEGIR_IMAGEN);
                    }
                });



                AlertDialog.Builder alertDialogBuilder =
                        new AlertDialog.Builder(HomeActivity.this)
                                .setTitle("Equipo")
                                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // capturar y gaurdadr en bd
                                        final String NOMBRE = (((TextView)popupView.findViewById(R.id.nombreNuevoEquipo)).getText().toString());
                                        final String ALIAS = (((TextView)popupView.findViewById(R.id.alias)).getText().toString());
                                        final String BARRIO = (((TextView)popupView.findViewById(R.id.barrio)).getText().toString());
                                        final String DIRECCION = (((TextView)popupView.findViewById(R.id.direccion)).getText().toString());
                                        Bitmap ESCUDO = null;
                                        if(imageSelected) {
                                            ESCUDO =((BitmapDrawable) ((ImageView) popupView.findViewById(R.id.escudo)).getDrawable()).getBitmap();
                                        }

                                        /* if(NOMBRE.isEmpty() || ALIAS.isEmpty() || BARRIO.isEmpty() || DIRECCION.isEmpty())
                                        {
                                            Toast.makeText(HomeActivity.this, "Datos insuficientes", Toast.LENGTH_SHORT).show();

                                        } */
                                        //else {
                                            Equipo e = new Equipo(NOMBRE,ALIAS,BARRIO,DIRECCION,Utils.getByteArrayFromBitmap(ESCUDO),null);
                                            EquipoRepository.getInstance(HomeActivity.this).addEquipo(e);
                                            Toast.makeText(HomeActivity.this, "Equipo agregado", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        //}
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                alertDialogBuilder.setView(popupView);
                AlertDialog alertDialog = alertDialogBuilder.show();

                break;

            case "Jugadores":

                foto = (ImageView) popupViewJugadores.findViewById(R.id.fotoJugador);
                foto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //para ir a la galeria
                        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        getIntent.setType("image/*");

                        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickIntent.setType("image/*");

                        Intent chooserIntent = Intent.createChooser(getIntent, "Elegir foto");
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                        //utiliza la constante INTENT_ELEGIR_FOTO en onActivityResult
                        startActivityForResult(chooserIntent, INTENT_ELEGIR_FOTO);
                    }
                });

                //Spinner equipo
                //Capturo todos los nombres de los equipos y seteo el spinner
                equipos = EquipoRepository.getInstance(this).getEquipos();
                ArrayList<String> equiposNombre = new ArrayList<>();
                for (int i = 0; i < equipos.size(); i++)
                {
                    equiposNombre.add(equipos.get(i).getNombre());
                }

                final Spinner spinnerEquipos = (Spinner) popupViewJugadores.findViewById(R.id.equipoJugador);
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(HomeActivity.this,R.layout.spinner_equipos, equiposNombre);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Cree una clase NothingSelectedSpinnerAdapter y un xml Spinner_row_selected
                spinnerEquipos.setAdapter(
                        new NothingSelectedSpinnerAdapter(
                                adapter,
                                R.layout.spinner_row_nothing_selected_equipo,
                                this));

                spinnerEquipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        if(pos > 0) {
                            equipoSelected = pos-1;
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                //Spinner posicion
                ArrayList<String> posicionesJugadores = new ArrayList<>();
                posicionesJugadores.add("Base");
                posicionesJugadores.add("Ayuda Base");
                posicionesJugadores.add("Alero");
                posicionesJugadores.add("Ala Pivot");
                posicionesJugadores.add("Pivot");
                final Spinner spinnerPosicion = (Spinner) popupViewJugadores.findViewById(R.id.posicionJugador);
                ArrayAdapter<String> adapterPosicion;
                adapterPosicion = new ArrayAdapter<String>(HomeActivity.this,R.layout.spinner_posicion, posicionesJugadores);
                adapterPosicion.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                spinnerPosicion.setAdapter(new NothingSelectedSpinnerAdapter(
                        adapterPosicion,
                        R.layout.spinner_row_nothing_selected_posicion,
                        this));


                spinnerPosicion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                //Spinner altura
                Integer alturaMinima = 160;
                Integer alturaMaxima = 221;
                Integer step = 1;
                String[] myValues = getArrayWithSteps(alturaMinima, alturaMaxima, step);
                NumberPicker picker = new NumberPicker(HomeActivity.this);
                picker.setDisplayedValues(myValues);
                picker.setWrapSelectorWheel(false);

                final Spinner spinnerAlturas = (Spinner) popupViewJugadores.findViewById(R.id.alturaJugador);
                ArrayAdapter<String> adapterAltura;
                adapterAltura = new ArrayAdapter<String>(HomeActivity.this,R.layout.spinner_altura, myValues);
                adapterAltura.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerAlturas.setAdapter(
                        new NothingSelectedSpinnerAdapter(
                                adapterAltura,
                                R.layout.spinner_row_nothing_selected_altura,
                                this));

                spinnerAlturas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                AlertDialog.Builder alertDialogBuilderJugadores =
                        new AlertDialog.Builder(HomeActivity.this)
                                .setTitle("Jugador")
                                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // capturar y gaurdadr en bd
                                        final String NOMBRE = (((TextView)popupViewJugadores.findViewById(R.id.nombreJugador)).getText().toString());
                                        final String APELLIDO = (((TextView)popupViewJugadores.findViewById(R.id.apellidoJugador)).getText().toString());
                                        Bitmap FOTO = null;
                                        if(fotoSelected) {
                                            FOTO =((BitmapDrawable) ((ImageView) popupViewJugadores.findViewById(R.id.fotoJugador)).getDrawable()).getBitmap();
                                        }

                                        final String POSICION = spinnerPosicion.getSelectedItem().toString();
                                        final int ALTURA = Integer.parseInt(spinnerAlturas.getSelectedItem().toString());

                                        Jugador j = new Jugador(NOMBRE,APELLIDO,equipos.get(equipoSelected),POSICION,ALTURA,Utils.getByteArrayFromBitmap(FOTO),IS_FALSE);
                                        JugadorRepository.getInstance(HomeActivity.this).addJugador(j);

                                        Toast.makeText(HomeActivity.this, "Jugador  agregado", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                alertDialogBuilderJugadores.setView(popupViewJugadores);
                AlertDialog alertDialogJugadores = alertDialogBuilderJugadores.show();
                //alertDialogJugadores.getWindow().setLayout(1000, 1400);
                break;
            case "Seguimientos":
                Intent intent = new Intent(HomeActivity.this,InicioSeguimientoActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case (INTENT_ELEGIR_IMAGEN):
                    Uri selectedImageUri = data.getData();

                    if(selectedImageUri !=null) {
                        try {
                            escudo.setImageBitmap(decodeUri(selectedImageUri));
                            imageSelected = true;
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case (INTENT_ELEGIR_FOTO):
                    Uri selectedFotoUri = data.getData();

                    if(selectedFotoUri !=null) {
                        try {
                            foto.setImageBitmap(decodeUri(selectedFotoUri));
                            fotoSelected = true;
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

            }
        }
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);
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
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            String email = sessionManager.getEmail();
            Toast.makeText(HomeActivity.this, "Adiós!", Toast.LENGTH_SHORT).show();
            sessionManager.logout();
            finish();
            Intent i = new Intent(HomeActivity.this, Ingresar.class);
            i.putExtra("LOGGEDOUT", true);
            i.putExtra("EMAIL", email);
            startActivity(i);

        }
        return true;

    }

    //pasos del spinnner
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