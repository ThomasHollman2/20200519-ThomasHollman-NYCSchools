package com.example.nycschools.model;

import com.example.nycschools.SchoolItems;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;

public interface Repository {
    Single<List<SchoolItems>> getSchools();
}
