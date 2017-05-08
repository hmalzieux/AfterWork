package com.example.hugo.afterwork;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;

import com.example.hugo.afterwork.androidsqlite.DatabaseHandler;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SensorActivity sensor;
    private DatabaseHandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sensor = new SensorActivity((SensorManager)getSystemService(SENSOR_SERVICE),this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        myDb = new DatabaseHandler(this);
        String[] res = myDb.getInfoUser(sharedPreferences.getInt("idUser", -1));

        View headerView = navigationView.getHeaderView(0);
        TextView nom_prenom = (TextView) headerView.findViewById(R.id.nav_nom_prenom);
        TextView mail = (TextView) headerView.findViewById(R.id.nav_mail);

        nom_prenom.setText(res[0]+" "+res[1]);
        mail.setText(res[2]);
    }

    public void onResume() {
        super.onResume();
        sensor.onResume();
    }

    public void onPause() {
        super.onPause();
        sensor.onPause();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            deconnection();
        }
    }

    public void onWiggle() {
        /*
        * Sert pour le capteur (SensorActivity.java)
        * Si le drawer est visible alors on le cache et inversement
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_profil) {
            fragment = new profilFragment();
            title = "Profil";
        } else if (id == R.id.nav_cours) {
            Intent intent = new Intent(this,ListCoursActivity.class);
            intent.putExtra("filtre","");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (id == R.id.nav_qcm) {
            fragment = new qcmFragment();
            title = "QCM";
        }else if (id == R.id.nav_matiere) {
            fragment = new MatiereFragment();
            title = "Matière";
        }else if (id == R.id.nav_logout){
            drawer.closeDrawer(GravityCompat.START);
            deconnection();
        }

        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void deconnection(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Déconnection");
        builder.setMessage("Attention ! Vous allez être déconnecté! êtes vous sûr de cela ?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new Dialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(getString(R.string.annuler), new Dialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
