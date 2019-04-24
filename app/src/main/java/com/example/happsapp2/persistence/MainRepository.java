package com.example.happsapp2.persistence;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.example.happsapp2.async.InsertAsyncTask;
import com.example.happsapp2.models.VideoGame;
import com.example.happsapp2.models.Category;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.persistence.VideoGameDB.VideoGameDao;
import com.example.happsapp2.persistence.ConcertDB.ConcertDao;

import java.util.List;

public class MainRepository {

    private MainDatabase gMainDatabase;
    private ConcertDao concertDao;
    private LiveData<List<Concert>> allConcerts;
    private LiveData<List<VideoGame>> allVideoGames;
    private VideoGameDao videoGameDao;


    public MainRepository(Application application) {
        gMainDatabase = MainDatabase.getInstance(application);
        concertDao = gMainDatabase.getConcertDAO();
        videoGameDao = gMainDatabase.getVideoGameDAO();
        allConcerts = concertDao.getAllConcerts();
        allVideoGames = videoGameDao.getAllVideoGames();
    }

    //Categories

    public void insertCategoryTask(Category category) {
        new InsertAsyncTask(gMainDatabase.getCategoryDAO()).execute(category);
    }

    public void updateCategory(Category category) {

    }

    public LiveData<List<Category>> retrieveCategoryTask() {
        return gMainDatabase.getCategoryDAO().getCategories();
    }

    public void deleteCategory(Category category) {

    }


    //Concerts

    public void insertConcertTask(Concert concert) {
        new InsertConcertAsyncTask(concertDao).execute(concert);
    }

    public void updateConcert(Concert concert) {
        new UpdateConcertAsyncTask(concertDao).execute(concert);
    }

    public void deleteConcert(Concert concert) {
        new DeleteConcertAsyncTask(concertDao).execute(concert);
    }

    public LiveData<List<Concert>> getAllConcerts() {
        return allConcerts;
    }

    public void deleteAllConcerts() {
        new DeleteAllConcertAsyncTask(concertDao).execute();
    }

    private static class InsertConcertAsyncTask extends AsyncTask<Concert, Void, Void> {
        private ConcertDao concertDao;

        private InsertConcertAsyncTask(ConcertDao concertDao) {
            this.concertDao = concertDao;
        }

        @Override
        protected Void doInBackground(Concert... concerts) {
            concertDao.insertConcert(concerts[0]);
            return null;
        }
    }

    private static class UpdateConcertAsyncTask extends AsyncTask<Concert, Void, Void> {
        private ConcertDao concertDao;

        private UpdateConcertAsyncTask(ConcertDao concertDao) {
            this.concertDao = concertDao;
        }

        @Override
        protected Void doInBackground(Concert... concerts) {
            concertDao.update(concerts[0]);
            return null;
        }
    }

    private static class DeleteConcertAsyncTask extends AsyncTask<Concert, Void, Void> {
        private ConcertDao concertDao;

        private DeleteConcertAsyncTask(ConcertDao concertDao) {
            this.concertDao = concertDao;
        }

        @Override
        protected Void doInBackground(Concert... concerts) {
            concertDao.delete(concerts[0]);
            return null;
        }
    }

    private static class DeleteAllConcertAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConcertDao concertDao;

        public DeleteAllConcertAsyncTask(ConcertDao concertDao) { this.concertDao = concertDao;  }

        @Override
        protected Void doInBackground(Void... voids) {
            concertDao.deleteAllConcerts();
            return null;
        }
    }

    //VideoGames

    public void insertVideoGameTask(VideoGame videoGame) {
        new InsertVideoGameAsyncTask(videoGameDao).execute(videoGame);
    }

    public void updateVideoGame(VideoGame videoGame) {
        new UpdateVideoGameAsyncTask(videoGameDao).execute(videoGame);
    }

    public void deleteVideoGame(VideoGame videoGame) {
        new DeleteVideoGameAsyncTask(videoGameDao).execute(videoGame);
    }

    public LiveData<List<VideoGame>> getAllVideoGames() {
        return allVideoGames;
    }

    public void deleteAllVideoGames() {
        new DeleteAllVideoGamesAsyncTask(videoGameDao).execute();
    }

    private static class InsertVideoGameAsyncTask extends AsyncTask<VideoGame, Void, Void> {
        private VideoGameDao videoGameDao;

        private InsertVideoGameAsyncTask(VideoGameDao videoGameDao) {
            this.videoGameDao = videoGameDao;
        }

        @Override
        protected Void doInBackground(VideoGame... videoGames) {
            videoGameDao.insertVideoGame(videoGames[0]);
            return null;
        }
    }

    private static class UpdateVideoGameAsyncTask extends AsyncTask<VideoGame, Void, Void> {
        private VideoGameDao videoGameDao;

        private UpdateVideoGameAsyncTask(VideoGameDao videoGameDao) {
            this.videoGameDao = videoGameDao;
        }

        @Override
        protected Void doInBackground(VideoGame... videoGames) {
            videoGameDao.update(videoGames[0]);
            return null;
        }
    }

    private static class DeleteVideoGameAsyncTask extends AsyncTask<VideoGame, Void, Void> {
        private VideoGameDao videoGameDao;

        private DeleteVideoGameAsyncTask(VideoGameDao videoGameDao) {
            this.videoGameDao = videoGameDao;
        }

        @Override
        protected Void doInBackground(VideoGame... videoGames) {
            videoGameDao.delete(videoGames[0]);
            return null;
        }
    }

    private static class DeleteAllVideoGamesAsyncTask extends AsyncTask<Void, Void, Void> {
        private VideoGameDao videoGameDao;

        public DeleteAllVideoGamesAsyncTask(VideoGameDao videoGameDao) { this.videoGameDao = videoGameDao;  }

        @Override
        protected Void doInBackground(Void... voids) {
            videoGameDao.deleteAllVideoGames();
            return null;
        }
    }
}
