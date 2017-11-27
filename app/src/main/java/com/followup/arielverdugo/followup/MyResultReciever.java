package com.followup.arielverdugo.followup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.ImageView;

/**
 * Created by arielverdugo on 24/11/17.
 */

public class MyResultReciever extends ResultReceiver {

    private Receiver mReceiver;
    private ImageView foto;
    private Context context;
    private Bundle data;

    public MyResultReciever(Handler handler, ImageView foto, Context context, Bundle data) {
        super(handler);
        this.foto = foto;
        this.context = context;
        this.data = data;
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);

    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);

        }

    }

}