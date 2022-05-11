package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editPhone;
    private EditText editInstitution;
    private EditText editField;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editPhone = findViewById(R.id.editPhone);
        editInstitution = findViewById(R.id.editInstitution);
        editField = findViewById(R.id.editField);

        TextView cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstName = editFirstName.getText().toString();
                String lastName = editLastName.getText().toString();
                String phone = editPhone.getText().toString();
                String institution = editInstitution.getText().toString();
                String field = editField.getText().toString();

                if (firstName.equals("") || lastName.equals("")) {
                    Toast.makeText(ProfileActivity.this, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show();
                }
                else if (!phone.equals("") && !phone.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
                    Toast.makeText(ProfileActivity.this, "Le téléphone doit respecter le format : 123-456-7890", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent current = getIntent();
                    User user = (User) current.getSerializableExtra("user");
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    if (!institution.equals(""))
                        user.setInstitution(institution);
                    if (!field.equals(""))
                        user.setField(field);
                    if (!phone.equals(""))
                        user.setPhone(new BigInteger(phone));
                    user.setBirthdate(user.getBirthdate().replaceAll("/", "-"));

                    queue = Volley.newRequestQueue(ProfileActivity.this);

                    String url = "http://192.168.2.13:8000/api/addUser";
                    JSONObject jsonBody = new JSONObject();

                    try {
                        jsonBody.put("email", user.getEmail());
                        jsonBody.put("mdp", user.getPassword());
                        jsonBody.put("firstName", firstName);
                        jsonBody.put("lastName", lastName);
                        jsonBody.put("institution", institution);
                        jsonBody.put("field", field);

                        if (phone.equals(""))
                            jsonBody.put("phone", new BigInteger("0"));
                        else
                            jsonBody.put("phone", new BigInteger(phone.replaceAll("-", "")));

                        jsonBody.put("birthdate", user.getBirthdate().replaceAll("/", "-"));
                        jsonBody.put("role", 2);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Request a string response from the provided URL.
                    JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    DBManager dbManager = new DBManager();
                                    dbManager.setupDBConnection(ProfileActivity.this);
                                    dbManager.setUser(user);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(ProfileActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            });

                    // Add the request to the RequestQueue.
                    queue.add(objectRequest);
                }
            }
        });
    }
}