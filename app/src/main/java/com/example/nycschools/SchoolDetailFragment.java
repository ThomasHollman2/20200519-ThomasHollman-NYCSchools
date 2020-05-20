package com.example.nycschools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SchoolDetailFragment extends Fragment {

    private static final String FRAGMENT_DATA = SchoolDetailFragment.class.getSimpleName() + "FRAGMENT_DATA";
    TextView tvSchoolName, tvNumSatTestTakers, tvMath, tvReading, tvWriting;
    /**
     * "inflate" the layout that this fragment will
     * show to the user.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(
                R.layout.school_details_layout,
                container,
                false);
        tvSchoolName = view.findViewById(R.id.tv_school_name);
        tvNumSatTestTakers = view.findViewById(R.id.tv_num_sat_test_takers);
        tvMath = view.findViewById(R.id.tv_math);
        tvReading = view.findViewById(R.id.tv_reading);
        tvWriting = view.findViewById(R.id.tv_writing);
        return view;

    }

    public static SchoolDetailFragment instance(SchoolItems item){
        SchoolDetailFragment fragment = new SchoolDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAGMENT_DATA, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        SchoolItems item = bundle.getParcelable(FRAGMENT_DATA);
        tvSchoolName.setText(item.school_name);
        tvNumSatTestTakers.setText("Number of SAT Test Takers " + item.num_of_sat_test_takers);
        tvMath.setText("SAT Math Average Score " + item.sat_math_avg_score);
        tvReading.setText("SAT Reading Average Score " + item.sat_critical_reading_avg_score);
        tvWriting.setText("SAT Writing Average Score " + item.sat_writing_avg_score);

    }
}
