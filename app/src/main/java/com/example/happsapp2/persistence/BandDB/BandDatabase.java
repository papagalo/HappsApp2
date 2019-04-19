package com.example.happsapp2.persistence.BandDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.happsapp2.models.Band;

@Database(entities = {Band.class}, version = 1)
public abstract class BandDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "band_database";

    private static BandDatabase instance;

    static BandDatabase getInstance(final Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BandDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract BandDao getBandDAO();

}
