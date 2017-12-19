package com.followup.arielverdugo.followup;

/**
 * Created by arielverdugo on 6/12/17.
 */


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class VerticalPageAdapter extends PagerAdapter {

    private final UtilsNew.LibraryObject[] TWO_WAY_LIBRARIES = new UtilsNew.LibraryObject[]{
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Fintech"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Delivery"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Social network"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "E-commerce"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Wearable"
            ),
            new UtilsNew.LibraryObject(
                    R.drawable.pencil,
                    "Internet of things"
            )
    };

    private LayoutInflater mLayoutInflater;

    public VerticalPageAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return TWO_WAY_LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.item, container, false);

        UtilsNew.setupItem(view, TWO_WAY_LIBRARIES[position]);


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
}

