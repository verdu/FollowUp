package com.followup.arielverdugo.followup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button ingresar;
    private Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar =(Button) findViewById(R.id.button);
        registrarse =(Button) findViewById(R.id.button2);

        ingresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intento = new Intent(MainActivity.this,Ingresar.class);
                startActivity(intento);
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intento = new Intent(MainActivity.this,Registrarse.class);
                startActivity(intento);
            }
        });

    }
}
