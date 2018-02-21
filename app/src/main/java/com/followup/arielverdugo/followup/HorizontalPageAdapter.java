package com.followup.arielverdugo.followup;

/**
 * Created by arielverdugo on 6/12/17.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPageAdapter extends PagerAdapter implements View.OnClickListener{

    /*private final UtilsNew.LibraryObject[] LIBRARIES = new UtilsNew.LibraryObject[]{
            new UtilsNew.LibraryObject(
                    R.drawable.eliminar,
                    "Strategy"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.editar,
                    "Design"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Development"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Quality Assurance"
            )
    };*/

    private final UtilsNew.LibraryObject[] LIBRARIES = new UtilsNew.LibraryObject[]{
            new UtilsNew.LibraryObject(
                    R.drawable.eliminar,
                    "Strategy"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.editar,
                    "Design"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Development"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Quality Assurance"
            )
    };


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsTwoWay;

    public HorizontalPageAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : SeguimientoActivity.jugadoresFavoritos.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        if (mIsTwoWay) {
            view = mLayoutInflater.inflate(R.layout.two_way_item, container, false);

            final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                    (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
            verticalInfiniteCycleViewPager.setAdapter(
                    new VerticalPageAdapter(mContext)
            );
            verticalInfiniteCycleViewPager.setCurrentItem(position);
            //verticalInfiniteCycleViewPager.setOnClickListener();
        } else {
            view = mLayoutInflater.inflate(R.layout.item, container, false);
            //UtilsNew.setupItemNew(view,SeguimientoActivity.jugadoresFavoritos.get(position));
            UtilsNew.setupItemNew(view,SeguimientoActivity.jugadoresFavoritos.get(position));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            //view = mLayoutInflater.inflate(R.layout.item, container, false);
            //UtilsNew.setupItem(view, LIBRARIES[position]);
        }

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }


    @Override
    public void onClick(View v) {

    }
}
