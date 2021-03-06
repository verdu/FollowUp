package com.followup.arielverdugo.followup;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

/**
 * Created by arielverdugo on 23/1/18.
 */

public class TabEstadisticaEntrenamientoSimple  extends android.support.v4.app.Fragment implements EstadisticaEntrenamientoSimpleAdapter.ItemClickListener{

    private static  String TAG = "RecyclerViewFragment";
    private static  String KEY_LAYOUT_MANAGER = "layoutManager";
    private static  int SPAN_COUNT = 2;
    //private static  int DATASET_COUNT = (SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorLibres)*2;
    private static int DATASET_COUNT = 0;
    RecyclerView recyclerViewGenerales;

    private StaggeredGridLayoutManager gaggeredGridLayoutManager;


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    protected TabEstadisticaEntrenamientoSimple.LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    public RecyclerView mRecyclerView;
    protected EstadisticaEntrenamientoSimpleAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;
    public ViewGroup vistasLibres;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DATASET_COUNT = (SeleccionarEntrenamientoAdapter.SeleccionarEntrenamientoViewHolder.contadorLibres)*2;
        initDataset();

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.tab_estadisticas_generales, container, false);
        View rootView = inflater.inflate(R.layout.tab_estadisticas_entrenamiento_simple, container, false);
        Context c = rootView.getContext();
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerEntrenamientoSimple);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = TabEstadisticaEntrenamientoSimple.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (TabEstadisticaEntrenamientoSimple.LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new EstadisticaEntrenamientoSimpleAdapter(c,mDataset);
        //mAdapter.setClickListener(this);
        // Set CustomAdapter as the adapter for RecyclerView.

        mRecyclerView.setAdapter(mAdapter);
        int numberOfColumns = 2;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(6);
        mRecyclerView.setLayoutManager(new GridLayoutManager(c, numberOfColumns));
        return rootView;


    }

    public void setRecyclerViewLayoutManager(TabEstadisticaEntrenamientoSimple.LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = TabEstadisticaEntrenamientoSimple.LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = TabEstadisticaEntrenamientoSimple.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = TabEstadisticaEntrenamientoSimple.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }


    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + mAdapter.getItem(position) + ", which is at cell position " + position);
    }
}


