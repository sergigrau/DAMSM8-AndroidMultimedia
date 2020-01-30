package edu.fje.dam2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

/**
 * Activity que permet demostrar el funcionament de un Servei
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */
public class M03_AsynTask extends AppCompatActivity {

    private ListView llista;
    private ArrayList<String> valors= new ArrayList<>();
    private int comptador=0;
    private ArrayAdapter adaptador;
    private  LlistaTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m03_asyntask);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button boto = findViewById(R.id.boto);


        llista =  findViewById(R.id.llista);
        valors.add("A");
        adaptador = new ArrayAdapter(this,
        android.R.layout.simple_list_item_1, valors);
        llista.setAdapter(adaptador);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int amplada = size.x;
        final int alcada = size.y;

        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animacio1 =
                        ObjectAnimator.ofFloat(boto, "x", 0, amplada-boto.getWidth());
                animacio1.setDuration(1000);
                ObjectAnimator animacio2 =
                        ObjectAnimator.ofFloat(boto, "y", 0, alcada-boto.getHeight());
                animacio1.setDuration(1000);
                ObjectAnimator animacio3 =
                        ObjectAnimator.ofFloat(boto, "x",  amplada-boto.getWidth(),0);
                animacio1.setDuration(1000);
                ObjectAnimator animacio4 =
                        ObjectAnimator.ofFloat(boto, "y", alcada-boto.getHeight(),0);
                animacio1.setDuration(1000);

                final AnimatorSet animacio = new AnimatorSet();
                animacio.playSequentially(animacio1, animacio2, animacio3, animacio4);

                animacio.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator a) {
                        super.onAnimationEnd(a);
                        task= new LlistaTask();
                        task.execute();
                        animacio.start();
                    }

                });
                animacio.start();
            }
        });
    }

    /**
     * Servei implementat mitjançant una herència d'AsyncTask
     * @author sergi.grau@fje.edu
     * @version 1.0 18.02.2018
     */
    private class LlistaTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return String.valueOf(comptador++);
        }

        @Override
        protected void onPostExecute(final String resultat) {

            runOnUiThread(new Runnable(){
                public void run(){
                    Log.v("DAM2", resultat);
                    valors.add(resultat);
                    adaptador.notifyDataSetChanged();
                }
            });


        }
    }
}
