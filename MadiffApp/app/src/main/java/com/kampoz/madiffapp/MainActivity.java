package com.kampoz.madiffapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kampoz.madiffapp.adapter.GifsAdapter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

  private GifsAdapter gifsAdapter;
  private ArrayList<String> urls = new ArrayList<>();
  private ProgressDialog dialog;

  //Todo: Add the production key
  private String API_KEY;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if(BuildConfig.DEBUG) {
      API_KEY = "dc6zaTOxFJmzC";
    } else {

    }
    dialog = ProgressDialog.show(MainActivity.this, "",
        "Loading images, please wait...", true);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvGifs);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    recyclerView.setItemAnimator(new DefaultItemAnimator());
    gifsAdapter = new GifsAdapter(this, urls);
    recyclerView.setAdapter(gifsAdapter);

    StringRequest stringRequest = new StringRequest(String.format("http://api.giphy.com/v1/gifs/trending?limit=20&api_key=%s", API_KEY),
        new Listener<String>() {
          @Override
          public void onResponse(String response) {

            Log.d("on Response", response);

            try {
              JSONObject jsonResponse = new JSONObject(response);
              JSONArray dataArray = jsonResponse.optJSONArray("data");
              for (int i = 0; i < dataArray.length(); i++) {
                JSONObject gifObject = dataArray.getJSONObject(i);
                JSONObject gifImagesObject = gifObject.getJSONObject("images");
                JSONObject gifFixedHeightObject = gifImagesObject.getJSONObject("fixed_height");
                String gifUrlString = gifFixedHeightObject.getString("url");

                urls.add(gifUrlString);
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
            gifsAdapter.notifyDataSetChanged();

            dialog.dismiss();
          }
        }, new ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        dialog.dismiss();
      }
    });
    VolleySingleton.getInstance(MainActivity.this).getRequestQueue().add(stringRequest);
  }

}
