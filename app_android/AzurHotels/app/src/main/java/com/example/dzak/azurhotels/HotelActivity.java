package com.example.dzak.azurhotels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dzak.azurhotels.view.ListViewHotelAdapter;

public class HotelActivity extends AppCompatActivity {

    ListViewHotelAdapter mAdapter;
    AdapterView lv_hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        View view = findViewById(R.id.hotel_layout);

        mAdapter = new ListViewHotelAdapter(this, MVCPattern.model.getHotels());
        lv_hotels = (ListView) view.findViewById(R.id.lv_hotels);
        lv_hotels.setAdapter(mAdapter);

        MVCPattern.currentActivity = this;
        MVCPattern.changeActivity();

    }
}
