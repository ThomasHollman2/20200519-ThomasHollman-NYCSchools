package com.example.nycschools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsViewHolder> {

    private List<SchoolItems> dataSet;
    private ClickListener listener;

    SchoolsAdapter(ClickListener listener){
        this.listener = listener;
    }

    public SchoolsAdapter() {

    }

    public void setDataSet(List<SchoolItems> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SchoolsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SchoolsViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.school_layout,
                                parent,
                                false)

        );
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolsViewHolder holder, final int position) {
        holder.tvSchoolsName.setText(
                dataSet.get(position).school_name);

        holder.itemView.setOnClickListener(v ->
                listener.onClick(dataSet.get(position)));
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }
}
