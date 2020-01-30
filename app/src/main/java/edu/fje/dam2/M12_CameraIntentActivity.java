package edu.fje.dam2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Activity que permet l'accés a l'aplicació estàndard de la càmera de fotos
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */

public class M12_CameraIntentActivity extends AppCompatActivity {

    private static final int PERMIS_CAMARA = 200;
    private static final int PERMIS_CAPTURA_IMATGE = 300;
    private ImageView imatge;
    private Button boto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m13_camera_intent);

        boto = (Button) findViewById(R.id.botoFoto);
        imatge = (ImageView) findViewById(R.id.imageView1);

        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(M12_CameraIntentActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMIS_CAMARA);
                    return;
                }
                fesFoto(view);
            }
        });
    }

    // Número que identifica l'activitat de l'aplicació de fotos
    private static final int APP_CAMERA = 0;
    // Identificador de la imatge que crearà l'aplicació de fotos
    private Uri identificadorImatge;

    private void fesFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, PERMIS_CAPTURA_IMATGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PERMIS_CAPTURA_IMATGE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imatge.setImageBitmap(imageBitmap);
            }
        }
    }
}