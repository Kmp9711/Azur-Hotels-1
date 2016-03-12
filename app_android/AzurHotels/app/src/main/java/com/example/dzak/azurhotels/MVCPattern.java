package com.example.dzak.azurhotels;

import android.app.Activity;
import android.util.Log;

import com.example.dzak.azurhotels.controller.AzureHotelController;
import com.example.dzak.azurhotels.listener.ViewListener;
import com.example.dzak.azurhotels.model.AzureHotelModel;
import com.example.dzak.azurhotels.view.AzureHotelView;

/**
 * Created by dzak on 20/02/16.
 */
public class MVCPattern {

    public static final String TAG = "MVCPattern";

    public static Activity currentActivity;
    public static AzureHotelController controller;
    public static AzureHotelModel model;
    public static AzureHotelView view;

    public static ViewListener currentListener;

    public static void changeActivity(){
        if (currentListener != null) {
            currentListener.onActivityHasChange();
            Log.i(TAG, "On a changé d'activité !");
        }
    }

}
