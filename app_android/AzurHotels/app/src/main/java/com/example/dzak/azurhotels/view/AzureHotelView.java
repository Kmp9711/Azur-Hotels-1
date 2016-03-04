package com.example.dzak.azurhotels.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.dzak.azurhotels.FormRequest;
import com.example.dzak.azurhotels.R;
import com.example.dzak.azurhotels.listener.FormListener;
import com.example.dzak.azurhotels.listener.MenuListener;
import com.example.dzak.azurhotels.model.Hotel;

import java.util.ArrayList;

/**
 * Created by dzak on 20/02/16.
 */
public class AzureHotelView {

    public Activity activity;
    public FormRequest request;

    String[] titleTexte = {"Menu principale", "Les hotels"};

    ListView lv_hotels;
    ListViewHotelAdapter mAdapter;

    MenuListener menuListener;

    public AzureHotelView(Activity activity){
        this.activity = activity;
    }

    public void setMenuListener(MenuListener listener){
        menuListener = listener;
    }

    public void afficheMenu(){
        // Change layout
        switchPage(0);

        // Btn listener
        Button btn_hotels = (Button) activity.findViewById(R.id.btn_hotels);
        btn_hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuListener.onMenuClick();
            }
        });
    }

    public void afficheLesHotels(ArrayList<Hotel> lesHotels){
        // Change layout
        switchPage(1);

        // Display list
        mAdapter = new ListViewHotelAdapter(activity, lesHotels);
        lv_hotels = (ListView) activity.findViewById(R.id.lv_hotels);
        lv_hotels.setAdapter(mAdapter);
    }

    public void afficheLogin(){
        request = new FormRequest();
        String mail = "";
        String mdp = "";
        request.data.put("mail", mail);
        request.data.put("mdp", mdp);

        FormListener listener = new FormListener() {
            @Override
            public void onSend() {
                ArrayList<String> fields = new ArrayList<>();
                ArrayList<Object> objects = new ArrayList<>();
                sendRequest(fields, objects);
            }
        };
    }

    public void activeUserBar(){
        // TO DO
    }

    public void setTitle(int index){
        TextView textView = (TextView) activity.findViewById(R.id.session_title);
        textView.setText(titleTexte[index]);
    }

    /**
     * Send request
     * @param fields
     * @param values
     */
    public void sendRequest(ArrayList<String> fields, ArrayList<Object> values){
        request = new FormRequest();

        for(int i = 0; i < fields.size(); i++){
            request.data.put(fields.get(i), values.get(i));
        }
    }

    public void switchPage(int index){
        // Switch page
        ViewSwitcher switcher = (ViewSwitcher) activity.findViewById(R.id.viewSwitcher);
        switcher.setDisplayedChild(index);
        // Set title session
        setTitle(index);
    }

}
