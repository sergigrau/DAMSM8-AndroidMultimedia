package edu.fje.dam2;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * Classe que fa us del sistema de notificaicons amb SnackBar. Es pot aplicar
 * a qualsevol View, però és recomanable un CoordinatorLayout
 * @version 5.0 27.01.2020
 * @author sergi.grau@fje.edu
 */
public class M04_Notificacions_SnackBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m04_notificacions_snack_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reproduïnt...", Snackbar.LENGTH_LONG)
                        .setAction("Veure", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Acció veure més",
                                        Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });
    }

}
