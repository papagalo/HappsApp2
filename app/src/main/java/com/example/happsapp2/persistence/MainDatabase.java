package com.example.happsapp2.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.happsapp2.models.Band;
import com.example.happsapp2.models.Category;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.models.Following;
import com.example.happsapp2.models.Places;
import com.example.happsapp2.models.User;
import com.example.happsapp2.models.UserFollowers;
import com.example.happsapp2.persistence.BandDB.BandDao;
import com.example.happsapp2.persistence.CategoryDB.CategoryDao;
import com.example.happsapp2.persistence.ConcertDB.ConcertDao;

@Database(entities = {Band.class,
                      Concert.class,
                      Places.class,
                      User.class,
                      Following.class,
                      UserFollowers.class,
                      Category.class}, version  = 1)
public abstract class MainDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "main_database";

    private static MainDatabase instance;

    public static synchronized MainDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MainDatabase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateConcertDbAsyncTask(instance).execute();
            new PopulateBandDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateConcertDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConcertDao concertDao;

        private PopulateConcertDbAsyncTask(MainDatabase db) {
            concertDao = db.getConcertDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            concertDao.insertConcert(new Concert("The Fritz", "Boone Saloon",
                    "7:00 pm", "12:00 am"));
            concertDao.insertConcert(new Concert("The Mantras", "Tapp Room",
                    "9:00 pm", "1:00 am"));
            concertDao.insertConcert(new Concert("Big Something", "The Local",
                    "8:00 pm", "2:00 am"));
            concertDao.insertConcert(new Concert("The Who", "Convocation Center",
                    "8:00 pm", "2:00 am"));
            concertDao.insertConcert(new Concert("Funkelstiltskin", "The Local",
                    "8:00 pm", "2:00 am"));
            concertDao.insertConcert(new Concert("Pigeons Playing Ping Pong", "The Local",
                    "10:00 pm", "2:00 am"));
            concertDao.insertConcert(new Concert("ZOSO", "Legends",
                    "5:00 pm", "2:00 am"));
            concertDao.insertConcert(new Concert("Rebelution", "Galileo's",
                    "8:00 pm", "2:00 am"));
            return null;
        }
    }

    private static class PopulateBandDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BandDao bandDao;

        private PopulateBandDbAsyncTask(MainDatabase db) {
            bandDao = db.getBandDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bandDao.insertBand(new Band("The Fritz", "Funk/Rock/Soul"));
            bandDao.insertBand(new Band("The Mantras", "Rock/Jam/World"));
            bandDao.insertBand(new Band("Big Something", "Rock/Funk/Reggae"));
            return null;
        }
    }

    public abstract BandDao getBandDAO();
    public abstract ConcertDao getConcertDAO();
    public abstract CategoryDao getCategoryDAO();

}
