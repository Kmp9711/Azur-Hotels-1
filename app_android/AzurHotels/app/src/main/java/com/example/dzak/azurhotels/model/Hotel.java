package com.example.dzak.azurhotels.model;

import java.text.DecimalFormat;

/**
 * Created by dzak on 19/02/16.
 */
public class Hotel {

    private int id;
    private String nom;
    private String tel;
    private String cp;
    private String adresse;
    private String description;
    private float rate;
    private float prix;

    public Hotel(int id, String nom, String tel, String cp, String adresse, String description, float rank, float prix){
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.cp = cp;
        this.adresse = adresse;
        this.description = description;

        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormat dfRank = new DecimalFormat("#.#");

        this.rate = Float.parseFloat(dfRank.format(rank));
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

    public String getCp() {
        return cp;
    }

    public String getAdresse() {
        return adresse;
    }

    public float getRate() {
        return rate;
    }
}
