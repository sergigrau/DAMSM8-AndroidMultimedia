package edu.fje.dam2;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que demostra el funcionament dels m39_fragments amb Android
 * Disposa d'una classe d'utilitat per a facilitar la manipulació de la taula
 * per detectar els canvis afegeix   android:configChanges="orientation|screenSize" a
 * l'activity en el manifest
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */
@SuppressLint("ValidFragment")
public class M17_FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m39_fragments_main);
        // FragmentTransaction transaction = getFragmentManager()
        // .beginTransaction();
        // transaction.add(R.id.fragmentcontainer1, new MyListFragment());
        //
        // transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m17_fragments, menu);
        return true;
    }

    public static class FragmentInterna extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.m17_fragment_detall2,
                    container, false);
            return view;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.afegirLlista:
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.contenidorFragmentLlista, new M17_LlistaFragmentActivity());
                transaction
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
                break;
            case R.id.afegirDetall:
                transaction = manager.beginTransaction();
                transaction.add(R.id.contenidorFragmentDetall, new M17_DetallFragmentActivity());

                transaction.commit();
                break;

            case R.id.canviarDetall:
                Fragment f = new FragmentInterna();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.contenidorFragmentDetall, f);

                transaction.commit();
                break;


            default:
                break;
        }

        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration novaConfiguracio) {
        super.onConfigurationChanged(novaConfiguracio);
        if (novaConfiguracio.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (novaConfiguracio.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

}