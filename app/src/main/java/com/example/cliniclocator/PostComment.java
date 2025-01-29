package com.example.cliniclocator;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostComment {

    // Method to send a POST request
    public void sendPost(String username, String email, String comments, String location, Context ctx, MainActivity main) {
        RequestQueue queue = Volley.newRequestQueue(ctx);

        // URL of the API
        String url = "http://192.168.0.12/comments/api.php";

        // Create a StringRequest
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> main.showToast("Response is: " + response),
                error -> main.showToast ("Error: " + error.toString())){

            @Override
            protected Map<String, String> getParams() {
                // Add parameters to the request
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("comments", comments);
                params.put("location", location);
                return params;
            }
        };

        // Add the request to the queue
        queue.add(postRequest);
    }



}
