package com.example.hugo.afterwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.afterwork.androidsqlite.ARepondu;
import com.example.hugo.afterwork.androidsqlite.DatabaseHandler;
import com.example.hugo.afterwork.androidsqlite.Question;

import java.util.ArrayList;

public class FormulaireQCMActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private int nbRadioGroup;
    private ArrayList<String[]> contenu;
    private DatabaseHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_qcm);
        myDb = new DatabaseHandler(this);
        sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        TextView titre = (TextView) findViewById(R.id.titre_qcm);
        titre.setText(sharedPreferences.getString("titreQcm", ""));
        nbRadioGroup = 0;
        nbRadioGroup = createQuestions();
        final Button valider = (Button) findViewById(R.id.boutonEnvoiQCM);
        final Button continuer = (Button) findViewById(R.id.bouton_continuer_QCM);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkResultats(nbRadioGroup)) {
                    ScrollView scrollview;
                    scrollview = (ScrollView) findViewById(R.id.scrollView_qcm);
                    scrollview.pageScroll(View.FOCUS_UP);
                    valider.setVisibility(View.GONE);
                    continuer.setVisibility(View.VISIBLE);
                }else {
                    Toast toast = Toast.makeText(FormulaireQCMActivity.this, getResources().getString(R.string.qcm_non_valide),Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    v.setGravity(Gravity.CENTER);
                    toast.show();
                }
            }
        });
        continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FormulaireQCMActivity.this,MainActivity.class));
            }
        });

    }

    private boolean checkResultats(int nbRadioGroup) {
        LinearLayout parent = (LinearLayout) findViewById(R.id.linear_qcm_reponse);
        boolean allSelected = true;
        for (int i=0;i<nbRadioGroup;i++) {
            RadioGroup question = (RadioGroup) parent.getChildAt(i).findViewById(R.id.radioGroup);
            int radioButtonID = question.getCheckedRadioButtonId();
            if (radioButtonID == -1) {
                allSelected = false;
            }
        }
        if (allSelected) {
            int bonne_rep = 0;
            for (int i = 0; i < nbRadioGroup; i++) {
                RadioGroup question = (RadioGroup) parent.getChildAt(i).findViewById(R.id.radioGroup);
                TextView reponse = (TextView) parent.getChildAt(i).findViewById(R.id.radio_reponse);
                int radioButtonID = question.getCheckedRadioButtonId();
                View radioButton = question.findViewById(radioButtonID);
                int idx = question.indexOfChild(radioButton);
                RadioButton r = (RadioButton) question.getChildAt(idx);
                for (int j = 0; j < question.getChildCount(); j++) {
                    question.getChildAt(j).setEnabled(false);
                }
                String selectedtext = r.getText().toString();
                String temp = reponse.getText().toString();
                reponse.setVisibility(View.VISIBLE);
                if (selectedtext.equals(temp)) {
                    reponse.setText("");
                    r.setButtonDrawable(R.drawable.bouton_bonne_reponse);
                    bonne_rep++;

                } else {
                    reponse.setText("La bonne réponse est : " + temp);
                    reponse.setBackgroundResource(R.color.redAlert);
                    r.setButtonDrawable(R.drawable.bouton_mauvaise_reponse);
                }
            }
            myDb.addARepondu(new ARepondu(sharedPreferences.getInt("idUser", -1),Integer.parseInt(sharedPreferences.getString("idQcm", "")),(int)(bonne_rep * 10 / nbRadioGroup)));
        }
        return allSelected;
    }

    private int createQuestions() {
        contenu = myDb.getQuestion(sharedPreferences.getString("idQcm", ""));
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup) findViewById(R.id.linear_qcm_reponse);
        View view;
        TextView question;
        RadioButton r1;
        RadioButton r2;
        RadioButton r3;
        TextView vrai_reponse;
        for(String[] s: contenu){
            view = inflater.inflate(R.layout.item_qcm_reponse, null);
            question = (TextView) view.findViewById(R.id.question);
            question.setText(s[0]);
            r1 = (RadioButton) view.findViewById(R.id.radio_r1);
            r1.setText(s[1]);
            r2 = (RadioButton) view.findViewById(R.id.radio_r2);
            r2.setText(s[2]);
            r3 = (RadioButton) view.findViewById(R.id.radio_r3);
            r3.setText(s[3]);
            vrai_reponse = (TextView) view.findViewById(R.id.radio_reponse);
            vrai_reponse.setText(s[4]);
            parent.addView(view, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
        return contenu.size();
    }
}
