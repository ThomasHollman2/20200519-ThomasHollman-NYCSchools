package com.example.nycschools.di;

import com.example.nycschools.model.Repository;
import com.example.nycschools.model.RepositoryImpl;
import com.example.nycschools.model.SchoolsApiService;

public class Injection {

    private SchoolsApiService schoolsApiService;

    public Repository providesRepository() {
        return new RepositoryImpl(providesSchoolInterface());
    }

    private SchoolsApiService providesSchoolInterface() {
        if (schoolsApiService == null) {
            schoolsApiService = SchoolsApiService.Companion.getInstance();
        }
        return schoolsApiService;
    }

}
