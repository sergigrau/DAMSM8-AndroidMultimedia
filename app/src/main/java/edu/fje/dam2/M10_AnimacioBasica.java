package edu.fje.dam2;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;

import android.view.Display;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Classe que implementa una animació bàsica
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */

public class M10_AnimacioBasica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m10_animacio_basica);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int amplada = size.x;
                int alcada = size.y;

                ObjectAnimator anim =
                        ObjectAnimator.ofFloat(fab, "y", 0, alcada / 2);
                anim.setDuration(1000);
                anim.start();
                anim = ObjectAnimator.ofFloat(fab, "alpha", 1f, 0f);
                anim.setDuration(1000);
                anim.start();
            }
        });


    }

}
