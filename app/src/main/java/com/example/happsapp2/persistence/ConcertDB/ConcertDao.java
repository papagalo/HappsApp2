package com.example.happsapp2.persistence.ConcertDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.happsapp2.models.Concert;

import java.util.List;

@Dao
public interface ConcertDao {

    @Insert
    void insertConcert(Concert concert);

    @Query("SELECT * FROM concerts")
    LiveData<List<Concert>> getAllConcerts();

    @Query("SELECT * FROM concerts WHERE concertID = :concert_id;")
    List<Concert> getConcertWithCustomQuery(int concert_id);

    @Delete
    int delete(Concert concert);

    @Update
    int update(Concert concert);

}
