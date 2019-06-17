package com.example.celebritydatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Celebrity.class}, version = 1)
public abstract class CelebrityRoomDatabase extends RoomDatabase {
    public abstract CelebrityDao celebrityDao();

    private static volatile CelebrityRoomDatabase INSTANCE;

    static CelebrityRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (CelebrityRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CelebrityRoomDatabase.class,
                            "celebrity_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback RoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            //Following line would be used if I wanted a new db on app restarts
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CelebrityDao celebrityDao;

        PopulateDbAsync(CelebrityRoomDatabase db) {
            celebrityDao = db.celebrityDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            celebrityDao.deleteAll();

            Celebrity celebrity = new Celebrity("Donald", "Trump", "US President");
            celebrityDao.insert(celebrity);
            celebrity = new Celebrity("Kanye", "West", "Music Producer");
            celebrityDao.insert(celebrity);
            return null;
        }
    }
}
