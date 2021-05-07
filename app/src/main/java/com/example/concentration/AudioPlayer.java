package com.example.concentration;

import android.content.Context;
import android.media.MediaPlayer;

/**
 *  AudioPlayer.java
 *  Purpose:            Implements the MediaPlayer class for the user to play and stop music on MainActivity.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;

    // To stop the music
    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    // To play the music
    public void play(Context c) {
        mPlayer = MediaPlayer.create(
                c, R.raw.chugjug);
        mPlayer.start();
    }
}
