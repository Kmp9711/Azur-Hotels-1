package com.example.dzak.azurhotels.controller;

import android.util.Log;

import com.example.dzak.azurhotels.FormRequest;
import com.example.dzak.azurhotels.MVCPattern;
import com.example.dzak.azurhotels.listener.MenuListener;
import com.example.dzak.azurhotels.model.Hotel;
import com.example.dzak.azurhotels.webservice.RequestAction;
import com.example.dzak.azurhotels.webservice.RequestListener;
import com.example.dzak.azurhotels.webservice.RequestPost;
import com.example.dzak.azurhotels.webservice.RequestTask;

/**
 * Created by dzak on 19/02/16.
 */
public class AzureHotelController {

    public AzureHotelController(){

        // Listener
        MenuListener menuListener = new MenuListener() {
            @Override
            public void onMenuClick() {
                getHotels();
            }
        };

        MVCPattern.view.setMenuListener(menuListener);
    }

    public void getMenu(){

        MVCPattern.view.afficheMenu();
    }

    public void getHotels(){
        // Get data
        RequestListener listener = new RequestListener() {
            @Override
            public void whenFinish() {
                MVCPattern.view.afficheLesHotels(MVCPattern.model.getHotels());
            }
        };

        RequestTask request = new RequestTask(listener);
        request.execute(RequestAction.ws_url_get + "&var=get_hotels");
    }

    public void getHotelProfil(Hotel hotel){
        MVCPattern.view.afficheHotelProfil(hotel);
    }

    public void getLogin(){
        MVCPattern.view.afficheLogin();
    }

    public void loginProcess(final FormRequest formRequest){
        RequestListener listener = new RequestListener() {
            @Override
            public void whenFinish() {
                // Connexion
                String mail = formRequest.data.get("mail").toString();
                String nom = formRequest.data.get("nom").toString();
                String prenom = formRequest.data.get("prenom").toString();

                MVCPattern.model.connectUser(mail, nom, prenom);
                // Test redirection au menu
                Log.i("Connexion", "Vous êtes connecté(e)");
            }
        };

        RequestPost request = new RequestPost(formRequest, listener);
        request.execute(RequestAction.ws_url_post + "&var=req_login");
    }
}
