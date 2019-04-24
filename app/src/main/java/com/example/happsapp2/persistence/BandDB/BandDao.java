package com.example.happsapp2.persistence.BandDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.happsapp2.models.Band;

import java.util.List;

@Dao
public interface BandDao {

    @Insert
    void insertBand(Band band);

    @Query("SELECT * FROM band;")
    LiveData<List<Band>> getAllBands();

    /*@Query("SELECT * FROM band WHERE bandName = :bandName;")
    List<Band> getBandWithCustomQuery(String bandName);*/

    @Query("DELETE FROM band")
    void deleteAllBands();

    @Delete
    int delete(Band band);

    @Update
    int update(Band band);

}
