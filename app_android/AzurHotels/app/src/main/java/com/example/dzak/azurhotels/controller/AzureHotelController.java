package com.example.dzak.azurhotels.controller;

import com.example.dzak.azurhotels.MVCPattern;
import com.example.dzak.azurhotels.listener.MenuListener;
import com.example.dzak.azurhotels.webservice.RequestAction;
import com.example.dzak.azurhotels.webservice.RequestListener;
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

    public void getLogin(){
        // TO DO
    }

    public void loginProcess(){
        // Get data
        RequestListener listener = new RequestListener() {
            @Override
            public void whenFinish() {
                MVCPattern.view.activeUserBar();
            }
        };

        // Il serais préférable d'utuliser un post pour recupere l'utilsateur via la requete sql
        // avec les champs mail et mdp
        //RequestTask request = new RequestTask(listener);
    }
}
