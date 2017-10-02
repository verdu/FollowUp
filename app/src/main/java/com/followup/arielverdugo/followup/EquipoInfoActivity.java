package com.followup.arielverdugo.followup;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.Z;
import static com.followup.arielverdugo.followup.SeccionAdapterEquipoInfo.SeccionEquipoInfoViewHolder.posiciones;

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
                mState = 2;
                //vuelve a llamar al oncreateOptionsMenu
                invalidateOptionsMenu();


            }
        });

        mRecyclerView.addOnItemTouchListener(r);
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


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        int id = 0;

        List<Equipo> equipos = EquipoRepository.getInstance(this).getEquipos();

        int posicionaBorrar = 0;
        switch (item.getItemId()) {
            case R.id.eliminar:
                List posicionesEliminar = new ArrayList();

                for (int i = 0; i < posiciones.size() ;i++){
                    posicionesEliminar.add(posiciones.get(i));
                }
                //el problema es q el posiciones es global
                for(int i = posicionesEliminar.size() - 1; i >= 0;i--) {

                    posicionaBorrar = (Integer)posicionesEliminar.get(i);
                    id = equipos.get(posicionaBorrar).getId();
                    posiciones.remove(i);
                    EquipoRepository.getInstance(EquipoInfoActivity.this).deleteEquipoById(id);
                }
                List<Equipo> equiposDefinitivos = EquipoRepository.getInstance(this).getEquipos();
                lastmAdapter = new SeccionAdapterEquipoInfo(equiposDefinitivos);
                mRecyclerView.setAdapter(lastmAdapter);
                //cambio el estado para que desaparezca el menu
                mState = 1;
                invalidateOptionsMenu();

                return true;

            case R.id.editar:
                Toast.makeText(EquipoInfoActivity.this,"Equipo editado",Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


}
