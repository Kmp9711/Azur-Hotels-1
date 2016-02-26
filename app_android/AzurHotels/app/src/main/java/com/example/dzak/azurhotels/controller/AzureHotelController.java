package com.example.dzak.azurhotels.controller;

import com.example.dzak.azurhotels.MVCPattern;
import com.example.dzak.azurhotels.listener.MenuListener;
import com.example.dzak.azurhotels.model.AzureHotelModel;
import com.example.dzak.azurhotels.view.AzureHotelView;
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

        RequestTask request = new RequestTask(RequestAction.GET_HOTELS, listener);
        request.execute("http://10.0.2.2/azure_app/webservice/api.php?action=get&var=temp");
    }
}
