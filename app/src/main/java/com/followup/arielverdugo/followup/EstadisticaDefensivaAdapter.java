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

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;


/**
 * Created by arielverdugo on 29/12/17.
 */

public class EstadisticaDefensivaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int number = 0;
    private int numberb = 0;
    public static ElegantNumberButton rebotesDefensivos;
    public static ElegantNumberButton robos;
    public static ElegantNumberButton bloqueos;

    // data is passed into the constructor
    EstadisticaDefensivaAdapter(Context context, String[] data) {
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

    public ViewHolder ViewBuilderHeader(ViewGroup parent)
    {
        switch (number){
            case 0:
                View view = mInflater.inflate(R.layout.item_recycler_defensivos_header_rebotes, parent, false);
                return new ViewHolder(view);

            case 1:
                View view1 = mInflater.inflate(R.layout.item_recycler_defensivos_header_robos, parent, false);
                return new ViewHolder(view1);

            case 2:
                View view2 = mInflater.inflate(R.layout.item_recycler_defensivos_header_bloqueos, parent, false);
                return new ViewHolder(view2);


            default:return null;


        }


    }

    public ViewHolder2 ViewBuilderSubHeader(ViewGroup parent)
    {
        switch (numberb){
            case 0:
                View view = mInflater.inflate(R.layout.item_recycler_defensivos_subheader_rebotes, parent, false);
                return new ViewHolder2(view);

            case 1:
                View view1 = mInflater.inflate(R.layout.item_recycler_defensivos_subheader_robos, parent, false);
                return new ViewHolder2(view1);

            case 2:
                View view2 = mInflater.inflate(R.layout.item_recycler_defensivos_subheader_bloqueos, parent, false);
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
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                switch (position){
                    case 0:
                        ViewHolder viewHolder0 = (ViewHolder) holder;
                        viewHolder0.myTextView1.setText("Rebotes Defensivos");
                        number++;
                        break;
                    case 2:

                        ViewHolder viewHolder1 = (ViewHolder) holder;
                        viewHolder1.myTextView2.setText("Robos");
                        number++;
                        break;
                    case 4:
                        ViewHolder viewHolder2 = (ViewHolder) holder;
                        viewHolder2.myTextView3.setText("Bloqueos");
                        number++;
                        break;

                }
                break;

            case 2:
                switch (position){
                    case 1:
                        ViewHolder2 viewHolder0 = (ViewHolder2)holder;
                        viewHolder0.myTextView1.setText("es");
                        numberb++;
                        break;
                    case 3:
                        ViewHolder2 viewHolder1 = (ViewHolder2)holder;
                        viewHolder1.myTextView2.setText("es");
                        numberb++;
                        break;
                    case 5:
                        ViewHolder2 viewHolder2 = (ViewHolder2)holder;
                        viewHolder2.myTextView3.setText("es");
                        numberb++;
                        break;

                }
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
        TextView myTextView2;
        TextView myTextView3;


        ViewHolder(View itemView) {
            super(itemView);
            //myTextView = (TextView) itemView.findViewById(R.id.test);
            //itemView.setOnClickListener(this);
            switch (number){
                case 0:
                    /*NumberPicker numberPicker = (NumberPicker) itemView.findViewById(R.id.number_picker);
                    numberPicker.setDividerColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    numberPicker.setDividerColorResource(R.color.colorPrimary);*/
                    myTextView1 = (TextView) itemView.findViewById(R.id.testRebotesDefensivos);
                    rebotesDefensivos = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberRebotesDefensivos);
                    itemView.setOnClickListener(this);
                    break;
                case 1:
                    myTextView2 = (TextView) itemView.findViewById(R.id.testRobos);
                    robos = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberRobos);
                    itemView.setOnClickListener(this);

                case 2:
                    myTextView3 = (TextView) itemView.findViewById(R.id.testBloqueos);
                    bloqueos = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberBloqueos);
                    itemView.setOnClickListener(this);
                    break;


            }

        }

        private void setDividerColor(NumberPicker picker, int color) {

            java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (java.lang.reflect.Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        ColorDrawable colorDrawable = new ColorDrawable(color);
                        pf.set(picker, colorDrawable);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;
        TextView myTextView2;
        TextView myTextView3;


        ViewHolder2(View itemView) {
            super(itemView);
            switch (numberb){
                case 0:
                    myTextView1 = (TextView) itemView.findViewById(R.id.testSubRebotesDefensivos);
                    itemView.setOnClickListener(this);
                    break;
                case 1:
                    myTextView2 = (TextView) itemView.findViewById(R.id.testSubRobos);
                    itemView.setOnClickListener(this);
                    break;
                case 2:
                    myTextView3 = (TextView) itemView.findViewById(R.id.testSubBloqueos);
                    itemView.setOnClickListener(this);
                    break;


            }

            /*myTextView4 = (TextView) itemView.findViewById(R.id.test);
            itemView.setOnClickListener(this);*/
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
