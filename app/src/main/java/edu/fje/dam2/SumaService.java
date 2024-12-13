package edu.fje.smx8;

import android.app.IntentService;
import android.content.Intent;

public class SumaService extends IntentService {

    public SumaService() {
        super("SumaService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int n1 = intent.getIntExtra("n1", 0);
            int n2 = intent.getIntExtra("n2", 0);
            int result = n1 + n2;

            Intent resultIntent = new Intent("edu.fje.smx8.SUMA_RESULTAT");
            resultIntent.putExtra("suma", result);
            sendBroadcast(resultIntent);
        }
    }
}