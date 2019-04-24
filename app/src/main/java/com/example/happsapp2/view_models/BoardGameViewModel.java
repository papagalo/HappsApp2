package com.example.happsapp2.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.happsapp2.models.BoardGame;
import com.example.happsapp2.persistence.MainRepository;

import java.util.List;

public class BoardGameViewModel extends AndroidViewModel {
    private MainRepository repository;
    private LiveData<List<BoardGame>> allBoardGames;

    public BoardGameViewModel(@NonNull Application application) {
        super(application);
        repository = new MainRepository(application);
        allBoardGames = repository.getAllBoardGames();
    }

    public void insert(BoardGame boardGame) {
        repository.insertBoardGameTask(boardGame);
    }

    public void update(BoardGame boardGame) {
        repository.updateBoardGame(boardGame);
    }

    public void delete(BoardGame boardGame) {
        repository.deleteBoardGame(boardGame);
    }

    public LiveData<List<BoardGame>> getAllBoardGames() {
        return allBoardGames;
    }

    public void deleteAllBoardGames() { repository.deleteAllBoardGames(); }
}
