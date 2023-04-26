package com.example.bd_rlist_sqlite.room;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.example.bd_rlist_sqlite.WordDao;
import com.example.bd_rlist_sqlite.WordRoomDatabase;

import java.util.List;

public class DiaRepository {
    private DiaDao mDiaDao;
    private LiveData<List<Dia>> mAllDias;

    DiaRepository(Application application) {
        DiaRoomDatabase db = DiaRoomDatabase.getDatabase(application);
        mDiaDao = db.diaDao();
        mAllDias = mDiaDao.getAll();
    }

    LiveData<List<Dia>> getAllDias() {
        return mAllDias;
    }

    void insert(Dia dia) {
        DiaRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDiaDao.insert(dia);
        });
    }

    void delete(int id) {
        DiaRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDiaDao.deleteID(id);
        });
    }
}
