package com.followup.arielverdugo.followup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 27/11/17.
 */

public class EditarJugadorActivity extends AppCompatActivity{


    private EditText nombre;
    private EditText apellido;
    private ImageView fotoJugador;
    private byte[] fotoAnterior;
    public static final int INTENT_ELEGIR_IMAGEN = 1;
    private Button editar;
    private Boolean imageSelected = false;
    public AhoyOnboarderActivity proonga;
    static Boolean editado = false;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_jugador);
        final Bundle extras = getIntent().getExtras();
        int idJugador = extras.getInt("id");
        final Jugador jugadorEditar = JugadorRepository.getInstance(EditarJugadorActivity.this).findJugadorById(idJugador);

        nombre = (EditText) findViewById(R.id.editarNombreJugador);
        nombre.setText(jugadorEditar.getNombre().toString());
        apellido = (EditText) findViewById(R.id.editarApellidoJugador);
        apellido.setText(jugadorEditar.getApellido().toString());
        fotoAnterior = jugadorEditar.getFoto();
        editar = (Button) findViewById(R.id.aceptarEdicion);


        if (fotoAnterior == null)
        {
            fotoJugador = (ImageView)findViewById(R.id.editarFotoJugador);
            fotoJugador.setImageResource(R.drawable.anadirimagen);
            fotoJugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //para ir a la galeria
                    Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    getIntent.setType("image/*");

                    Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickIntent.setType("image/*");

                    Intent chooserIntent = Intent.createChooser(getIntent, "Elegir imagen");
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                    //utiliza la constante INTENT_ELEGIR_IMAGEN en onActivityResult
                    startActivityForResult(chooserIntent, INTENT_ELEGIR_IMAGEN);
                }
            });

        }
        else
        {
            fotoJugador = (ImageView) findViewById(R.id.editarFotoJugador);

            Bitmap bitmap = BitmapFactory.decodeByteArray(fotoAnterior, 0, fotoAnterior.length);
            fotoJugador.setImageBitmap(bitmap);

            fotoJugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //para ir a la galeria
                    Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    getIntent.setType("image/*");

                    Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickIntent.setType("image/*");

                    Intent chooserIntent = Intent.createChooser(getIntent, "Elegir imagen");
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                    //utiliza la constante INTENT_ELEGIR_IMAGEN en onActivityResult
                    startActivityForResult(chooserIntent, INTENT_ELEGIR_IMAGEN);
                }
            });
        }



        //se crea y llena el spiner de equipos
        List<Equipo> equipos = EquipoRepository.getInstance(EditarJugadorActivity.this).getEquipos();
        ArrayList<String> equiposNombre = new ArrayList<>();
        for (int i = 0; i < equipos.size(); i++)
        {
            equiposNombre.add(equipos.get(i).getNombre());
        }

        final Spinner spinnerEquipos = (Spinner) findViewById(R.id.editarEquipoJugador);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(EditarJugadorActivity.this,R.layout.spinner_equipos, equiposNombre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

        final Spinner spinnerPosiciones = (Spinner)findViewById(R.id.editarPosicionJugador);
        ArrayAdapter<String>adapterPosiciones;
        adapterPosiciones = new ArrayAdapter<String>(EditarJugadorActivity.this,R.layout.spinner_posicion, posicionesJugadores);
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
        NumberPicker picker = new NumberPicker(EditarJugadorActivity.this);
        picker.setDisplayedValues(myValues);
        picker.setWrapSelectorWheel(false);

        final Spinner spinnerAlturas = (Spinner) findViewById(R.id.editarAlturaJugador);
        ArrayAdapter<String> adapterAltura;
        adapterAltura = new ArrayAdapter<String>(EditarJugadorActivity.this,R.layout.spinner_altura, myValues);
        adapterAltura.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlturas.setAdapter(adapterAltura);
        int alturaJugador = jugadorEditar.getAltura();
        String compareValueAltura = Integer.toString(alturaJugador);

        if (!compareValueAltura.equals(null)) {
            int spinnerPosition = adapterAltura.getPosition(compareValueAltura);
            spinnerAlturas.setSelection(spinnerPosition);
        }

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String NOMBRE = (((TextView) findViewById(R.id.editarNombreJugador)).getText().toString());
                final String APELLIDO = (((TextView) findViewById(R.id.editarApellidoJugador)).getText().toString());
                Bitmap FOTO = null;
                FOTO = ((BitmapDrawable) ((ImageView) findViewById(R.id.editarFotoJugador)).getDrawable()).getBitmap();
                final int ALTURA =  Integer.parseInt(spinnerAlturas.getSelectedItem().toString());
                final String POSICION = spinnerPosiciones.getSelectedItem().toString();
                final String EQUIPO = spinnerEquipos.getSelectedItem().toString();

                List <Equipo> eq= EquipoRepository.getInstance(EditarJugadorActivity.this).findEquipoByName(EQUIPO);
                Equipo e = eq.get(0);

                jugadorEditar.setNombre(NOMBRE);
                jugadorEditar.setApellido(APELLIDO);
                jugadorEditar.setFoto(Utils.getByteArrayFromBitmap(FOTO));
                jugadorEditar.setAltura(ALTURA);
                jugadorEditar.setPosicion(POSICION);
                jugadorEditar.setEquipo(e);

                JugadorRepository.getInstance(EditarJugadorActivity.this).updateJugador(jugadorEditar);
                //el editado true es para poder actualizar el cardview desdepues de la finalizacion de este intento
                editado = true;
                Toast.makeText(EditarJugadorActivity.this, "Jugador editado", Toast.LENGTH_SHORT).show();
                Intent intento = new Intent(EditarJugadorActivity.this,JugadorEquipoInfoActivity.class);
                startActivity(intento);

            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case (INTENT_ELEGIR_IMAGEN):
                    Uri selectedImageUri = data.getData();

                    if(selectedImageUri !=null) {
                        try {
                            fotoJugador.setImageBitmap(decodeUri(selectedImageUri));
                            imageSelected = true;
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
