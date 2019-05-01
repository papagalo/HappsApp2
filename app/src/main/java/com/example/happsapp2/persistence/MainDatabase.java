package com.example.happsapp2.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.happsapp2.models.Band;
import com.example.happsapp2.models.BoardGame;
import com.example.happsapp2.models.Category;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.models.Following;
import com.example.happsapp2.models.Places;
import com.example.happsapp2.models.User;
import com.example.happsapp2.models.UserFollowers;
import com.example.happsapp2.models.VideoGame;
import com.example.happsapp2.persistence.BandDB.BandDao;
import com.example.happsapp2.persistence.BoardGameDB.BoardGameDao;
import com.example.happsapp2.persistence.CategoryDB.CategoryDao;
import com.example.happsapp2.persistence.ConcertDB.ConcertDao;
import com.example.happsapp2.persistence.UserDB.UserDao;
import com.example.happsapp2.persistence.VideoGameDB.VideoGameDao;

import static android.content.ContentValues.TAG;

@Database(entities = {Band.class,
                      BoardGame.class,
                      Concert.class,
                      Places.class,
                      User.class,
                      Following.class,
                      UserFollowers.class,
                      Category.class,
                      VideoGame.class}, version  = 7)
public abstract class MainDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "main_database";

    private static MainDatabase instance;

    public static synchronized MainDatabase getInstance(final Context context) {
        if (instance == null) {
            Log.d(TAG, "getInstance: NULL");
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MainDatabase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        Log.d(TAG, "getInstance: NOT NULL");
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateConcertDbAsyncTask(instance).execute();
            new PopulateBandDbAsyncTask(instance).execute();
            new PopulateVideoGameDbAsyncTask(instance).execute();
            new PopulateBoardGameDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateConcertDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConcertDao concertDao;
        private static final String TAG = "PopulateConcertDbAsyncT";

        private PopulateConcertDbAsyncTask(MainDatabase db) {
            concertDao = db.getConcertDAO();
            Log.d(TAG, "PopulateConcertDbAsyncTask: GETDAO");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: DOINBACKGROUND");
            return null;
        }
    }

    private static class PopulateVideoGameDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private VideoGameDao videoGameDao;
        private static final String TAG = "PopulateVideoGameDbAsyn";

        private PopulateVideoGameDbAsyncTask(MainDatabase db) {
            videoGameDao = db.getVideoGameDAO();
            videoGameDao.insertVideoGame(new VideoGame("The Fritz", "Funk/Rock/Soul", "My House",
                    "5:00 pm", "9:00 pm"));
            Log.d(TAG, "PopulateVideoGameDbAsyncTask: GETDAO");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: DOINBACKGROUND");
            videoGameDao.insertVideoGame(new VideoGame("The Fritz", "Funk/Rock/Soul", "My House",
                    "5:00 pm", "9:00 pm"));
            return null;
        }
    }

    private static class PopulateBoardGameDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BoardGameDao boardGameDao;
        private static final String TAG = "PopulateBoardGameDbAsyn";

        private PopulateBoardGameDbAsyncTask(MainDatabase db) {
            boardGameDao = db.getBoardGameDAO();
            boardGameDao.insertBoardGame(new BoardGame("The Fritz", "My House",
                    "5:00 pm", "9:00 pm"));
            Log.d(TAG, "PopulateVideoGameDbAsyncTask: GETDAO");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: DOINBACKGROUND");
            /*BoardGameDao.insertBoardGame(new BoardGame("The Fritz", "My House",
                    "5:00 pm", "9:00 pm"));*/
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
    public abstract VideoGameDao getVideoGameDAO();
    public abstract BoardGameDao getBoardGameDAO();
    public abstract UserDao getUserDao();

}
