package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPassword;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, editEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    queue = Volley.newRequestQueue(LoginActivity.this);

                    String url = "http://192.168.2.13:8000/api/login";
                    JSONObject jsonBody = new JSONObject();

                    try {
                        jsonBody.put("email", email);
                        jsonBody.put("mdp", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Request a string response from the provided URL.
                    JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    if (response.has("err")) {
                                        String error = "";
                                        try {
                                            error = response.get("err").toString();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        User user = new User();
                                        try {
                                            user.setId(response.getInt("id"));
                                            user.setEmail(response.getString("email"));
                                            user.setFirstName(response.getString("firstName"));
                                            user.setLastName(response.getString("lastName"));
                                            user.setInstitution(response.getString("institution"));
                                            user.setField(response.getString("field"));
                                            if (!response.isNull("phone"))
                                                user.setPhone(new BigInteger(response.get("phone").toString()));
                                            user.setInstitution(response.getString("institution"));
                                            user.setBirthdate(response.get("birthdate").toString());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        DBManager dbManager = new DBManager();
                                        dbManager.setupDBConnection(LoginActivity.this);
                                        dbManager.setUser(user);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    // Add the request to the RequestQueue.
                    queue.add(objectRequest);
                }
                // À envoyer vers l'accueil
                /*Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);*/

            }
        });

        TextView gotoRegister = findViewById(R.id.gotoRegister);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}