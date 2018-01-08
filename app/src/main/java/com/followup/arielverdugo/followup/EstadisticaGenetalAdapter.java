package com.followup.arielverdugo.followup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by arielverdugo on 29/12/17.
 */

public class EstadisticaGenetalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    EstadisticaGenetalAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:
                View view = mInflater.inflate(R.layout.item_recycler_generales_header, parent, false);
                return new ViewHolder(view);

            case 2:
                View view2 = mInflater.inflate(R.layout.item_recycler_generales_subheader, parent, false);
                //view2.setPadding(100,0,0,0);
                return new ViewHolder2(view2);

            default:return null;
        }

    }

    // binds the data to the textview in each cell
    /*@Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData[position];
        holder.myTextView.setText(animal);
    }*/


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder viewHolder0 = (ViewHolder) holder;

                break;

            case 2:
                ViewHolder2 viewHolder2 = (ViewHolder2)holder;

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
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.nameTxt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder2(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.nameTxt);
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
     void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
