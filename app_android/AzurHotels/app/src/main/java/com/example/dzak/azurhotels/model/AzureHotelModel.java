package com.example.dzak.azurhotels.model;

import java.util.ArrayList;

/**
 * Created by dzak on 19/02/16.
 */
public class AzureHotelModel {

    private ArrayList<Hotel> hotels = new ArrayList<Hotel>();
    private User user = null;

    /**
     * Ajoute un hotel dans la liste d'hotels du model
     * @param id
     * @param nom
     * @param tel
     * @param description
     * @param prix
     */
    public void addHotel(int id, String nom, String tel, String description, float prix){
        hotels.add(new Hotel(id, nom, tel, description, prix));
    }

    /**
     * Nettoie la liste d'hotels
     */
    public void resetHotel(){
        hotels.clear();
    }

    /**
     * Accesseur de la liste d'hotels
     * @return
     */
    public ArrayList<Hotel> getHotels(){
        return hotels;
    }

    /**
     * Remplace les hotels pour une autre liste d'hotels
     * @param hotels
     */
    public void replaceHotels(ArrayList<Hotel> hotels){
        this.hotels = hotels;
    }

    // * Partie user

    /**
     * Deconnecte l'utilisateur de l'application
     * @return Retourne true si la deconnection à bien eu lieu sinon false
     */
    public boolean deconnectUser(){
        boolean deconnect = false;
        if (user != null){
            user = null;
            deconnect = true;
        }
        return deconnect;
    }

    /**
     * Methode pour connecter un utilisateur à l'application
     * @param mail Mail de l'utilisateur
     * @param nom Nom de l'utilisateur
     * @param prenom Penom de l'utilisateur
     */
    public void connectUser(String mail, String nom, String prenom){
        user = new User(mail, nom, prenom);
    }
}
