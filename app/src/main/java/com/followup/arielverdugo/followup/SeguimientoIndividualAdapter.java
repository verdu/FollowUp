package com.followup.arielverdugo.followup;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.followup.arielverdugo.followup.interfaces.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 19/2/18.
 */

public class SeguimientoIndividualAdapter extends RecyclerView.Adapter<SeguimientoIndividualAdapter.SeguimientoIndividualViewHolder> {

    private static RecyclerViewClickListener itemListener;


    public static class SeguimientoIndividualViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        // each data item is just a string in this case
        public ImageView imagenJugador;
        public TextView nombreJugador;

        public CardView cv;


        static List<Integer> posiciones = new ArrayList<>();

        public SeguimientoIndividualViewHolder(View v) {
            super(v);

            imagenJugador = (ImageView) v.findViewById(R.id.fotoJugadorSeguimientoIndividual);
            //nombreJugador = (TextView) v.findViewById(R.id.n);

            cv = (CardView) v.findViewById(R.id.cardSeguimientoIndividual);
            cv.setClickable(true);
            cv.setOnLongClickListener(this);

            cv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }


        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SeguimientoIndividualAdapter() {

    }

    // Create new views (invoked by the layout manager)
    @Override
    public SeguimientoIndividualAdapter.SeguimientoIndividualViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_seguimiento_individual, parent, false);

        return new SeguimientoIndividualViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SeguimientoIndividualViewHolder viewHolder, int i) {


        viewHolder.imagenJugador.setImageResource(R.drawable.hardenmaster);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 1;
    }




}
