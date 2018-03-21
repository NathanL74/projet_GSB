package com.example.lestiennathan.projet_gsb.Medecin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lestiennathan.projet_gsb.Metier.Medecin.Medecin;
import com.example.lestiennathan.projet_gsb.R;

import java.util.HashMap;
import java.util.Map;

public class DetailMedecinActivity extends AppCompatActivity {

    Button btn_supprimer;
    RequestQueue requestQueue;
    String suppMedecinUrl = "http://192.168.210.6:8081/TISSOT-cakephp-3.3/medecins/delete/";

    Medecin monMedecin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_medecin);

        monMedecin = (Medecin) getIntent().getSerializableExtra("Medecin");

        TextView textView_detail_medecin = (TextView) findViewById(R.id.detail_medecin);
        textView_detail_medecin.setText(monMedecin.getNom() + ' ' + monMedecin.getPrenom());


        btn_supprimer = (Button) findViewById(R.id.btn_suppMedecin);
        requestQueue = Volley.newRequestQueue(getApplicationContext());


        btn_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suppMedecinUrl += monMedecin.getId();
                StringRequest request = new StringRequest(Request.Method.POST, suppMedecinUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailMedecinActivity.this, "Le medecin a été supprimé ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        return parameters;
                    }

                };
                requestQueue.add(request);
            }

        });
    }
}
