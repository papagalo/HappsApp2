package com.example.happsapp2.persistence.CategoryDB;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.happsapp2.async.InsertAsyncTask;
import com.example.happsapp2.models.Category;

import java.util.List;

public class CategoryRepository {
    private CategoryDatabase gCategoryDatabase;

    public CategoryRepository(Context context) {
        gCategoryDatabase = CategoryDatabase.getInstance(context);
    }

    public void insertCategoryTask(Category category) {
        new InsertAsyncTask(gCategoryDatabase.getCategoryDAO()).execute(category);
    }

    public void updateCategory(Category category) {

    }

    public LiveData<List<Category>> retrieveCategoryTask() {
        return gCategoryDatabase.getCategoryDAO().getCategories();
    }

    public void deleteCategory(Category category) {

    }
}
