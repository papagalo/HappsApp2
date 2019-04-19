package com.example.happsapp2.persistence.CategoryDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.happsapp2.models.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insertCategories(Category... categories);

    @Query("SELECT * FROM categories;")
    LiveData<List<Category>> getCategories();

    @Query("SELECT * FROM categories WHERE title = :title;")
    List<Category> getCategoryWithCustomQuery(String title);

    @Delete
    int deleteCategories(Category... categories);

    @Update
    int Update(Category categories);

}
