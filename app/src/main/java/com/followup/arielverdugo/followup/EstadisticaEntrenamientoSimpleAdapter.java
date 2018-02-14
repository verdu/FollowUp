package com.followup.arielverdugo.followup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

/**
 * Created by arielverdugo on 23/1/18.
 */

public class EstadisticaEntrenamientoSimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private EstadisticaDefensivaAdapter.ItemClickListener mClickListener;
    private int number = 0;
    private int numberb = 0;
    public static ArrayList<Integer>tagsEntrenamientoSimple = new ArrayList<>();
    public static int contadorSimple = 1;
    public static ArrayList<View> viewsSimple = new ArrayList<>();


    // data is passed into the constructor
    EstadisticaEntrenamientoSimpleAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        setHasStableIds(true);
    }

    // inflates the cell layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:

                return ViewBuilderHeader(parent);


            case 2:

                return ViewBuilderSubHeader(parent);

            default:return null;
        }

    }

    public EstadisticaEntrenamientoSimpleAdapter.ViewHolder ViewBuilderHeader(ViewGroup parent)
    {
            View view = mInflater.inflate(R.layout.item_recycler_entrenamiento_header_simple, parent, false);
            return new EstadisticaEntrenamientoSimpleAdapter.ViewHolder(view);
    }

    public EstadisticaEntrenamientoSimpleAdapter.ViewHolder2 ViewBuilderSubHeader(ViewGroup parent)
    {
            View view = mInflater.inflate(R.layout.item_recycler_entrenamiento_subheader_simple, parent, false);
            return new EstadisticaEntrenamientoSimpleAdapter.ViewHolder2(view);

    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                EstadisticaEntrenamientoSimpleAdapter.ViewHolder viewHolder0 = (EstadisticaEntrenamientoSimpleAdapter.ViewHolder) holder;
                viewHolder0.myTextView1.setText("Simples");

                break;

            case 2:

                EstadisticaEntrenamientoSimpleAdapter.ViewHolder2 viewHolder = (EstadisticaEntrenamientoSimpleAdapter.ViewHolder2)holder;
                viewHolder.myTextView1.setText("es");
                break;

            }
    }




    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;
        ElegantNumberButton libres;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = (TextView) itemView.findViewById(R.id.testEntrenemientoSimple);
            libres = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberEntrenamientoSimple);
            libres.setTag(contadorSimple);
            viewsSimple.add(libres);
            tagsEntrenamientoSimple.add(contadorSimple);
            contadorSimple++;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }


    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;


        ViewHolder2(View itemView) {
            super(itemView);
            myTextView1 = (TextView) itemView.findViewById(R.id.testSubEntrenemientoSimple);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }


    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(EstadisticaDefensivaAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}

