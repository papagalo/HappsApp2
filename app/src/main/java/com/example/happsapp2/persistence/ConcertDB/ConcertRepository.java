package com.example.happsapp2.persistence.ConcertDB;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.happsapp2.models.Concert;

import java.util.List;

public class ConcertRepository {
    private ConcertDatabase gConcertDatabase;

    public ConcertRepository(Context context) {
        gConcertDatabase = ConcertDatabase.getInstance(context);
    }

    public void insertConcertTask(Concert concert) {

    }

    public void updateConcert(Concert concert) {

    }

    public LiveData<List<Concert>> retrieveAllConcertsTask() {
        return gConcertDatabase.getConcertDAO().getAllConcerts();
    }

    public void deleteConcert(Concert concert) {

    }
}
