package com.example.dzak.azurhotels.model;

import java.text.DecimalFormat;

/**
 * Created by dzak on 19/02/16.
 */
public class Hotel {

    private int id;
    private String nom;
    private String tel;
    private String description;
    private float prix;

    public Hotel(int id, String nom, String tel, String description, double prix){
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.description = description;

        DecimalFormat df = new DecimalFormat("#.##");
        this.prix = Float.parseFloat(df.format(prix));
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }
}
