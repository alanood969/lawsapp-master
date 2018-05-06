package com.example.taha.laws.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taha.laws.R;
import com.example.taha.laws.data.Garden;
import com.example.taha.laws.data.Post;
import com.example.taha.laws.data.plants;

import java.util.List;

public class PlantedPlantsAdapter extends RecyclerView.Adapter<PlantedPlantsAdapter.ViewHolder>{


    private LayoutInflater inflater;
    private List<plants> myPlantedPlants;
    private Listener listener;


    // on click
    public interface Listener {
        void onClick(int position);
    }


   public PlantedPlantsAdapter(Context context , List<plants> myPlantedPlants){
       inflater = LayoutInflater.from(context);
       this.myPlantedPlants = myPlantedPlants;
        }

   // Determine how many card view to generate
   @Override
   public int getItemCount(){ return myPlantedPlants.size(); }

   public void setListener(Listener listener){this.listener = listener;}
   @Override
   public PlantedPlantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
       CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.planted_plants_card, parent, false);
        return new ViewHolder(cv);
        }

   @Override
   public void onBindViewHolder(ViewHolder holder, final int position){
       CardView cardView = holder.cardView;
       ImageView imageView = cardView.findViewById(R.id.planted_imageView);
       Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),myPlantedPlants.get(position).getImageResourceId());

       //image view bind
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(myPlantedPlants.get(position).getName());
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
    public static class ViewHolder extends RecyclerView.ViewHolder {
     private CardView cardView;
      public ViewHolder(CardView v) {
        super(v);
        cardView = v;
    }
}






}