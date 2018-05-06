package com.example.taha.laws.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taha.laws.R;

public class HarvestedPlantsAdapter extends RecyclerView.Adapter<HarvestedPlantsAdapter.ViewHolder>{
    private String[] captions;
    private int[] imageIds;



    public HarvestedPlantsAdapter(String[] captions, int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }

    // Determine how many card view to generate
    @Override
    public int getItemCount(){ return captions.length; }

    // determine which card to represent
    @Override
    public HarvestedPlantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.harvested_plants_card, parent, false);
        return new HarvestedPlantsAdapter.ViewHolder(cv);
    }

    // bind data
    @Override
    public void onBindViewHolder(HarvestedPlantsAdapter.ViewHolder holder, int position){
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.harvested_imageView);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);

        //image captions
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);

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