package com.bariscanyilmaz.musicplayer.model;

public class Song {

    public String name;
    public String path;
    public String album;
    public String artist;

    public Song(String name,String path, String album, String artist){
        this.name=name;
        this.album=album;
        this.path=path;
        this.artist=artist;
    }


}