package com.example.bottomnavigation;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prography_6th_android.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView num;
    TextView title;
    TextView director;
    TextView release_date;

    ViewHolder(View itemView)
    {
        super(itemView);

        num = itemView.findViewById(R.id.Num);
        title = itemView.findViewById(R.id.Title);
        director = itemView.findViewById(R.id.Director);
        release_date = itemView.findViewById(R.id.Date);
    }
}
