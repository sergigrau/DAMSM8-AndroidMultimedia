package edu.fje.dam2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * Activitat que mostra el funcionament de la
 * classe MediaPlayer i de la gestió del focus
 *
 *   <meta-data
 * android:name="preloaded_fonts"
 * android:resource="@array/preloaded_fonts" />
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */

public class M01_AudioActivity extends AppCompatActivity {

    private AudioManager am;
    private MediaPlayer mp;
    private String LOG = "edu.fje.dam2";
    private SeekBar barraProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                if (mp.isPlaying()) {
                    text = "Pausant Audio";
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    mp.pause();

                } else {
                    text = "Reproduint Audio";
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    mp.start();
                }
                Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mp = MediaPlayer.create(this, R.raw.m02_audio1);
        barraProgress.setMax(mp.getDuration());

        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int requestResult = am.requestAudioFocus(
                mAudioFocusListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
        if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mp.start();

            Log.d(LOG, "audioFocus listener aconseguit amb èxit");

        } else if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
            mp.stop();
        } else {
            Log.d(LOG, "error en la petició del listener de focus ");
        }

        barraProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mp.seekTo(barraProgress.getProgress());
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

    /**
     * Classe interna que gestiona tots els canvis d'estat del AudioFocus.
     * Es tracta d'un listener per a determinats canvis d'audio
     */
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {

            switch (focusChange) {
                //perdem el focus per exemple, una altre reproductor de música
                case AudioManager.AUDIOFOCUS_LOSS:
                    mp.stop();
                    Log.d(LOG, "AudioFocus: rebut AUDIOFOCUS_LOSS");
                    mp.release();
                    mp = null;
                    break;
                //perdem el focus temporalement, per exemple, trucada
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    if (mp.isPlaying())
                        mp.pause();

                    Log.d(LOG, "AudioFocus: rebut AUDIOFOCUS_LOSS_TRANSIENT");

                    break;
                //baixem el volum temporalment
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mp.setVolume(0.5f, 0.5f);
                    Log.d(LOG, "AudioFocus: rebut AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    break;

                //es recupera el focus d'audio
                case AudioManager.AUDIOFOCUS_GAIN:
                    mp.start();
                    mp.setVolume(1.0f, 1.0f);
                    Log.d(LOG, "AudioFocus: rebut AUDIOFOCUS_GAIN");
                    break;

                default:
                    Log.e(LOG, "codi desconegut");
            }
        }
    };
}
