package com.example.dzak.azurhotels.model;

/**
 * Created by dzak on 26/02/16.
 */
public class User {

    private String mail;
    private String prenom;
    private String nom;

    public User(String mail, String nom, String prenom){
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }
    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

}
