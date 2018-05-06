package com.example.taha.laws.data;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import config.AppController;

import static config.AppConfig.URL_POSTS;
import static config.AppController.TAG;

public class PostData {
    private List<Post> posts = Collections.emptyList();

    public boolean AddPost(){ return false; }

    public boolean EditPost(){ return false; }

    public List<Post> GetPosts() {

        JsonArrayRequest postsReq = new JsonArrayRequest(URL_POSTS,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Post post = new Post();
                                Users user = new Users();
                                JSONObject obj = response.getJSONObject(i);
                                post.setID(obj.getInt("post_id"));
                                post.setTitle(obj.getString("title"));
                                post.setContent(obj.getString("content"));
                                post.setImageID(obj.getString("image"));
                                user.setID(obj.getInt("user_id"));
                                post.setUser(user);
                                post.setCreateDate(obj.getString("create_date"));
                                post.setModifyDate(obj.getString("modify_date"));

                                // adding movie to movies array
                                posts.add(post);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(postsReq);
        return posts;
    }

    public List<Post> GetPostsTest() {
        List<Post> posts = new LinkedList<>();
        Post post = new Post();
        Users user = new Users();
        user.setID(1);
        post.setID(1);
        post.setTitle("title1");
        post.setContent("content");
        post.setUser(user);
        post.setCreateDate("14 april");
        for (int i = 0; i < 5; i++)
        {
            posts.add(post);
        }
        return posts;

    }

    public List<Post> GetPostsByTopic() { return null; }

    public Post GetPostByID() { return null; }
}
