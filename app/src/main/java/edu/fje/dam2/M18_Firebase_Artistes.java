package edu.fje.dam2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity que accedeix a una base de dades de FireBase sense autenticació
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */
public class M18_Firebase_Artistes extends AppCompatActivity {

    public static final String NOM = "dam2.fje.edu.nom";
    public static final String ID = "dam2.fje.edu.id";

    EditText nom;
    Spinner genere;
    Button afegirArtista;
    ListView llistaArtistes;

    List<M18_Artista> artistes;

    DatabaseReference DBArtistes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m18_firebase);

        DBArtistes = FirebaseDatabase.getInstance().getReference("artistes");

        nom =  findViewById(R.id.editNom);
        genere =  findViewById(R.id.generes);
        llistaArtistes =  findViewById(R.id.llistaArtistes);

        afegirArtista =  findViewById(R.id.afegirArtistes);

        artistes = new ArrayList<>();

        afegirArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afegirArtista();
            }
        });


        llistaArtistes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                M18_Artista artist = artistes.get(i);
                Intent intent = new Intent(getApplicationContext(), M18_Firebase_Artistes.class);
                intent.putExtra(ID, artist.getId());
                intent.putExtra(NOM, artist.getNom());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DBArtistes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistes.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    M18_Artista artista = postSnapshot.getValue(M18_Artista.class);
                    artistes.add(artista);
                }

                M18_Llista artistaAdapter = new M18_Llista(M18_Firebase_Artistes.this, artistes);
                llistaArtistes.setAdapter(artistaAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void afegirArtista() {
        String nom = this.nom.getText().toString().trim();
        String genere = this.genere.getSelectedItem().toString();

        if (!TextUtils.isEmpty(nom)) {

            String id = DBArtistes.push().getKey();

            M18_Artista artista = new M18_Artista(id, nom, genere);

            DBArtistes.child(id).setValue(artista);

            this.nom.setText("");

            Toast.makeText(this, "Artista afegit", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Cal entrar un nom", Toast.LENGTH_LONG).show();
        }
    }
}