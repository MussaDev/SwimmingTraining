package com.example.swimmingtraining;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class WhatchVideoToSsilka extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatch_video_to_ssilka);
        // установите свой путь к файлу на SD-карточке
        String videoSource ="https://firebasestorage.googleapis.com/v0/b/swimmingtraining-e7688.appspot.com/o/20170122_230400.mp4?alt=media&token=1053455a-a8f1-4ac4-9e0f-64149276fdf2";
        VideoView videoView = (VideoView) findViewById(R.id.videoView2);
        videoView.setVideoURI(Uri.parse(videoSource));
        videoView.setVideoPath(videoSource);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus(0);
        videoView.start(); // начинаем воспроизведение автоматически
    }
}
