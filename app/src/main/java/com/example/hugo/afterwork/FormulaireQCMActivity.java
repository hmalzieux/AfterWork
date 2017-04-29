package com.example.hugo.afterwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class FormulaireQCMActivity extends AppCompatActivity {

    private Button Button;
    private RadioButton RadioButton;
    private View view;
    private int note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_qcm);

        onRadioButtonClicked(view);
        addListenerOnButton();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_r1:
                if (checked)
                    // Vérifie si c'est la bonne réponse avec la base de données
                    //si bonne réponse alors incrémentation de note
                    break;
            case R.id.radio_r2:
                if (checked)
                    // Vérif bd
                    break;
            case R.id.radio_r3:
                if (checked)
                    // Vérif bd
                    break;
        }
    }

    public void addListenerOnButton() {
        Button = (Button) findViewById(R.id.boutonEnvoiQCM);

        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormulaireQCMActivity.this, FormulaireQCMActivity.class);
                startActivity(intent);
            }

        });

    }

}
