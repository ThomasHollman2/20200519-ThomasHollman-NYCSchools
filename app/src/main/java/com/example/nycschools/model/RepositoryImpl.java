package com.example.nycschools.model;

import com.example.nycschools.SchoolItems;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository {
    private SchoolsApiService schoolsApiService;

    public RepositoryImpl(SchoolsApiService schoolsApiService) {
        this.schoolsApiService = schoolsApiService;
    }

    @Override
    public Single<List<SchoolItems>> getSchools() {
        return schoolsApiService
                .getSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
