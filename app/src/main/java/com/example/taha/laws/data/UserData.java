package com.example.taha.laws.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import config.AppConfig;
import config.AppController;

import static config.AppController.TAG;

public class UserData {
    private Users userentity = new Users();

    public Users GetUserById (Users u)
    {
        userentity.setID(u.getID());
        // Tag used to cancel the request
        String tag_string_req = "req_getuserbyid";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GETUSERBYID, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "User Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // function success
                        JSONObject obj = jObj.getJSONObject("user");
                        userentity.setID(obj.getInt("user_id"));
                        userentity.setUsername(obj.getString("username"));
                        userentity.setFirstName(obj.getString("first_name"));
                        userentity.setLastName(obj.getString("last_name"));
                        userentity.setEmail(obj.getString("email"));
                        userentity.setPhone(obj.getString("phone"));
                        userentity.setAge(obj.getString("age"));
                        userentity.setLongitude(obj.getString("longitude"));
                        userentity.setLatitude(obj.getString("latitude"));
                        userentity.setImageId(obj.getString("image"));

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        //Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", Integer.toString(userentity.getID()));
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return userentity;
    }
}
