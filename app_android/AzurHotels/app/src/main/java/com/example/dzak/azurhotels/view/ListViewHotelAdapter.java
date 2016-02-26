package com.example.dzak.azurhotels.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dzak.azurhotels.R;
import com.example.dzak.azurhotels.model.Hotel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dzak on 20/02/16.
 */
public class ListViewHotelAdapter extends BaseAdapter {
    private Map<String, Bitmap> mBitmapCache = new HashMap<String, Bitmap>();

    private ArrayList<Hotel> listHotel;
    private Context context;

    public ListViewHotelAdapter(Context context, ArrayList<Hotel> objects) {
        super();
        this.listHotel = objects;
        this.context = context;
    }

    class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public ImageDownloader(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            //int width = 320;
            //int height = 240;
            //Bitmap resized = Bitmap.createScaledBitmap(result, width, height, true);
            bmImage.setVisibility(View.VISIBLE);
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public Hotel getItem(int position) {
        return listHotel.get(position);
    }

    @Override
    public int getCount() {
        return listHotel.size();
    }

    @Override
    public long getItemId(int position) {
        return listHotel.get(position).getId();
    }

    // Creation d'une vue customisée
    public class ViewHolder{
        ImageView imageView;
        View rowTextView;
        TextView title;
        TextView price;
        TextView description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;

        // Si la vue n'existe pas on la créer sinon on la recupére
        if (convertView == null){
            convertView = inflater.inflate(R.layout.hotels_main, parent, false);

            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.hotel_icon);


            holder.rowTextView = convertView.findViewById(R.id.layout_hotel_text);
            holder.title = (TextView) convertView.findViewById(R.id.hotel_name);
            holder.price = (TextView) holder.rowTextView.findViewById(R.id.hotel_price);

            holder.description = (TextView) convertView.findViewById(R.id.hotel_description);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Récupere l'objet Hotel
        if ( listHotel.get(position) != null) {
            Hotel hotel = listHotel.get(position);

            // L'id de l'hotel
            int id = hotel.getId();

            // L'url de l'image
            String url = "http://10.0.2.2/azure_app/images/hotels/" + id + ".jpg";

            // On recupére l'image stockée dans un dictionnaire
            Bitmap bitmap = mBitmapCache.get(url);

            // Si l'image existe deja alors on l'assigne immédiatement, sinon on la télécharge
            if (bitmap != null) {
                holder.imageView.setImageBitmap(bitmap);
            } else {
                new ImageDownloader(holder.imageView).execute(url);
            }

            // On assigne les informations
            holder.title.setText(hotel.getNom());
            holder.price.setText(hotel.getPrix() + "€");
            // description
            String description = hotel.getDescription();
            holder.description.setText(description);
        }

        return convertView;
    }

}
