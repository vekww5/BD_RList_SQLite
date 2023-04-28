package com.example.bd_rlist_sqlite.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.bd_rlist_sqlite.WordRepository;

import java.util.List;

public class DiaViewModel extends AndroidViewModel {
    private DiaRepository mRepository;
    private final LiveData<List<Dia>> mAllDias;

    public DiaViewModel (Application application) {
        super(application);
        mRepository = new DiaRepository(application);
        mAllDias = mRepository.getAllDias();
    }

    public LiveData<List<Dia>> getAllDias() { return mAllDias; }

    public LiveData<List<Dia>> getDiaForPeriod(long start, long end) { return mRepository.getDiaForPeriod(start, end); }

    public void insert(Dia dia) { mRepository.insert(dia); }

    public void delete(int id) { mRepository.delete(id); }

}
