package edu.fje.dam2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

/**
 *  BroadcastReceiver que el sistema mostra cada vegada
 *  que l'alarma arriba a la seva fí
 * @version 5.0 27.01.2020
 * @author sergi.grau@fje.edu
 */
public class M08_AlarmaReceiver extends BroadcastReceiver {
    public M08_AlarmaReceiver() {
    }
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Vibrant Alarma",
                    Toast.LENGTH_LONG).show();
            Vibrator vibrator = (Vibrator) context
                    .getSystemService(Context.VIBRATOR_SERVICE);

            VibrationEffect effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);
        }

}
