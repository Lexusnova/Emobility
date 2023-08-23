package com.example.emobility.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;


import com.example.emobility.R;
import com.example.emobility.SharedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.emobility.classes.Verwalter;

/**
 * @class MainActivity
 * @brief Erstellt das BottomNavigationBar und schaut ob
 */
public class MainActivity extends AppCompatActivity {
    private SharedViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent(); // Holt den Intent


        // Schaut ob der Benutzer schonmal Intiailsiert wurde
        if (!Verwalter.getAlreadyInit()) {
            Verwalter.setAlreadyInit(true);
        } else {
            Verwalter.getLadesaeule().clear();
            Verwalter.getReportListe().clear();
            Verwalter.getFavouritenListe().clear();
            Verwalter.setAlreadyInit(false);
        }

        //bottom Navigatio nwird erstellt und die Werte initiaisliert
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mapsFragment, R.id.favFragment, R.id.reportListFragment,R.id.reportFragment)
                .build();

        // Here it starting to initiaisliert
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        final NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(navView, navController);


    }

}