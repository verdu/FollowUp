package com.followup.arielverdugo.followup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by arielverdugo on 1/6/17.
 */

public class Registrarse extends Activity {

    private SessionManager sessionManager;
    private Button registrarse;

    DatabaseHelper helper = new DatabaseHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        sessionManager = new SessionManager(this);


        registrarse = (Button) findViewById(R.id.button3);

        registrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String emailText = ((EditText) findViewById(R.id.editText4)).getText().toString();
                String contrasenaText = ((EditText) findViewById(R.id.editText)).getText().toString();
                String edadText = ((EditText) findViewById(R.id.editText1)).getText().toString();
                int edad = Integer.parseInt(edadText);
                String confirmarText = ((EditText) findViewById(R.id.editText5)).getText().toString();
                String nombreText = ((EditText) findViewById(R.id.editText2)).getText().toString();
                String apellidolText = ((EditText) findViewById(R.id.editText3)).getText().toString();


                if (emailText.isEmpty() || contrasenaText.isEmpty() || confirmarText.isEmpty() || nombreText.isEmpty() || apellidolText.isEmpty() || edadText.isEmpty()) {
                    Toast.makeText(Registrarse.this, "Datos insuficientes", Toast.LENGTH_SHORT).show();

                } else if (confirmarText.equals(contrasenaText) == true) {
                    Usuario u = new Usuario(nombreText, apellidolText, edad, emailText, contrasenaText);
                    UsuarioRepository.getInstance(Registrarse.this).addUsuario(u);
                    onBackPressed();

                    //sessionManager.guardarEmail(emailText);
                    Toast.makeText(Registrarse.this, "Registro con éxito", Toast.LENGTH_SHORT).show();
                    Intent intento = new Intent(Registrarse.this, HomeActivity.class);

                    startActivity(intento);

                } else {
                    Toast.makeText(Registrarse.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

                }


            }

        });


    }





}
