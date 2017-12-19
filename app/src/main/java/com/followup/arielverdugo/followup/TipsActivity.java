package com.followup.arielverdugo.followup;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielverdugo on 29/11/17.
 */

public class TipsActivity extends AhoyOnboarderActivity implements View.OnClickListener{



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int a = R.drawable.anadirimagen;

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Tips a tener en cuenta", "A continuación se briandararán diferentes tips para que tu experiencia con FollowUp sea la mejor.",R.drawable.tips);

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Elegir Jugadores", "Primero tenés que seleccionar a tus jugadores favoritos",R.drawable.hardenmaster);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Seleccionar jugador a seguir", "Entre todos tus jugadores favoritos debes elejir a 1 para comenzar el seguimiento", R.drawable.pencil);
        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Seleccionar tipo de seguimiento", "Existen dos tipos de seguimientos: Partidos y Entrenamientos", R.drawable.pencil);
        AhoyOnboarderCard ahoyOnboarderCard5 = new AhoyOnboarderCard("Listo! Desde ahora podrás cargar las estadísticas de tus jugadores y verlas en tu historial", "Existen dos tipos de seguimientos: Partidos y Entrenamientos", R.drawable.pencil);
        //ImageView imagenFondo = (ImageView) findViewById(R.id.background_image);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard5.setBackgroundColor(R.color.black_transparent);


        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);
        pages.add(ahoyOnboarderCard5);


        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("Get Started");



        showNavigationControls(true);
        //setGradientBackground();

        setImageBackground(R.drawable.basketballboard);

        //Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);



    }

    private void onClick(AhoyOnboarderCard ahoyOnboarderCard1) {

            //Intent intento = new Intent(TipsActivity.this,CrearSeguimientoActivity.class);
            //startActivity(intento);
        Toast.makeText(this, "Pressed", Toast.LENGTH_SHORT).show();


    }




    @Override
    public void onFinishButtonPressed() {
        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
    }

}

