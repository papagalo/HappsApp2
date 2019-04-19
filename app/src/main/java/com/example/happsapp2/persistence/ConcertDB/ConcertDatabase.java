package com.example.happsapp2.persistence.ConcertDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.happsapp2.models.Concert;

@Database(entities = {Concert.class}, version = 1)
public abstract class ConcertDatabase extends RoomDatabase  {
    public static final String DATABASE_NAME = "concerts_db";

    private static ConcertDatabase instance;

    static ConcertDatabase getInstance(final Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ConcertDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract ConcertDao getConcertDAO();
}
