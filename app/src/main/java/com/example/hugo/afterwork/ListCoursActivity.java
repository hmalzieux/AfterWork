package com.example.hugo.afterwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListCoursActivity extends AppCompatActivity {
    String [][] cours = {{"Design Pattern","Ingé Log.","TDs"},{"Factory","Ingé Log.","Cours"},{"Pile","Compilation","TPs"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cours);
        TextView info = (TextView) findViewById(R.id.filtre_info);
        String filtre = getIntent().getExtras().getString("filtre");
        if (!filtre.equals("")) {
            info.setText(info.getText()+filtre);
        } else{
            info.setText("Les leçons ne sont pas filtrées");
        }

        ListView listView = (ListView) findViewById(R.id.listview_cours);
        CoursAdapter adapter = new CoursAdapter(this,cours);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }
}
