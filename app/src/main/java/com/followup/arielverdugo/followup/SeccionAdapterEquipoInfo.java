package com.followup.arielverdugo.followup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.followup.arielverdugo.followup.interfaces.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 4/9/17.
 */

public class SeccionAdapterEquipoInfo extends RecyclerView.Adapter<SeccionAdapterEquipoInfo.SeccionEquipoInfoViewHolder> {
    private List<Equipo> equipos;
    private static RecyclerViewClickListener itemListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class SeccionEquipoInfoViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        // each data item is just a string in this case
        public ImageView imagenEquipo;
        public TextView nombreEquipo;
        public TextView apodoEquipo;
        public TextView barrioEquipo;
        public TextView direccionEquipo;
        public CardView cv;

        //cambie de private a static para acceder a sus valores
        static SparseBooleanArray selectedItems = new SparseBooleanArray();

        static List<Integer> posiciones = new ArrayList<Integer>();

        public SeccionEquipoInfoViewHolder(View v) {
            super(v);

            imagenEquipo = (ImageView) v.findViewById(R.id.imagenEquipoInfo);
            nombreEquipo = (TextView) v.findViewById(R.id.nombreEquipoInfo);
            apodoEquipo = (TextView) v.findViewById(R.id.apodoEquipoInfo);
            barrioEquipo = (TextView) v.findViewById(R.id.barrioEquipoInfo);
            direccionEquipo = (TextView) v.findViewById(R.id.direccionEquipoInfo);
            cv = (CardView) v.findViewById(R.id.cardViewEquipoInfo);
            cv.setClickable(true);
            cv.setOnLongClickListener(this);

            cv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (selectedItems.get(getAdapterPosition(), false)) {
                        selectedItems.delete(getAdapterPosition());
                        v.setSelected(false);
                    }
                    else {
                        selectedItems.put(getAdapterPosition(), true);
                        v.setSelected(true);

                    }
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
    public SeccionAdapterEquipoInfo(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SeccionAdapterEquipoInfo.SeccionEquipoInfoViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_equipoinfo, parent, false);

        return new SeccionEquipoInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SeccionEquipoInfoViewHolder viewHolder, int i) {

        if (equipos.get(i).getEscudo() != null) {
            Bitmap imagenEquipo = BitmapFactory.decodeByteArray(equipos.get(i).getEscudo(), 0, equipos.get(i).getEscudo().length);
            viewHolder.imagenEquipo.setImageBitmap(imagenEquipo);

        } else {
            viewHolder.imagenEquipo.setImageResource(R.drawable.sinimagen);
        }
        viewHolder.nombreEquipo.setText(equipos.get(i).getNombre());
        viewHolder.apodoEquipo.setText(equipos.get(i).getApodo());
        viewHolder.barrioEquipo.setText(equipos.get(i).getBarrio());
        viewHolder.direccionEquipo.setText(equipos.get(i).getDireccion());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return equipos.size();
    }



}