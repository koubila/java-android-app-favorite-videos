package com.itakademy.mybestyoutube.adapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import com.itakademy.mybestyoutube.R;
import com.itakademy.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;
public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo youtubeVideo);
    }
    private List<YoutubeVideo> youtubeVideos;
    private final OnItemClickListener listener;

    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitre;
        public TextView tvDescription;
        public TextView tvUrl;
        public TextView tvCategorie;

        public YoutubeVideoViewHolder(View itemView){
            super(itemView);
            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
           // tvUrl = itemView.findViewById(R.id.tvUrl);
          //  tvCategorie = itemView.findViewById(R.id.tvCategorie);
        }
        public void bind(final YoutubeVideo youtubeVideo, final OnItemClickListener listener) {
            //tvTitre.setText(youtubeVideo.tvTitre);
            //Picasso.with(itemView.getContext()).load(youtubeVideo.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(youtubeVideo);
                }
            });
        }
    }

    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos, OnItemClickListener listener){
        this.youtubeVideos = youtubeVideos;
        this.listener = listener;
    }

    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtubevideo_item, parent, false);
        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeVideoViewHolder holder, int position) {
        YoutubeVideo youtubeVideo = youtubeVideos.get(position);
        holder.tvTitre.setText(youtubeVideo.getTitre());
        holder.tvDescription.setText(youtubeVideo.getCategorie());
        holder.bind(youtubeVideos.get(position), listener);
       // holder.tvUrl.setText(youtubeVideo.getDescription());
        //holder.tvCategorie.setText(youtubeVideo.getDescription());

    }

    @Override
    public int getItemCount() { return youtubeVideos.size();}
}
