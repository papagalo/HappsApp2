package com.example.happsapp2.async;

import android.os.AsyncTask;

import com.example.happsapp2.models.Category;
import com.example.happsapp2.persistence.CategoryDB.CategoryDao;

public class InsertAsyncTask extends AsyncTask<Category, Void, Void> {

    private CategoryDao gCategoryDao;

    public InsertAsyncTask(CategoryDao categoryDao) {
        gCategoryDao = categoryDao;
    }

    @Override
    protected Void doInBackground(Category... categories) {
        gCategoryDao.insertCategories(categories);
        return null;
    }
}
