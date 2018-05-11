package com.example.puneettokhi.lab3;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

/**
 * Created by puneettokhi on 5/6/18.
 */

// creating RecyclerAdapter class which extends from RecyclerView.Adapter
public class RecyclerAdapter extends RecyclerView.Adapter<YoutubeVideoHolder> {

        // required variables
        private static final String className = RecyclerAdapter.class.getSimpleName();
        private Context context;
        private ArrayList<Videos> videoList;

        // constructor with 2 arguments
        public RecyclerAdapter(Context context, ArrayList<Videos> youtubeVideoList) {
            this.context = context;
            this.videoList = youtubeVideoList;
        }

        // returns an instance of holder class "YoutubeVideoHolder" with the updated view when view is loaded
        @Override
        public YoutubeVideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.card_video, parent, false);
            return new YoutubeVideoHolder(view);  // returns the instance with updated view
        }

        // This method is used to update the contents of the itemView to reflect the item at the given position
        @Override
        public void onBindViewHolder(YoutubeVideoHolder holder, final int position) {

            final Videos youtubeVideos = videoList.get(position);

            holder.videoTitle.setText(youtubeVideos.getTitle());
            holder.videoDuration.setText(youtubeVideos.getVideoDuration());


        // using the API developer key in Resources class to initialize the thumbnail image view
            holder.videoThumbnailView.initialize(Resources.API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    //when initialization is success, set the video id to thumbnail to load
                    youTubeThumbnailLoader.setVideo(youtubeVideos.getVideoId());

                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                        @Override
                        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                            //when thumbnail loaded successfully release the thumbnail loader as we show thumbnail in adapter
                            youTubeThumbnailLoader.release();  // releases
                        }

                        // if there is an error in thumbnail loading, just log the error message
                        @Override
                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                            //print or show error when thumbnail load failed
                            Log.e(className, "Youtube Thumbnail Error");
                        }
                    });
                }

                // if fail to initialize thumbnail, we log the error message
                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    //print or show error when initialization failed
                    Log.e(className, "Youtube Initialization Failure");

                }
            });

        }

        // this method shows the items on screen based on items in arraylist
        @Override
        public int getItemCount() {

            if(videoList != null){
                return videoList.size();
            }
            else{
                return 0;
            }

        }
    }

