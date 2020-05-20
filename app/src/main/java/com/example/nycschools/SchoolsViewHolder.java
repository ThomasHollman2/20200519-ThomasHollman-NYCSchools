package com.example.nycschools;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SchoolsViewHolder extends RecyclerView.ViewHolder {

    TextView tvSchoolsName;

    public SchoolsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvSchoolsName = itemView.findViewById(R.id.tv_schools_name);

    }
}