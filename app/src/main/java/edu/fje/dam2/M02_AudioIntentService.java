package edu.fje.dam2;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

/** Servei que hereta de IntentService per a crear un fil cada vegada que es crida.
 * Si es criden diversos passen a la cua
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */

public class M02_AudioIntentService extends IntentService {

    private MediaPlayer mp;


    public M02_AudioIntentService() {
        super("serveiAudio");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.m02_audio1);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (intent != null) {
            String operacio = intent.getStringExtra("operacio");
            switch (operacio){
                case "inici" : mp.start();
                    break;
                case "pausa" : mp.pause();
                    break;
                case "salta": mp.seekTo(10000);
                    break;
                default:
                    break;
            }
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
