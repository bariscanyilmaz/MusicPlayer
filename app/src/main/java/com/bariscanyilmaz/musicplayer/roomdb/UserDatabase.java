package com.bariscanyilmaz.musicplayer.roomdb;

import androidx.room.RoomDatabase;

public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
