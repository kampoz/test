package com.kampoz.madiffapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.kampoz.madiffapp.R;
import com.kampoz.madiffapp.SquareImageView;
import java.util.ArrayList;

/**
 * Created by kampoz on 2017-05-22.
 */

public class GifsAdapter extends RecyclerView.Adapter<GifsAdapter.ViewHolder> {

  private ArrayList<String> urls = new ArrayList<>();
  private Context context;

  public static class ViewHolder extends RecyclerView.ViewHolder{
    public SquareImageView gifImageView;

    public ViewHolder(View itemView){
      super(itemView);
      gifImageView = (SquareImageView)itemView.findViewById(R.id.ivGif);
    }
  }

  public GifsAdapter(Context context, ArrayList<String> urls){
    this.urls = urls;
    this.context = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_gif_layout, parent, false);
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

    Glide
        .with(context)
        .load(urls.get(position))
        .into(holder.gifImageView);
  }

  @Override
  public int getItemCount() {
    return urls.size();
  }
}
