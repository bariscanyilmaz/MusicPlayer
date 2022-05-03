package com.bariscanyilmaz.musicplayer.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bariscanyilmaz.musicplayer.model.PlayList;
import com.bariscanyilmaz.musicplayer.model.User;

@Database(entities = {User.class, PlayList.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract PlayListDao playListDao();


}
