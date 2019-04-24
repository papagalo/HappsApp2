package com.example.happsapp2.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.happsapp2.models.VideoGame;
import com.example.happsapp2.persistence.MainRepository;

import java.util.List;

public class VideoGameViewModel extends AndroidViewModel {
    private MainRepository repository;
    private LiveData<List<VideoGame>> allVideoGames;

    public VideoGameViewModel(@NonNull Application application) {
        super(application);
        repository = new MainRepository(application);
        allVideoGames = repository.getAllVideoGames();
    }

    public void insert(VideoGame videoGame) {
        repository.insertVideoGameTask(videoGame);
    }

    public void update(VideoGame videoGame) {
        repository.updateVideoGame(videoGame);
    }

    public void delete(VideoGame videoGame) {
        repository.deleteVideoGame(videoGame);
    }

    public LiveData<List<VideoGame>> getAllVideoGames() {
        return allVideoGames;
    }

    public void deleteAllVideoGames() { repository.deleteAllVideoGames(); }
}
