package com.kampoz.madiffapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by kampoz on 2017-05-22.
 */

public class VolleySingleton {
  private static VolleySingleton mInstance = null;
  private RequestQueue mRequestQueue;

  private VolleySingleton(Context context){

    mRequestQueue = Volley.newRequestQueue(context);
  }

  public static VolleySingleton getInstance(Context context){
    if(mInstance == null){
      mInstance = new VolleySingleton(context);
    }
    return mInstance;
  }

  public RequestQueue getRequestQueue(){
    return this.mRequestQueue;
  }

}
