package com.followup.arielverdugo.followup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

/**
 * Created by arielverdugo on 23/1/18.
 */

public class EstadisticaEntrenamientoTripleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private EstadisticaDefensivaAdapter.ItemClickListener mClickListener;
    public static ArrayList<Integer> tagsEntrenamientoTriple = new ArrayList<>();
    public static int contadorTriple = 1;
    public static ArrayList<View> viewsTriple = new ArrayList<>();

    // data is passed into the constructor
    EstadisticaEntrenamientoTripleAdapter(Context context, String[] data) {
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

    public EstadisticaEntrenamientoTripleAdapter.ViewHolder ViewBuilderHeader(ViewGroup parent)
    {
        View view = mInflater.inflate(R.layout.item_recycler_entrenamiento_header_triple, parent, false);
        return new EstadisticaEntrenamientoTripleAdapter.ViewHolder(view);
    }

    public EstadisticaEntrenamientoTripleAdapter.ViewHolder2 ViewBuilderSubHeader(ViewGroup parent)
    {
        View view = mInflater.inflate(R.layout.item_recycler_entrenamiento_subheader_triple, parent, false);
        return new EstadisticaEntrenamientoTripleAdapter.ViewHolder2(view);

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
                EstadisticaEntrenamientoTripleAdapter.ViewHolder viewHolder0 = (EstadisticaEntrenamientoTripleAdapter.ViewHolder) holder;
                viewHolder0.myTextView1.setText("Triples");
                break;

            case 2:

                EstadisticaEntrenamientoTripleAdapter.ViewHolder2 viewHolder = (EstadisticaEntrenamientoTripleAdapter.ViewHolder2)holder;
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
        ElegantNumberButton triples;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = (TextView) itemView.findViewById(R.id.testEntrenemientoTriple);
            triples = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberEntrenamientoSimple);
            triples.setTag(contadorTriple);
            viewsTriple.add(triples);
            tagsEntrenamientoTriple.add(contadorTriple);
            contadorTriple++;
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
            myTextView1 = (TextView) itemView.findViewById(R.id.testSubEntrenamientoTriple);
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


