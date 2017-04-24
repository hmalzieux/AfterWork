package com.example.hugo.afterwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CoursActivity extends AppCompatActivity {

    private android.widget.Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        Button = (Button) findViewById(R.id.bouton);

        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormulaireQCMActivity.this, FormulaireQCMActivity.class);
                startActivity(intent);
            }

        });
}
