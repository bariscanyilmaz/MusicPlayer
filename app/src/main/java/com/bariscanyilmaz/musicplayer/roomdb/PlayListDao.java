package com.bariscanyilmaz.musicplayer.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bariscanyilmaz.musicplayer.model.PlayList;

import java.util.List;

@Dao
public interface PlayListDao {

    @Insert
    void insert(PlayList user);

    @Query("SELECT * FROM PlayList")
    List<PlayList> getAll();
}
