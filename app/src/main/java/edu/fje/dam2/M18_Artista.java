package edu.fje.dam2;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Classe Entitat que representa un Artista
 * @version 5.0 27.01.2020
 */

@IgnoreExtraProperties
public class M18_Artista {


    private String id;
    private String nom;
    private String genere;

    public M18_Artista(){
    }

    public M18_Artista(String id, String nom, String genere) {
        this.id = id;
        this.nom = nom;
        this.genere = genere;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getGenere() {
        return genere;
    }
}