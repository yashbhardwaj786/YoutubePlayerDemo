package com.example.zolo13.youtubedemoapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import static com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import static com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import static com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import static com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import static com.google.android.youtube.player.YouTubePlayer.Provider;

public class BasicPlayerActivity extends YouTubeBaseActivity implements OnInitializedListener {
    public static final String API_KEY = Config.DEVELOPER_KEY;

    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public String video_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_basic_player);
        video_id = getIntent().getStringExtra("video_id");
        // Initializing YouTube player view
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(null== player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(video_id);
        }

        // Add listeners to YouTubePlayer instance
        player.setPlayerStateChangeListener(new PlayerStateChangeListener() {
            @Override public void onAdStarted() { }
            @Override public void onError(ErrorReason arg0) { }
            @Override public void onLoaded(String arg0) { }
            @Override public void onLoading() { }
            @Override public void onVideoEnded() { }
            @Override public void onVideoStarted() { }
        });


        player.setPlaybackEventListener(new PlaybackEventListener() {
            @Override public void onBuffering(boolean arg0) { }
            @Override public void onPaused() { }
            @Override public void onPlaying() { }
            @Override public void onSeekTo(int arg0) { }
            @Override public void onStopped() { }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {


        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }

}