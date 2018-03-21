package com.example.lestiennathan.projet_gsb.Metier.Medecin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lestien.nathan on 21/03/2018.
 */

public class Medecins {

    private ArrayList<Medecin> medecins;

    public ArrayList<Medecin> getMedecins() { return medecins;
    }

    public ArrayList<HashMap<String, String>> getMedecinsArray(){
        ArrayList<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        for(com.example.lestiennathan.projet_gsb.Metier.Medecin.Medecin medecin : getMedecins()){
            liste.add(medecin.getMedecins());
        }
        return liste;
    }
}
