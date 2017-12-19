package com.followup.arielverdugo.followup;

/**
 * Created by arielverdugo on 6/12/17.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class UtilsNew {

    public static void setupItem(final View view, final LibraryObject libraryObject) {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        txt.setText(libraryObject.getTitle());

        final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        img.setImageResource(libraryObject.getRes());
        int id = view.getId();
    }

    public static void setupItemNew(final View view, final Jugador jugador)
    {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        txt.setText(jugador.getApellido());

        //final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        //Bitmap fotoJugador = BitmapFactory.decodeByteArray(jugador.getFoto(), 0, jugador.getFoto().length);
        //img.setImageBitmap(fotoJugador);
        int id = view.getId();
    }

    public static class LibraryObject {

        private String mTitle;
        private int mRes;

        public LibraryObject(final int res, final String title) {
            mRes = res;
            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }

}

