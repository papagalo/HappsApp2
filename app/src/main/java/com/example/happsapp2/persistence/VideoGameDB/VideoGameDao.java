package com.example.happsapp2.persistence.VideoGameDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.happsapp2.models.VideoGame;

import java.util.List;

@Dao
public interface VideoGameDao {

    @Insert
    void insertVideoGame(VideoGame videoGame);

    @Query("SELECT * FROM videoGames")
    LiveData<List<VideoGame>> getAllVideoGames();

    @Query("SELECT * FROM videoGames WHERE videoGameID = :videoGame_id;")
    List<VideoGame> getVideoGameWithCustomQuery(int videoGame_id);

    @Query("DELETE FROM videoGames")
    void deleteAllVideoGames();

    @Delete
    int delete(VideoGame videoGame);

    @Update
    int update(VideoGame videoGame);

}
