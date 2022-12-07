package edu.val.ejemploslibroandroidapp.tema3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;


public class ActividadVideoView extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        this.videoView = findViewById(R.id.videoView);
        this.videoView.setOnCompletionListener(this);
        String ruta_video = "android.resource://" + getPackageName() + "/" + R.raw.videotoros;
        Uri uri_video = Uri.parse(ruta_video);

        this.videoView.setVideoURI(uri_video);
        this.videoView.start();


    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(MainActivity.ETIQUETA_LOG, "Ha finalizado el video");
    }
}