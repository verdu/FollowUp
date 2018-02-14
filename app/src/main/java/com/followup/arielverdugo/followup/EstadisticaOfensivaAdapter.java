package com.followup.arielverdugo.followup;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class EstadisticaOfensivaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int number = 0;
    private int numberb = 0;
    private String dato = "";
    public static ElegantNumberButton golesCampoHechos;
    public static ElegantNumberButton golesCampoIntentados;
    public static ElegantNumberButton porcentajeGolesCampo;
    public static ElegantNumberButton triplesHechos;
    public static ElegantNumberButton triplesIntentados;
    public static ElegantNumberButton porcentajeTriples;
    public static ElegantNumberButton libresHechos;
    public static ElegantNumberButton libresIntentados;
    public static ElegantNumberButton porcentajeLibres;
    public static ElegantNumberButton rebotesOfensivos;


    // data is passed into the constructor
    EstadisticaOfensivaAdapter(Context context, String[] data) {
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
                View view = mInflater.inflate(R.layout.item_recycler_ofensivas_header_golescampo_hechos, parent, false);
                return new ViewHolder(view);

            case 1:
                View view1 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_golescampo_intentados, parent, false);
                return new ViewHolder(view1);

            case 2:
                View view2 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_golescampo_porcentaje, parent, false);
                return new ViewHolder(view2);

            case 3:
                View view3 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_triples_hechos, parent, false);
                return new ViewHolder(view3);

            case 4:
                View view4 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_triples_intentados, parent, false);
                return new ViewHolder(view4);

            case 5:
                View view5 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_triples_porcentaje, parent, false);
                return new ViewHolder(view5);

            case 6:
                View view6 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_simples_hechos, parent, false);
                return new ViewHolder(view6);

            case 7:
                View view7 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_simples_intentados, parent, false);
                return new ViewHolder(view7);

            case 8:
                View view8 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_simples_porcentaje, parent, false);
                return new ViewHolder(view8);

            case 9:
                View view9 = mInflater.inflate(R.layout.item_recycler_ofensivas_header_rebote_ofensivo, parent, false);
                return new ViewHolder(view9);

            default:return null;


        }


    }

    public ViewHolder2 ViewBuilderSubHeader(ViewGroup parent)
    {
        switch (numberb){
            case 0:
                View view = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_golescampo_hechos, parent, false);
                return new ViewHolder2(view);

            case 1:
                View view1 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_golescampo_intentados, parent, false);
                return new ViewHolder2(view1);

            case 2:
                View view2 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_golescampo_porcentaje, parent, false);
                return new ViewHolder2(view2);

            case 3:
                View view3 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_triples_hechos, parent, false);
                return new ViewHolder2(view3);

            case 4:
                View view4 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_triples_intentados, parent, false);
                return new ViewHolder2(view4);

            case 5:
                View view5 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_triples_porcentaje, parent, false);
                return new ViewHolder2(view5);

            case 6:
                View view6 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_simples_hechos, parent, false);
                return new ViewHolder2(view6);

            case 7:
                View view7 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_simples_intentados, parent, false);
                return new ViewHolder2(view7);

            case 8:
                View view8 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_simples_porcentaje, parent, false);
                return new ViewHolder2(view8);

            case 9:
                View view9 = mInflater.inflate(R.layout.item_recycler_ofensivas_subheader_rebote_ofensivo, parent, false);
                return new ViewHolder2(view9);

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
                        viewHolder0.myTextView1.setText("Goles de campo hechos");
                        number++;
                        break;
                    case 2:
                        ViewHolder viewHolder1 = (ViewHolder) holder;
                        viewHolder1.myTextView2.setText("Goles de campo Intentados");
                        number++;
                        break;
                    case 4:
                        ViewHolder viewHolder2 = (ViewHolder) holder;
                        viewHolder2.myTextView3.setText("Porcentaje de goles de campo");
                        number++;
                        break;
                    case 6:
                        ViewHolder viewHolder3 = (ViewHolder) holder;
                        viewHolder3.myTextView4.setText("Triple hecho");
                        number++;
                        break;
                    case 8:
                        ViewHolder viewHolder4 = (ViewHolder) holder;
                        viewHolder4.myTextView5.setText("Triple intentado");
                        number++;
                        break;
                    case 10:
                        ViewHolder viewHolder5 = (ViewHolder) holder;
                        viewHolder5.myTextView6.setText("Porcentaje de Triples");
                        number++;
                        break;
                    case 12:
                        ViewHolder viewHolder6 = (ViewHolder) holder;
                        viewHolder6.myTextView7.setText("Tiro libre hecho");
                        number++;
                        break;
                    case 14:
                        ViewHolder viewHolder7 = (ViewHolder) holder;
                        viewHolder7.myTextView8.setText("Tiro libre intentado");
                        number++;
                        break;
                    case 16:
                        ViewHolder viewHolder8 = (ViewHolder) holder;
                        viewHolder8.myTextView9.setText("Porcentaje de Tiro libre");
                        number++;
                        break;
                    case 18:
                        ViewHolder viewHolder9 = (ViewHolder) holder;
                        viewHolder9.myTextView10.setText("Rebotes Ofensivos");
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
                    case 7:
                        ViewHolder2 viewHolder3 = (ViewHolder2)holder;
                        viewHolder3.myTextView4.setText("es");
                        numberb++;
                        break;
                    case 9:
                        ViewHolder2 viewHolder4 = (ViewHolder2)holder;
                        viewHolder4.myTextView5.setText("es");
                        numberb++;
                        break;
                    case 11:
                        ViewHolder2 viewHolder5 = (ViewHolder2)holder;
                        viewHolder5.myTextView6.setText("es");
                        numberb++;
                        break;
                    case 13:
                        ViewHolder2 viewHolder6 = (ViewHolder2)holder;
                        viewHolder6.myTextView7.setText("es");
                        numberb++;
                        break;
                    case 15:
                        ViewHolder2 viewHolder7 = (ViewHolder2)holder;
                        viewHolder7.myTextView8.setText("es");
                        numberb++;
                        break;
                    case 17:
                        ViewHolder2 viewHolder8 = (ViewHolder2)holder;
                        viewHolder8.myTextView9.setText("es");
                        numberb++;
                        break;
                    case 19:
                        ViewHolder2 viewHolder9 = (ViewHolder2)holder;
                        viewHolder9.myTextView10.setText("es");
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
        TextView myTextView4;
        TextView myTextView5;
        TextView myTextView6;
        TextView myTextView7;
        TextView myTextView8;
        TextView myTextView9;
        TextView myTextView10;


        ViewHolder(View itemView) {
            super(itemView);
            //myTextView = (TextView) itemView.findViewById(R.id.test);
            //itemView.setOnClickListener(this);
            switch (number){
                case 0:
                    /*NumberPicker numberPicker = (NumberPicker) itemView.findViewById(R.id.number_picker);
                    numberPicker.setDividerColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    numberPicker.setDividerColorResource(R.color.colorPrimary);*/
                    myTextView1 = (TextView) itemView.findViewById(R.id.testGolesCampoHechos);
                    golesCampoHechos = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberGolesCampoHechos);
                    itemView.setOnClickListener(this);
                    break;
                case 1:
                    myTextView2 = (TextView) itemView.findViewById(R.id.testGolesCampoIntentados);
                    golesCampoIntentados = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberGolesCampoIntentados);
                    itemView.setOnClickListener(this);

                case 2:
                    myTextView3 = (TextView) itemView.findViewById(R.id.testPorcentajeGolesCampo);
                    porcentajeGolesCampo = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberPorcentajeGolesCampo);
                    itemView.setOnClickListener(this);
                    break;
                case 3:
                    myTextView4 = (TextView) itemView.findViewById(R.id.testTriplesHechos);
                    triplesHechos = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberTriplesHechos);
                    itemView.setOnClickListener(this);
                    break;
                case 4:
                    myTextView5 = (TextView) itemView.findViewById(R.id.testTriplesIntentados);
                    triplesIntentados = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberTriplesIntentados);
                    itemView.setOnClickListener(this);
                    break;
                case 5:
                    myTextView6 = (TextView) itemView.findViewById(R.id.testPorcentajeTriples);
                    porcentajeTriples = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberPorcentajeTriples);
                    itemView.setOnClickListener(this);
                    break;
                case 6:
                    myTextView7 = (TextView) itemView.findViewById(R.id.testTiroLibreHecho);
                    libresHechos = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberTiroLibreHecho);
                    itemView.setOnClickListener(this);
                    break;
                case 7:
                    myTextView8 = (TextView) itemView.findViewById(R.id.testTiroLibreIntentado);
                    libresIntentados = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberTiroLibreIntentado);
                    itemView.setOnClickListener(this);
                    break;
                case 8:
                    myTextView9 = (TextView) itemView.findViewById(R.id.testProcentajeTiroLibre);
                    porcentajeLibres = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberPorcentajeTiroLibre);
                    itemView.setOnClickListener(this);
                    break;
                case 9:
                    myTextView10 = (TextView) itemView.findViewById(R.id.testReboteOfensivo);
                    rebotesOfensivos = (ElegantNumberButton) itemView.findViewById(R.id.elegantNumberRebotesOfensivos);
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

        public  void  getResultado()
        {
            //puedo hacer las variables estaticas para accederlas de la otra clase
            /*dato = ViewHolder.this.golesCampoHechos.getNumber();
            Context contexto = ViewHolder.this.golesCampoHechos.getContext();
            Intent intent = new Intent("custom-message");
            intent.putExtra("golesCampoHechos",dato);
            LocalBroadcastManager.getInstance(contexto).sendBroadcast(intent);*/
        }
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;
        TextView myTextView2;
        TextView myTextView3;
        TextView myTextView4;
        TextView myTextView5;
        TextView myTextView6;
        TextView myTextView7;
        TextView myTextView8;
        TextView myTextView9;
        TextView myTextView10;

        ViewHolder2(View itemView) {
            super(itemView);
            switch (numberb){
                case 0:
                    myTextView1 = (TextView) itemView.findViewById(R.id.testSubGolesCampoHechos);
                    itemView.setOnClickListener(this);
                    break;
                case 1:
                    myTextView2 = (TextView) itemView.findViewById(R.id.testSubGolesCampoIntentados);
                    itemView.setOnClickListener(this);
                    break;
                case 2:
                    myTextView3 = (TextView) itemView.findViewById(R.id.testSubPorcentajeGolesCampo);
                    itemView.setOnClickListener(this);
                    break;
                case 3:
                    myTextView4 = (TextView) itemView.findViewById(R.id.testSubTrilpleHechos);
                    itemView.setOnClickListener(this);
                    break;
                case 4:
                    myTextView5 = (TextView) itemView.findViewById(R.id.testSubTtripleIntentados);
                    itemView.setOnClickListener(this);
                    break;
                case 5:
                    myTextView6 = (TextView) itemView.findViewById(R.id.testSubPorcentajeTriple);
                    itemView.setOnClickListener(this);
                    break;
                case 6:
                    myTextView7 = (TextView) itemView.findViewById(R.id.testSubTiroLibreHecho);
                    itemView.setOnClickListener(this);
                    break;
                case 7:
                    myTextView8 = (TextView) itemView.findViewById(R.id.testSubTiroLibreIntentado);
                    itemView.setOnClickListener(this);
                    break;
                case 8:
                    myTextView9 = (TextView) itemView.findViewById(R.id.testSubPorcentajeTiroLibre);
                    itemView.setOnClickListener(this);
                    break;
                case 9:
                    myTextView10 = (TextView) itemView.findViewById(R.id.testSubReboteOfensivo);
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

    /*public void poronga(){
        ViewHolder.this.golesCampoHechos;
    }*/

}
