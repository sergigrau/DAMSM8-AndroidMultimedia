package edu.fje.dam2;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * Activitat que mostra el funcionament de la
 * classe MediaPlayer amb un IntentService
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */

public class M02_AudioServeiActivity extends AppCompatActivity {


    private String LOG = "edu.fje.dam2";
    private SeekBar barraProgress;
    private boolean isReproduint= false;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent= new Intent(this, M02_AudioIntentService.class);
        intent.putExtra("operacio", "inici");
        startService(intent);

        setContentView(R.layout.m01_audio_principal);
        barraProgress = findViewById(R.id.barraProgress);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(android.R.drawable.ic_media_pause);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;

                if (!isReproduint) {
                    text = "Pausant Audio";
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    intent.putExtra("operacio", "inici");
                    startService(intent);

                } else {
                    text = "Reproduint Audio";
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    intent.putExtra("operacio", "pausa");
                    startService(intent);
                }

                Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


       // barraProgress.setMax(mp.getDuration());



        barraProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //mp.seekTo(barraProgress.getProgress());
             }
             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {
             }
             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m01_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
