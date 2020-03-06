package com.example.prography_6th_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prography_6th_android.R;
import com.example.prography_6th_android.ViewHolder;

import java.util.ArrayList;

public class jsonAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Movie> dataList = null;

    jsonAdapter(ArrayList<Movie> dataList){
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.fragment_menu2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int i = 1;
        viewHolder.num.setText(i++);
        viewHolder.title.setText(dataList.get(position).getTitle());
        viewHolder.director.setText(dataList.get(position).getDirector());
        viewHolder.release_date.setText(dataList.get(position).getRelease_date());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
