package com.example.happsapp2.persistence.BoardGameDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.happsapp2.models.BoardGame;

import java.util.List;

@Dao
public interface BoardGameDao {

    @Insert
    void insertBoardGame(BoardGame boardGame);

    @Query("SELECT * FROM boardGames")
    LiveData<List<BoardGame>> getAllBoardGames();

    @Query("SELECT * FROM boardGames WHERE boardGameID = :boardGame_id;")
    List<BoardGame> getBoardGameWithCustomQuery(int boardGame_id);

    @Query("DELETE FROM boardGames")
    void deleteAllBoardGames();

    @Delete
    int delete(BoardGame boardGame);

    @Update
    int update(BoardGame boardGame);

}
