package com.example.happsapp2.persistence;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.happsapp2.async.InsertAsyncTask;
import com.example.happsapp2.models.Band;
import com.example.happsapp2.models.Category;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.persistence.BandDB.BandDao;
import com.example.happsapp2.persistence.ConcertDB.ConcertDao;

import java.util.List;

public class MainRepository {

    private MainDatabase gMainDatabase;
    private ConcertDao concertDao;
    private LiveData<List<Concert>> allConcerts;
    private LiveData<List<Band>> allBands;
    private BandDao bandDao;


    public MainRepository(Application application) {
        gMainDatabase = MainDatabase.getInstance(application);
        concertDao = gMainDatabase.getConcertDAO();
        allConcerts = concertDao.getAllConcerts();
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

    //Bands

    public void insertBandTask(Band band) {    }

    public void updateBand(Band band) {    }

    public LiveData<List<Band>> retrieveBandsTask() { return allBands;  }

    public void deleteEvent(Band band) {

    }

    private static class InsertBandAsyncTask extends AsyncTask<Band, Void, Void> {
        private BandDao bandDao;

        private InsertBandAsyncTask(BandDao bandDao) {
            this.bandDao = bandDao;
        }

        @Override
        protected Void doInBackground(Band... bands) {
            bandDao.insertBand(bands[0]);
            return null;
        }
    }

    private static class UpdateBandAsyncTask extends AsyncTask<Band, Void, Void> {
        private BandDao bandDao;

        private UpdateBandAsyncTask(BandDao bandDao) {
            this.bandDao = bandDao;
        }

        @Override
        protected Void doInBackground(Band... bands) {
            bandDao.update(bands[0]);
            return null;
        }
    }

    private static class DeleteBandAsyncTask extends AsyncTask<Band, Void, Void> {
        private BandDao bandDao;

        private DeleteBandAsyncTask(BandDao bandDao) {
            this.bandDao = bandDao;
        }

        @Override
        protected Void doInBackground(Band... bands) {
            bandDao.delete(bands[0]);
            return null;
        }
    }



}
