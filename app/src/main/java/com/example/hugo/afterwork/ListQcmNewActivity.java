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

public class ListQcmNewActivity extends AppCompatActivity {
    ArrayList<String[]> qcm;
    private DatabaseHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_qcm_new);

        //BD
        final SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);

        myDb = new DatabaseHandler(this);
        qcm = myDb.getListQcmNew(sharedPreferences.getInt("idUser", -1));

        ListView listView = (ListView) findViewById(R.id.listview_qcm_new);
        QcmNewAdapter adapter = new QcmNewAdapter(this,qcm);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("titreQcm",qcm.get(position)[0]);
                editor.putString("idQcm",qcm.get(position)[1]);
                editor.commit();
                startActivity(new Intent(ListQcmNewActivity.this,FormulaireQCMActivity.class));
            }
        });
        if (listView.getCount() == 0){
            findViewById(R.id.list_vide).setVisibility(View.VISIBLE);
        }
    }
}
