package com.example.puneettokhi.lab3;

/**
 * Created by puneettokhi on 5/11/18.
 */

// this class has  attributes id,title and videoDuration which is called in another classes
public class Videos {

    private String videoId, title, videoDuration;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    // overriding the toString method
    @Override
    public String toString() {
        return "YoutubeVideoModel{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", videoDuration='" + videoDuration + '\'' +
                '}';
    }
}
