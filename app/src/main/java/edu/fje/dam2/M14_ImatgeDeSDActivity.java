package edu.fje.dam2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
/**
 * Activity que permet l'accés directament al dispositiu de càmera
 * @version 5.0 27.01.2020
 * @author sergi grau . amb llicència de IOC
 */
public class M14_ImatgeDeSDActivity extends Activity {
	
	private static final int ACTIVITAT_SELECCIONAR_IMATGE = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m14_imatges_sd);
        
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, ACTIVITAT_SELECCIONAR_IMATGE); 
    }
    
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
     
        switch(requestCode) {
            case ACTIVITAT_SELECCIONAR_IMATGE:
            	if(resultCode == RESULT_OK){  
                    Uri seleccio = intent.getData();
                    String[] columna = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(seleccio, columna, null, null, null);
                    cursor.moveToFirst();

                    int indexColumna = cursor.getColumnIndex(columna[0]);
                    String rutaFitxer = cursor.getString(indexColumna);
                    cursor.close();


                    Bitmap imatge = BitmapFactory.decodeFile(rutaFitxer);
                    ImageView imageView = (ImageView)findViewById(R.id.imageView1);
                    imageView.setImageBitmap(imatge);
                }
        }
    }    
}