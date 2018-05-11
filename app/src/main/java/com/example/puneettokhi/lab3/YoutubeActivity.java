package com.example.puneettokhi.lab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by puneettokhi on 5/11/18.
 */

// this class has a special resource file which is displayed when user clicks on a video
public class YoutubeActivity extends YouTubeBaseActivity {

    // required variables
    private static final String className = YoutubeActivity.class.getSimpleName();
    private String videoID;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_video); // set content layout as player_video.xml file
        //get the video id
        videoID = getIntent().getStringExtra("video_id");  // String.xml file has video id
        youTubePlayerView = findViewById(R.id.youtube_player_view);

        initializeYoutubePlayer();  // calls the startYoutubePlayer method
    }

    // initializes the youtube player using the API key and YoutubePlayer initialized listener

    private void initializeYoutubePlayer() {
        youTubePlayerView.initialize(Resources.API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean wasRestored) {

                //if initialization success then we load the video id to youtube player
                if (!wasRestored) {
                    //setting the player style as Default
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    //loads the video
                    youTubePlayer.loadVideo(videoID);


                }
            }

            // if initialization fails, then log the error message
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Log.e(className, "Youtube Player View initialization failed");
            }
        });
    }

}

