package com.example.puneettokhi.lab3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;

/**
 * Created by puneettokhi on 5/11/18.
 */

// holder class which extends RecyclerView.ViewHolder
public class YoutubeVideoHolder extends RecyclerView.ViewHolder {

    // required variables
    public YouTubeThumbnailView videoThumbnailView;
    public TextView videoTitle, videoDuration;

    // constructor
    public YoutubeVideoHolder(View itemView) {
        super(itemView);

        // gets the variables using id's from xml file
        videoThumbnailView = itemView.findViewById(R.id.player_view);
        videoTitle = itemView.findViewById(R.id.video_title_label);
        videoDuration = itemView.findViewById(R.id.video_duration_label);
    }
}