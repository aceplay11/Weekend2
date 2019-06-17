package com.example.celebritydatabase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CelebrityViewModel extends AndroidViewModel {

    private static CelebrityRepository repository;
    private LiveData<List<Celebrity>> allCelebrity;

    public CelebrityViewModel(Application application){
        super(application);
        repository = new CelebrityRepository(application);
        allCelebrity = repository.getAllCelebrity();
    }

    LiveData<List<Celebrity>> getAllCelebrity(){

        return allCelebrity;
    }

    static void insert(Celebrity celebrity){

        repository.insert(celebrity);
    }
}
