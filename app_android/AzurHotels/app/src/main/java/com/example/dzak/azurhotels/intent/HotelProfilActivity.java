package com.example.dzak.azurhotels.intent;

import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dzak.azurhotels.R;

public class HotelProfilActivity extends AppCompatActivity {

    TextView txv_hotel_nom;
    TextView txv_hotel_price;
    RatingBar rtb_hotel_rank;
    TextView txv_hotel_tel;
    TextView txv_hotel_adresse;
    TextView txv_hotel_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_profil);

        // Récupère les vues
        txv_hotel_nom = (TextView) findViewById(R.id.txv_hotel_nom);
        txv_hotel_price = (TextView) findViewById(R.id.txv_hotel_price);
        rtb_hotel_rank = (RatingBar) findViewById(R.id.rtb_hotel);
        txv_hotel_tel = (TextView) findViewById(R.id.txv_hotel_tel);
        txv_hotel_adresse = (TextView) findViewById(R.id.txv_hotel_adresse);
        txv_hotel_description = (TextView) findViewById(R.id.txv_hotel_description);

        // Assigne les champ
        txv_hotel_nom.setText(getIntent().getStringExtra("nom"));
        txv_hotel_price.setText("" + getIntent().getFloatExtra("price", 0.0f) + "€");

            // Rating
        rtb_hotel_rank.setRating(getIntent().getFloatExtra("rank", 2.6f));

        txv_hotel_tel.setText(getIntent().getStringExtra("tel"));

        String adresse = getIntent().getStringExtra("cp") + " - " + getIntent().getStringExtra("adresse");
        txv_hotel_adresse.setText(adresse);

        txv_hotel_description.setText(getIntent().getStringExtra("description"));
    }
}
