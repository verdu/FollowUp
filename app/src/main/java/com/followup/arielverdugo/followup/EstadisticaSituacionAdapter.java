package com.followup.arielverdugo.followup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by arielverdugo on 29/12/17.
 */

public class EstadisticaSituacionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int number = 0;
    private int numberb = 0;

    // data is passed into the constructor
    EstadisticaSituacionAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        setHasStableIds(true);
    }

    // inflates the cell layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case 0:
                View view = mInflater.inflate(R.layout.item_recycler_situacion_header_entrenamiento, parent, false);
                return new ViewHolder(view);

            case 1:
                View view1 = mInflater.inflate(R.layout.item_recycler_situacion_header_partido, parent, false);
                return new ViewHolder(view1);

            default:return null;

        }

    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;
        TextView myTextView2;

        ViewHolder(View itemView) {
            super(itemView);

            switch (number){
                case 0:
                    /*NumberPicker numberPicker = (NumberPicker) itemView.findViewById(R.id.number_picker);
                    numberPicker.setDividerColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    numberPicker.setDividerColorResource(R.color.colorPrimary);*/
                    //myTextView1 = (TextView) itemView.findViewById(R.id.testEntrenamiento);
                    //itemView.setOnClickListener(this);
                    break;
                case 1:
                    myTextView2 = (TextView) itemView.findViewById(R.id.testPartido);
                    itemView.setOnClickListener(this);
            }

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
        if(position%2 == 0) {
            return 0;
        }
        else{
            return 1;
        }
    }


    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
