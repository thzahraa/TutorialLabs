package com.example.tutorial10;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityVideo extends AppCompatActivity {
    VideoView vidView;
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList
            ("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
             "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
             "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"));
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        vidView = findViewById(R.id.videoView);
        final MediaController controller = new MediaController(this);
        controller.setAnchorView(vidView);
        vidView.setMediaController(controller);
        vidView.setVideoURI(Uri.parse(arrayList.get(index)));
        vidView.requestFocus();

        vidView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(),"Video Over",
                        Toast.LENGTH_SHORT).show();
                if (index++ == arrayList.size()){
                    index = 0;
                    mp.release();
                    Toast.makeText(getApplicationContext(),"Video Over",
                            Toast.LENGTH_SHORT).show();
                }else {
                    vidView.setVideoURI(Uri.parse(arrayList.get(index)));
                    vidView.start();
                }
            }
        });

        vidView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }
}
