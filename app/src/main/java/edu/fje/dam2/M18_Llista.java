package edu.fje.dam2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
/**
 * Classe que controla la llista on es mostren els artistes
 * @version 5.0 27.01.2020
 */

public class M18_Llista  extends ArrayAdapter<M18_Artista> {
    private Activity context;
    List<M18_Artista> artistes;

    public M18_Llista(Activity context, List<M18_Artista> artistes) {
        super(context, R.layout.m18_llista, artistes);
        this.context = context;
        this.artistes = artistes;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.m18_llista, null, true);

        TextView nom =  listViewItem.findViewById(R.id.nom);
        TextView genere = listViewItem.findViewById(R.id.genere);

        M18_Artista artist = artistes.get(position);
        nom.setText(artist.getNom());
        genere.setText(artist.getGenere());

        return listViewItem;
    }
}
