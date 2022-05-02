package com.bariscanyilmaz.musicplayer.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bariscanyilmaz.musicplayer.model.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
