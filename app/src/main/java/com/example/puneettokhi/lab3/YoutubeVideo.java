package com.example.puneettokhi.lab3;

/**
 * Created by puneettokhi on 5/8/18.
 */

// creating class with 2 attributes videoURL and title
public class YoutubeVideo {
    String vidoURL;
    String title;

    // constructor with 2 arguments
    public YoutubeVideo(String vidoURL, String title) {
        this.vidoURL = vidoURL;
        this.title = title;
    }

    // setters and getters
    public String getVidoURL() {
        return vidoURL;
    }

    public void setVidoURL(String vidoURL) {
        this.vidoURL = vidoURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
