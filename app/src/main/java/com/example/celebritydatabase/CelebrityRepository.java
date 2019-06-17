package com.example.celebritydatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CelebrityRepository {

    private CelebrityDao celebrityDao;
    private LiveData<List<Celebrity>> allCelebrity;

    CelebrityRepository(Application application){
        CelebrityRoomDatabase db = CelebrityRoomDatabase.getDatabase(application);
        celebrityDao = db.celebrityDao();
        allCelebrity = celebrityDao.getAllCelebrities();
    }

    LiveData<List<Celebrity>> getAllCelebrity(){
        return allCelebrity;
    }

    void insert(Celebrity celebrity){
            new insertAsyncTask(celebrityDao).execute(celebrity);
        }


    private static class insertAsyncTask extends AsyncTask<Celebrity, Void, Void> {

        private CelebrityDao asyncTaskDao;

        public insertAsyncTask(CelebrityDao celebrityDao) {
        }

        @Override
        protected Void doInBackground(Celebrity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

