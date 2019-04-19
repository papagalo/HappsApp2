package com.example.happsapp2.persistence.BandDB;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.happsapp2.models.Band;

import java.util.List;

public class BandRepository {
    private BandDatabase gBandDatabase;

    public BandRepository(Context context) {
        gBandDatabase = BandDatabase.getInstance(context);
    }

    public void insertBandTask(Band band) {    }

    public void updateBand(Band band) {    }

    public LiveData<List<Band>> retrieveEventsTask() {
        return gBandDatabase.getBandDAO().getAllBands();
    }

    public void deleteEvent(Band band) {

    }
}
