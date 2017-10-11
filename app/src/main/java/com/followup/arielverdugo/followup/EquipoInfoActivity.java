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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.followup.arielverdugo.followup.SeccionAdapterEquipoInfo.SeccionEquipoInfoViewHolder.posiciones;
import static com.followup.arielverdugo.followup.SeccionAdapterEquipoInfo.SeccionEquipoInfoViewHolder.selectedItems;

/**
 * Created by arielverdugo on 9/9/17.
 */

public class EquipoInfoActivity extends AppCompatActivity {

    private TextView totalEquipos;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mState = 1;

    private RecyclerView.Adapter lastmAdapter;

    private ImageView escudo;
    public static final int INTENT_ELEGIR_IMAGEN = 1;
    private Boolean imageSelected = false;


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

                //@Override
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




    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipoinfo);

        List<Equipo> equipos = EquipoRepository.getInstance(this).getEquipos();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerEquipo);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        
        mAdapter = new SeccionAdapterEquipoInfo(equipos);
        mRecyclerView.setAdapter(mAdapter);


        RecyclerTouchListener r = new RecyclerTouchListener(this,
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well

            }

            @Override
            public void onLongClick(View view,final int position) {
                //estado del menu, ahora que es 2 como entro en el else muestra el menu
                mState = checkIfSelectedPositions();
                //vuelve a llamar al oncreateOptionsMenu
                invalidateOptionsMenu();


            }
        });

        mRecyclerView.addOnItemTouchListener(r);
    }

    private Integer checkIfSelectedPositions()
    {
        System.out.println("checkIfSelectedPositions selectedItems size: " + selectedItems.size());
        return selectedItems.size() > 0 ? 2 : 1;
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.equipo_info_menu, menu);

        if (mState == 1)
        {
            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setVisible(false);
        }
        else{
            for (int i = 0; i < menu.size(); i++)
            {
                menu.getItem(i).setVisible(true);
            }
        }

        return true;
    }

    //como posciones es global si voy para atras quiero que vuelva a ser 0
    public void onBackPressed()
    {
        //moveTaskToBack(true);
        finish();
        posiciones.clear();
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        List<Equipo> equipos = EquipoRepository.getInstance(this).getEquipos();

        switch (item.getItemId()) {
            case R.id.eliminar:
                for(int i = selectedItems.size() - 1; i >= 0;i--) {
                    int idEquipoAEliminar = equipos.get(selectedItems.keyAt(i)).getId();
                    EquipoRepository.getInstance(EquipoInfoActivity.this).deleteEquipoById(idEquipoAEliminar);
                }
                selectedItems.clear();

                List<Equipo> equiposDefinitivos = EquipoRepository.getInstance(this).getEquipos();
                lastmAdapter = new SeccionAdapterEquipoInfo(equiposDefinitivos);
                mRecyclerView.setAdapter(lastmAdapter);
                //cambio el estado para que desaparezca el menu
                mState = checkIfSelectedPositions();
                invalidateOptionsMenu();
                return true;

            case R.id.editar:

                final ViewGroup popupView = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_editar_equipo, null);


                    //traer los valores a editar y listo
                    final List<Equipo> equiposEditar = EquipoRepository.getInstance(this).getEquipos();;
                    //la hice final para poder acceder a la posciion a editar, para luego conseguir el id
                    final int posicionEditar = selectedItems.keyAt(0);

                    String nombreAnterior = equiposEditar.get(posicionEditar).getNombre();
                    String apodoAnterior =  equiposEditar.get(posicionEditar).getApodo();
                    String barrioAnterior = equiposEditar.get(posicionEditar).getBarrio();
                    String direccionAnterior = equiposEditar.get(posicionEditar).getDireccion();
                    int id = equiposEditar.get(posicionEditar).getId();
                    byte[] escudoAnterior = equiposEditar.get(posicionEditar).getEscudo();

                    EditText nombre = (EditText) popupView.findViewById(R.id.editarNombreNuevoEquipo);
                    nombre.setText(nombreAnterior);
                    EditText apodo = (EditText) popupView.findViewById(R.id.editarAlias);
                    apodo.setText(apodoAnterior);
                    EditText direccion = (EditText) popupView.findViewById(R.id.editarDireccion);
                    direccion.setText(direccionAnterior);
                    EditText barrio = (EditText) popupView.findViewById(R.id.editarBarrio);
                    barrio.setText(barrioAnterior);

                    if (escudoAnterior == null)
                    {
                        escudo = (ImageView) popupView.findViewById(R.id.editarEscudo);
                        escudo.setOnClickListener(new View.OnClickListener() {
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
                        escudo = (ImageView) popupView.findViewById(R.id.editarEscudo);

                        Bitmap bitmap = BitmapFactory.decodeByteArray(escudoAnterior, 0, escudoAnterior.length);
                        escudo.setImageBitmap(bitmap);

                        escudo.setOnClickListener(new View.OnClickListener() {
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

                    AlertDialog.Builder alertDialogBuilder =
                            new AlertDialog.Builder(EquipoInfoActivity.this)
                                    .setTitle("Editar Equipo")
                                    .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // capturar y gaurdadr en bd
                                            final String NOMBRE = (((TextView) popupView.findViewById(R.id.editarNombreNuevoEquipo)).getText().toString());
                                            final String ALIAS = (((TextView) popupView.findViewById(R.id.editarAlias)).getText().toString());
                                            final String BARRIO = (((TextView) popupView.findViewById(R.id.editarBarrio)).getText().toString());
                                            final String DIRECCION = (((TextView) popupView.findViewById(R.id.editarDireccion)).getText().toString());
                                            Bitmap ESCUDO = null;
                                            ESCUDO = ((BitmapDrawable) ((ImageView) popupView.findViewById(R.id.editarEscudo)).getDrawable()).getBitmap();

                                            equiposEditar.get(posicionEditar).setNombre(NOMBRE);
                                            equiposEditar.get(posicionEditar).setApodo(ALIAS);
                                            equiposEditar.get(posicionEditar).setBarrio(BARRIO);
                                            equiposEditar.get(posicionEditar).setDireccion(DIRECCION);
                                            equiposEditar.get(posicionEditar).setEscudo(Utils.getByteArrayFromBitmap(ESCUDO));

                                            EquipoRepository.getInstance(EquipoInfoActivity.this).updateEquipo(equiposEditar.get(posicionEditar));
                                            Toast.makeText(EquipoInfoActivity.this, "Equipo editado", Toast.LENGTH_SHORT).show();
                                            equiposEditar.clear();
                                            selectedItems.clear();
                                            //se refresca el cardview
                                            List<Equipo> equiposEditados = EquipoRepository.getInstance(EquipoInfoActivity.this).getEquipos();
                                            lastmAdapter = new SeccionAdapterEquipoInfo(equiposEditados);
                                            mRecyclerView.setAdapter(lastmAdapter);
                                            dialog.dismiss();
                                            mState = 1;
                                            invalidateOptionsMenu();

                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                    alertDialogBuilder.setView(popupView);
                    AlertDialog alertDialog = alertDialogBuilder.show();


                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case INTENT_ELEGIR_IMAGEN:
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



}
