package com.example.lestiennathan.projet_gsb.Medecin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lestiennathan.projet_gsb.R;

import java.util.HashMap;
import java.util.Map;

public class CreateMedecinActivity extends AppCompatActivity {

    EditText editTextNom;
    EditText editTextPrenom;
    Button btn_valider;
    RequestQueue requestQueue;
    String addMedecinUrl = "http://192.168.210.6:8081/TISSOT-cakephp-3.3/medecins/add.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medecin);

        editTextNom = (EditText) findViewById(R.id.et_nomMed);
        editTextPrenom = (EditText) findViewById(R.id.et_prenomMed);
        btn_valider = (Button) findViewById(R.id.btn_addMedecin);
        requestQueue = Volley.newRequestQueue(getApplicationContext());


        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, addMedecinUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateMedecinActivity.this, "Nouveau médecin " + editTextNom.getText().toString() + " " + editTextPrenom.getText().toString() + " a été créer", Toast.LENGTH_SHORT).show();
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
                        parameters.put("nom", editTextNom.getText().toString());
                        parameters.put("prenom", editTextPrenom.getText().toString());

                        return parameters;
                    }

                };
                requestQueue.add(request);
            }

        });
    }
}
