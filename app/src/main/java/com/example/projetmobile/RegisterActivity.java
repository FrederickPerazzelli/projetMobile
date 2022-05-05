package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText date;
    private EditText password;
    private EditText confirmPassword;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editEmail);
        date = findViewById(R.id.editBirthdate);
        password = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.editPasswordConfirm);

        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pass1 = password.getText().toString();
                String pass2 = confirmPassword.getText().toString();
                String emailText = email.getText().toString();
                String dateText = date.getText().toString();

                if (pass1.equals("") || pass2.equals("") || emailText.equals("") || dateText.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
                else if (!pass1.matches(pass2)) {
                    Toast.makeText(RegisterActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                }
                else {
                    queue = Volley.newRequestQueue(RegisterActivity.this);

                    String url = "http://192.168.2.13:8000/api/compareEmail/"+emailText;

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Reste à traiter la réponse
                                    response = response.substring(response.indexOf('\"')).replace("\"", "");

                                    if (response.equals("True")) {
                                        Toast.makeText(RegisterActivity.this, "Ce courriel est déjà utilisé", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        User user = new User();
                                        user.setEmail(emailText);
                                        user.setBirthdate(dateText);
                                        user.setPassword(pass1);

                                        Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });

        TextView gotoLogin = findViewById(R.id.gotoLogin);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}