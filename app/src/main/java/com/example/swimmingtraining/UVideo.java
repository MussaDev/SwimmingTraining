package com.example.swimmingtraining;

public class UVideo {

    public String name_video;
    public String url;


    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public UVideo() {
    }

    public UVideo(String name_video, String url) {
        this.name_video = name_video;
        this.url = url;
    }

    public String getName_video() {
        return name_video;
    }
    public String getUrl() {
        return url;
    }
}

