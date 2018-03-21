package com.example.lestiennathan.projet_gsb.Medecin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.lestiennathan.projet_gsb.Metier.GsonRequest;
import com.example.lestiennathan.projet_gsb.Metier.Medecin.Medecin;
import com.example.lestiennathan.projet_gsb.Metier.Medecin.MedecinAdapter;
import com.example.lestiennathan.projet_gsb.Metier.Medecin.Medecins;
import com.example.lestiennathan.projet_gsb.Metier.VolleyHelper;
import com.example.lestiennathan.projet_gsb.R;

import java.util.ArrayList;

public class MedecinsActivity extends Activity {

    Button buttonAjouterMedecin;
    Button buttonRefreshMedecin;

    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String medecinsUrl = "http://192.168.210.6:8081/TISSOT-cakephp-3.3/medecins.json";
    ListView listViewMedecins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecins);

        listViewMedecins = (ListView) findViewById(R.id.lv_medecins);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        buttonAjouterMedecin = (Button) findViewById(R.id.btn_addMedecin);

        buttonAjouterMedecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateMedecinActivity.class);
                startActivity(intent);
            }
        });

        buttonRefreshMedecin = (Button) findViewById(R.id.btn_refreshMed);
        buttonRefreshMedecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final GsonRequest gsonRequest = new GsonRequest(medecinsUrl, Medecins.class, null, new Response.Listener<Medecins>() {
                    @Override
                    public void onResponse(Medecins medecins) {
                        ArrayList<Medecin> liste = medecins.getMedecins();

                        MedecinAdapter adapter = new MedecinAdapter(getApplicationContext(), liste );



                        listViewMedecins.setAdapter((ListAdapter) adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null) Log.e("MedecinsActivity", volleyError.getMessage());
                    }
                });

                ListView listViewDetailMedecin = (ListView) findViewById(R.id.lv_medecins);

                listViewDetailMedecin.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        Medecin monMedecin;
                        monMedecin = (Medecin) parent.getItemAtPosition(position);
                        Intent i = new Intent(MedecinsActivity.this, DetailMedecinActivity.class);
                        i.putExtra("Medecin", monMedecin);
                        startActivity(i);
                    }

                });


                VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
            }
        });

    }
}
