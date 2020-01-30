package edu.fje.dam2;

import android.os.Bundle;

import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Classe que assigna propietats a vistes creades de manera dinàmica
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */

public class M09_Propietats_dinamiques extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m09_propietats_dinamiques);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button boto = new Button(this);
        boto.setText("Sergi Grau");

        LinearLayout contingut = findViewById(R.id.contingut);
        LinearLayout ll = new LinearLayout(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(lp);
        contingut.addView(ll);
        ll.addView(boto,lp);
    }

}
