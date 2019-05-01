package com.example.happsapp2.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.happsapp2.models.User;
import com.example.happsapp2.persistence.MainRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private MainRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new MainRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user) {
        repository.insertUserTask(user);
    }

    public void update(User user) {
        repository.updateUser(user);
    }

    public void delete(User user) {
        repository.deleteUser(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public User getSpecificUser(int user_ID) {
        return repository.getSpecificUser(user_ID);
    }

    public void deleteAllUsers() { repository.deleteAllUsers(); }
}
