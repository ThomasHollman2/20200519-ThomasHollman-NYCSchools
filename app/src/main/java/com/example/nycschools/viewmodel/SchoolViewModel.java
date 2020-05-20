package com.example.nycschools.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nycschools.SchoolItems;
import com.example.nycschools.model.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.nycschools.SchoolItems;
import com.example.nycschools.model.Repository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class SchoolViewModel extends ViewModel {
    private Repository repository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<SchoolItems>> schoolItemsMutableLiveData = new MutableLiveData<>();
    public LiveData<List<SchoolItems>> schoolItemsLiveData = schoolItemsMutableLiveData;

    private MutableLiveData<String> errorMutableLiveData = new MutableLiveData<>();
    public LiveData<String> errorLiveData = errorMutableLiveData;

    public SchoolViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getSchools() {
        disposable.add(
                repository.getSchools().subscribeWith(
                        new DisposableSingleObserver<List<SchoolItems>>() {

                    @Override
                    public void onSuccess(List<SchoolItems> schoolItems) {
                        schoolItemsMutableLiveData.setValue(schoolItems);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMutableLiveData.setValue(e.getLocalizedMessage());
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}