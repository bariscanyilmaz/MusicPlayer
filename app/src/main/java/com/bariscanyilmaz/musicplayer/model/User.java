package com.bariscanyilmaz.musicplayer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "fullname")
    private String fullname;


    public User(String email,String password,String fullname){
        this.email=email;
        this.password=password;
        this.fullname=fullname;
    }
}
