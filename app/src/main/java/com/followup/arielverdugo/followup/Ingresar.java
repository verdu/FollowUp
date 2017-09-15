package com.followup.arielverdugo.followup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arielverdugo on 20/4/17.
 */

public class Ingresar extends Activity {

    private Button ingresar;

    private EditText email;
    private EditText contrasena;

    private SessionManager sessionManager;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        sessionManager = new SessionManager(this);
        email = (EditText) findViewById(R.id.editText7);
        contrasena = (EditText) findViewById(R.id.editText8);
        ingresar = (Button) findViewById(R.id.button4);


        ingresar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email.getText().toString());
                params.put("contrasena", contrasena.getText().toString());
                Usuario user = UsuarioRepository.getInstance(Ingresar.this).findUniqueWhere(params);

                if(user == null)
                {
                    Toast.makeText(Ingresar.this, "No existe", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    if(contrasena.getText().toString().equals(user.getContrasena()))
                    {
                        Toast.makeText(Ingresar.this, "Logged", Toast.LENGTH_SHORT).show();
                        String emailn = email.getText().toString();
                        sessionManager.guardarEmail(email.getText().toString());
                        Intent intento = new Intent(Ingresar.this,HomeActivity.class);
                        intento.putExtra("emailn", email.getText().toString());
                        startActivity(intento);
                    }
                }
                onBackPressed();
            }
        }

        );

    }
}
