package edu.fje.dam2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Activitat que demostra el funcionament d'una Alarma (un servei
 * del sistema) * per a demanar periodicament el valor d'una cotitzacio cada minut
 * @version 5.0 27.01.2020
 * @author sergi.grau@fje.edu
 */
public class M08_AlarmaActivity extends Activity {

    private static final long TEMPS_REPETICIO = 1000 * 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m08_alarma);

    }

    /**
     * Mètode que posa en marxa una Alarma un sol cop
     * Es cridat des del layout en fer click
     * @param view
     */
    public void iniciarAlarma(View view) {
        EditText temps =  findViewById(R.id.temps);
        int i = Integer.parseInt(temps.getText().toString());

        Intent intent = new Intent(this, M08_AlarmaReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //alarma que només s'executa un cop
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);

        Toast.makeText(this, "Alarma en " + i + " segons",
                Toast.LENGTH_LONG).show();
    }

    /**
     * Mètode que posa en marxa una Alarma i es repeteix
     * Es cridat des del layout en fer click
     * @param view
     */
    public void iniciarAlarmaRepeticio(View view) {
        EditText temps = (EditText) findViewById(R.id.temps);
        int i = Integer.parseInt(temps.getText().toString());

        Intent intent = new Intent(this, M08_AlarmaReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //alarma que es repeteix, al instant
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                TEMPS_REPETICIO, pendingIntent);

        Toast.makeText(this, "Alarma REPETIDA en " + TEMPS_REPETICIO + " segons",
                Toast.LENGTH_LONG).show();
    }
}
