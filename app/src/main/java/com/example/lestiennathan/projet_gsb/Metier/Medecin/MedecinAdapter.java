package com.example.lestiennathan.projet_gsb.Metier.Medecin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lestiennathan.projet_gsb.R;

import java.util.ArrayList;

/**
 * Created by lestien.nathan on 21/03/2018.
 */

public class MedecinAdapter extends ArrayList<Medecin> {

    public MedecinAdapter(Context context, ArrayList<Medecin> lesMedecins) {super(context, 0 , lesMedecins);}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_medecins, parent, false);
        }

        Medecin currentMedecin = getItem(position);

        TextView TextViewNom = (TextView) listItemView.findViewById(R.id.tv_nom_medecin);
        TextViewNom.setText(currentMedecin.getNom());

        TextView TextViewPrenom = (TextView) listItemView.findViewById(R.id.tv_prenom_medecin);
        TextViewPrenom.setText(currentMedecin.getPrenom());

        return listItemView;
    }

    public Context getContext() {
        return context;
    }
}
