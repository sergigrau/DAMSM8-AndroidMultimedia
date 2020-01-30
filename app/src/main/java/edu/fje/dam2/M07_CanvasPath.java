package edu.fje.dam2;

import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Activitat que mostra el funcionament de Canvas,
 * fa ús de Paint i Path
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */
public class M07_CanvasPath extends AppCompatActivity {

    private M07_VistaPaint vistaPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m07_canvas_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vistaPaint = findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        vistaPaint.init(metrics);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.m07_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.normal:
                vistaPaint.normal();
                return true;
            case R.id.emboss:
                vistaPaint.emboss();
                return true;
            case R.id.blur:
                vistaPaint.blur();
                return true;
            case R.id.clear:
                vistaPaint.clear();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
