package com.pmdm.audiofrominternet;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

/*
    <uses-permission android:name="android.permission.INTERNET"/>
 */
public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int arg1, int arg2) {
                Toast.makeText(MainActivity.this, "Espere un momento mientras se carga el mp3", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void ejecutar(View v) {
        try {
            mp.setDataSource("http://www.codigofuenteya.com.ar/recursos/gato.mp3");
            mp.prepareAsync();
        } catch (IOException e) { }
        Toast.makeText(this, "Espere un momento mientras se carga el mp3",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null;
        }
    }

}
