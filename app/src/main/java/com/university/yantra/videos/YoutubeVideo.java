package com.university.yantra.videos;

/**
 * Created by rahul on 20/2/18.
 */

public class YoutubeVideo {

    int id;
    String YouTubeId, title;

    public YoutubeVideo(String youTubeId) {
        YouTubeId = youTubeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public YoutubeVideo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYouTubeId() {
        return YouTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        YouTubeId = youTubeId;
    }
}
