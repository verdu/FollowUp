package com.followup.arielverdugo.followup;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private UsuarioRepository usuarioRepository;
    private Button ingresar;
    private Button registrarse;
    private EditText loginEmail, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar =(Button) findViewById(R.id.ingresarBtn);
        registrarse =(Button) findViewById(R.id.registrarBtn);
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);

        ingresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                usuarioRepository = UsuarioRepository.getInstance(MainActivity.this);
                Map<String, String> credentials = new HashMap<>();
                credentials.put("email", loginEmail.getText().toString());
                credentials.put("contrasena", loginPassword.getText().toString());
                List<Usuario> users = usuarioRepository.findWhere(credentials);

                if(users != null && users.size() != 0) {
                    sessionManager = SessionManager.getInstance(MainActivity.this);
                    sessionManager.guardarEmail(loginEmail.getText().toString());
                    Intent intento = new Intent(MainActivity.this,HomeActivity.class);
                    intento.putExtra("email", loginEmail.getText().toString());
                    startActivity(intento);
                }
                else {
                    Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this,Registrarse.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}