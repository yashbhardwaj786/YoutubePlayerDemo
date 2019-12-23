package com.example.zolo13.youtubedemoapp;


public class Album {
    private String name;
    private String playBackURL;

    public Album() {
    }

    public Album(String playBackURL, String name) {
        this.playBackURL = playBackURL;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayBackURL() {
        return playBackURL;
    }

    public void setPlayBackURL(String playBackURL) {
        this.playBackURL = playBackURL;
    }

}
