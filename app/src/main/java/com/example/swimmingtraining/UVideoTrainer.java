package com.example.swimmingtraining;

public class UVideoTrainer {

    public String data_video;
    public String author;
    public String name_video;
    public String url;


    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public UVideoTrainer() {
    }

    public UVideoTrainer(String data_video, String author, String name_video, String url) {
        this.data_video = data_video;
        this.author = author;
        this.name_video = name_video;
        this.url = url;
    }

    public String getData_video() { return data_video; }
    public String getAuthor() {
        return author;
    }
    public String getName_video() {
        return name_video;
    }
    public String getUrl() {
        return url;
    }
}

