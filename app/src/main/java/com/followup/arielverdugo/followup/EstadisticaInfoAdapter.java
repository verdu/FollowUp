package com.followup.arielverdugo.followup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arielverdugo on 22/1/18.
 */

class EstadisticaInfoAdapter extends RecyclerView.Adapter<EstadisticaInfoAdapter.EstadisticaInfoViewHolder> {
    private List<Seccion> secciones;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class EstadisticaInfoViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imagen;
        public TextView nombre;
        public EstadisticaInfoViewHolder(View v) {
            super(v);
            //imagen = (ImageView) v.findViewById(R.id.imagenSeccion);
            //nombre = (TextView) v.findViewById(R.id.textoSeccion);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public EstadisticaInfoAdapter.EstadisticaInfoViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new EstadisticaInfoAdapter.EstadisticaInfoViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EstadisticaInfoAdapter.EstadisticaInfoViewHolder viewHolder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //viewHolder.imagen.setImageResource(secciones.get(i).getImagen());
        //viewHolder.nombre.setText(secciones.get(i).getTexto());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 1;
    }
}
