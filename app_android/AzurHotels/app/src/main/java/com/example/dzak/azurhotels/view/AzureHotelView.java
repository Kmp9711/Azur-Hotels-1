package com.example.dzak.azurhotels.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dzak.azurhotels.FormRequest;
import com.example.dzak.azurhotels.HotelActivity;
import com.example.dzak.azurhotels.LoginActivity;
import com.example.dzak.azurhotels.MVCPattern;
import com.example.dzak.azurhotels.R;
import com.example.dzak.azurhotels.listener.MenuListener;
import com.example.dzak.azurhotels.listener.ViewListener;
import com.example.dzak.azurhotels.model.Hotel;

import java.util.ArrayList;

/**
 * Created by dzak on 20/02/16.
 */
public class AzureHotelView {

    public Activity activity;
    public Intent intent;
    public FormRequest request;

    String[] titleTexte = {"Menu principale", "Les hotels", "Connexion"};

    MenuListener menuListener;

    public AzureHotelView(Activity activity){
        this.activity = activity;
    }

    public void setMenuListener(MenuListener listener){
        menuListener = listener;
    }

    public void afficheMenu(){
        // Change activity intention
//        Intent menu_intent = new Intent(activity, MenuActivity.class);
//        switchActivity(menu_intent);

        // Btn listener
        View view = activity.findViewById(R.id.layout_menu);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.menu_linear_layout);

        Button btn_hotels = (Button) layout.findViewById(R.id.btn_hotels);
        Button btn_login = (Button) layout.findViewById(R.id.btn_login);


        // Bouton pour appeler le controller hotel
        btn_hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MVCPattern.controller.getHotels();
            }
        });

        // Bouton pour appeler le controller login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MVCPattern.controller.getLogin();
            }
        });
    }

    public void afficheLesHotels(ArrayList<Hotel> lesHotels){
        Intent menu_intent = new Intent(activity, HotelActivity.class);
        switchActivity(menu_intent);
    }

    public void afficheLogin(){
        // Set listener
        MVCPattern.currentListener = new ViewListener() {
            @Override
            public void onActivityHasChange() {
                request = new FormRequest();

                View view = MVCPattern.currentActivity.findViewById(R.id.connexion_layout);

                final EditText edt_mail = (EditText) view.findViewById(R.id.editText4);
                final EditText edt_mdp = (EditText) view.findViewById(R.id.editText5);

                edt_mail.setText("laffont@gmail.com");
                edt_mdp.setText("laffont971");

                Button btn_valider = (Button) view.findViewById(R.id.btn_valider);

                btn_valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Pendant le onclick

                    request.data.put("mail", edt_mail.getText().toString());
                    request.data.put("mdp", edt_mdp);

                    MVCPattern.controller.loginProcess(request);
                    }
                });
            }
        };

        Intent menu_intent = new Intent(activity, LoginActivity.class);
        switchActivity(menu_intent);
    }

    public void activeUserBar(){
        // TO DO
    }

    public void setTitle(int index){
        TextView textView = (TextView) activity.findViewById(R.id.session_title);
        textView.setText(titleTexte[index]);
    }

    public void switchActivity(Intent new_intent){
        activity.startActivity(new_intent);
        intent = new_intent;
    }


}
