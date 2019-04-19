package com.example.happsapp2.persistence.CategoryDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.happsapp2.models.Category;

@Database(entities = {Category.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "categories_db";

    private static CategoryDatabase instance;

    static CategoryDatabase getInstance(final Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    CategoryDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract CategoryDao getCategoryDAO();
}
