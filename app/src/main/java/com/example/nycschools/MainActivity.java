package com.example.nycschools;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nycschools.di.Injection;
import com.example.nycschools.viewmodel.SchoolViewModel;
import com.example.nycschools.viewmodel.ViewModelFactory;

public class MainActivity extends AppCompatActivity implements ClickListener{

    private RecyclerView recyclerView;
    private SchoolsAdapter adapter;

    private Injection injection = new Injection();
    private SchoolViewModel schoolViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schoolViewModel = new ViewModelProvider(this,
                new ViewModelFactory(injection
                        .providesRepository()))
                .get(SchoolViewModel.class);

        schoolViewModel.schoolItemsLiveData.observe(this, schoolItems -> {
            adapter.setDataSet(schoolItems);
        });

        schoolViewModel.errorLiveData.observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        initRecycler();
        schoolViewModel.getSchools();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);

        adapter = new SchoolsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(SchoolItems item) {
        SchoolDetailFragment fragment =
                SchoolDetailFragment.instance(item);

        FragmentManager fragmentManager =
                getSupportFragmentManager();
        FragmentTransaction transaction =
                fragmentManager.beginTransaction();
        transaction.replace(
                R.id.container,
                fragment);
        transaction.addToBackStack("");
        transaction.commit();

    }
}
