package com.example.happsapp2.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.happsapp2.models.Concert;
import com.example.happsapp2.persistence.MainRepository;

import java.util.List;

public class ConcertViewModel extends AndroidViewModel {
    private MainRepository repository;
    private LiveData<List<Concert>> allConcerts;

    public ConcertViewModel(@NonNull Application application) {
        super(application);
        repository = new MainRepository(application);
        allConcerts = repository.getAllConcerts();
    }

    public void insert(Concert concert) {
        repository.insertConcertTask(concert);
    }

    public void update(Concert concert) {
        repository.updateConcert(concert);
    }

    public void delete(Concert concert) {
        repository.deleteConcert(concert);
    }

    public LiveData<List<Concert>> getAllConcerts() {
        return allConcerts;
    }
}
