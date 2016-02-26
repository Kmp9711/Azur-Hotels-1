package com.example.dzak.azurhotels.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by dzak on 19/02/16.
 */
public class AzureHotelModel {

    private ArrayList<Hotel> hotels = new ArrayList<Hotel>();

    public AzureHotelModel(){
    }

    public void resetHotel(){
        hotels.clear();
    }

    public void addHotel(int id, String nom, String tel, String description, float prix){
        hotels.add(new Hotel(id, nom, tel, description, prix));
    }

    public ArrayList<Hotel> getHotels(){
        return hotels;
    }

    public void replaceHotels(ArrayList<Hotel> hotels){
        this.hotels = hotels;
    }
}
