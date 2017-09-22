package com.followup.arielverdugo.followup;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arielverdugo on 9/9/17.
 */

public class EquipoInfoActivity extends AppCompatActivity {

    private TextView totalEquipos;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mState = 1;




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

        /*
        RecyclerTouchListener r = new RecyclerTouchListener(this,
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well

            }

            @Override
            public void onLongClick(View view,final int position) {

                manageLongClickEvent(view , position);
                //estado del menu, ahora que es 2 como entro en el else muestra el menu
                mState = 2;
                //vuelve a llamar al oncreateOptionsMenu
                invalidateOptionsMenu();

            }
        });

        mRecyclerView.addOnItemTouchListener(r);*/


    }

    private void manageLongClickEvent(View seccion, int posicion) {
        View equipo = seccion.findViewById(R.id.cardViewEquipoInfo);
        equipo.requestFocus();
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
                menu.getItem(i).setVisible(true);
        }

        return true;
    }

}
