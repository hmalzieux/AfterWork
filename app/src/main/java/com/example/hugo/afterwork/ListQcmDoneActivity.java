package com.example.hugo.afterwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hugo.afterwork.androidsqlite.DatabaseHandler;

import java.util.ArrayList;

public class ListQcmDoneActivity extends AppCompatActivity {
    ArrayList<String[]> cours;
    private DatabaseHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_qcm_done);

        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);

        myDb = new DatabaseHandler(this);
        cours = myDb.getListQcmDone(sharedPreferences.getInt("idUser", -1));

        ListView listView = (ListView) findViewById(R.id.listview_qcm_old);
        QcmDoneAdapter adapter = new QcmDoneAdapter(this,cours);
        listView.setAdapter(adapter);
        if (listView.getCount() == 0){
            findViewById(R.id.list_vide).setVisibility(View.VISIBLE);
        }
    }
}
