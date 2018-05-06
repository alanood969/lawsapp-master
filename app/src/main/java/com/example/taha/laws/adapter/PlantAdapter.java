package com.example.taha.laws.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taha.laws.R;
import com.example.taha.laws.data.plants;
import com.example.taha.laws.userinterface.PlantInfoActivity;

import java.util.List;

//Plant adapter class -> take data from plants class -> put them into plants activities
public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder>{

    private Listener listener;
    private plants[] plantsList;

    // on click
    public interface Listener {
        void onClick(int position);
    }

    // constructor
    public PlantAdapter(Context context , plants[] plantsList){
        this.plantsList = plantsList;
    }

    // detemine how many card view to generate
    @Override
    public int getItemCount(){
        return plantsList.length;
    }

    @Override
    public PlantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_card, parent, false);
        return new ViewHolder(cv);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }


    // Bind data
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position){
        final CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.info_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), plantsList[position].getImageResourceId());

        //image view bind
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(plantsList[position].getName());

        //text view bind
        TextView plantTextView = cardView.findViewById(R.id.plant_text);
        TextView infoTextView = cardView.findViewById(R.id.info_text);

        plantTextView.setText(plantsList[position].getName());
        infoTextView.setText(plantsList[position].getName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }



    // inner class view holder contains card view
    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}