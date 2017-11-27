package com.followup.arielverdugo.followup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import java.io.FileNotFoundException;

/**
 * Created by arielverdugo on 24/11/17.
 */

public class PickImageActivity extends AppCompatActivity{

    private int PICK_IMAGE = 1;
    private String RESULT_KEY = "img";
    private String RECEIVER_KEY = "1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap received = null;
        try {
            received = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       Intent intent = new Intent();
        intent.putExtra("imagen",received);
        setResult(0,intent);
        finish();


    }



}
