package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.Date;

public class ComplaintActivity extends AppCompatActivity {
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_complaint);

        TextView cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                queue = Volley.newRequestQueue(ComplaintActivity.this);

                String url = "http://192.168.2.13:8000/api/addComplaint";
                JSONObject jsonBody = new JSONObject();

                DBManager dbManager = new DBManager();
                dbManager.setupDBConnection(ComplaintActivity.this);
                User user = dbManager.getCurrentUser();
                TextView description = findViewById(R.id.editTextTextMultiLine);

                try {
                    jsonBody.put("user", user.getId());
                    jsonBody.put("description", description.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Request a string response from the provided URL.
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Toast.makeText(ComplaintActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ComplaintActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                // Add the request to the RequestQueue.
                queue.add(objectRequest);
            }
        });
    }
}