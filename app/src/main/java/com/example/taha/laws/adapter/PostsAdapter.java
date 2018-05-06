package com.example.taha.laws.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.taha.laws.data.Post;
import com.example.taha.laws.R;

import java.util.List;

import config.AppController;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private LayoutInflater inflater;
    private List<Post> post;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PostsAdapter (Context context, List<Post> post){
        inflater = LayoutInflater.from(context);
        this.post = post;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return ViewHolder with card information
     */
    @Override
    public PostsAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) inflater.inflate(R.layout.post_card, parent,false);
        return new PostViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position){
        CardView cardView = holder.cardView;
        Post current = post.get(position);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbnail = cardView.findViewById(R.id.post_card_thumbnail);
        //image view bind
            thumbnail.setImageUrl(post.get(position).getImageID(), imageLoader);
        //text view bind
        TextView titleTextView = cardView.findViewById(R.id.post_card_title);
        TextView nameTextView = cardView.findViewById(R.id.post_card_name);
        TextView timeTextView = cardView.findViewById(R.id.post_card_time);

        titleTextView.setText(post.get(position).getTitle());
        nameTextView.setText(String.format("%s %s", post.get(position).getUser().getFirstName(), post.get(position).getUser().getLastName()));
        timeTextView.setText(post.get(position).getCreateDate());

    }

    // Determine how many card view to generate
    @Override
    public int getItemCount(){ return post.size(); }

    // inner class view holder contains card view
    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        private PostViewHolder(CardView cardview) {
            super(cardview);
            cardView = cardview;
        }
    }

}