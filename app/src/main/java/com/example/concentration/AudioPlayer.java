package com.example.concentration;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
    private MediaPlayer mPlayer;
    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
    public void play(Context c) {
        mPlayer = MediaPlayer.create(
                c, R.raw.chugjug);
        mPlayer.start();
    }
}
