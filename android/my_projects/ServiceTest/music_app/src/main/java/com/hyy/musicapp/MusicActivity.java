package com.hyy.musicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    private Button btn_main_play;
    private Button btn_main_stop;
    private Button btn_main_pause;
    private Button btn_main_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        btn_main_play = (Button) findViewById(R.id.btn_main_play);
        btn_main_stop = (Button) findViewById(R.id.btn_main_stop);
        btn_main_pause = (Button) findViewById(R.id.btn_main_pause);
        btn_main_exit = (Button) findViewById(R.id.btn_main_exit);

        btn_main_play.setOnClickListener((v) -> playMusic());
        btn_main_stop.setOnClickListener((v) -> stopMusic());
        btn_main_pause.setOnClickListener((v) -> pauseMusic());
        btn_main_exit.setOnClickListener((v) -> exitMusic());
    }

    private MediaPlayer player;

    private void exitMusic() {
        stopMusic();
        finish();
    }

    /*
     * 暂停音乐
     */
    private void pauseMusic() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    private void stopMusic() {
        if (player != null) {
            player.stop();//停止
            player.reset();//重置
            player.release();//释放资源
            player = null;//赋空
        }
    }

    /**
     * 播放音乐
     */
    private void playMusic() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.water_hander);
        }
        player.start();
    }
}
