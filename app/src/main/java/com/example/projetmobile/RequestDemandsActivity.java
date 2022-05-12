/****************************************
 Fichier : RequestDemandsActivity
 Auteur : Frédérick Perazzelli-Delorme
 Fonctionnalité : C6 - Affiche la liste de toutes les demandes
 Date : 6 mai 2022
 Techniques de l’informatique
 Vérification :
 Date Nom Approuvé
 =========================================================
 Historique de modifications :
 Date Nom Description
 =========================================================
 ****************************************/
package com.example.projetmobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class RequestDemandsActivity extends AppCompatActivity {

    ProgressBar circularProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_list_demands);

        circularProgressBar = (ProgressBar) findViewById(R.id.circular_progress);
        circularProgressBar.setVisibility(View.INVISIBLE);

        ShowProgressBarTask showTask = new ShowProgressBarTask();
        showTask.execute();

        final TextView responseReceived = (TextView) findViewById(R.id.stringRequests);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.13:8000/api/demandList";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String responseReceived = response.substring(response.indexOf("["));

                Intent intent = new Intent(RequestDemandsActivity.this , ListDemandsActivity.class);
                Bundle b = new Bundle();
                b.putString("listDemands", responseReceived);
                intent.putExtra("bundle", b);

                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseReceived.setText(error.toString());
            }
        });

        queue.add(stringRequest);
    }

    private class ShowProgressBarTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute(){
            circularProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(Void... params){
            for (int i = 0; i < 10; i++){
                try{
                    Thread.sleep(100);
                    publishProgress(i * 10);
                }catch (InterruptedException e){
                    return -1;
                }
            }
            return 100;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            int progress = values[0];
            circularProgressBar.setProgress(progress);
        }

        @Override
        protected void onPostExecute (Integer result){
            circularProgressBar.setProgress(View.INVISIBLE);
        }
    }

}