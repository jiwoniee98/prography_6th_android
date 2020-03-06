package com.example.bottomnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.prography_6th_android.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Integer> list;

    public ViewPagerAdapter(Context context, ArrayList<Integer> list){
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);

        ImageView imageView = view.findViewById(R.id.textView);
        imageView.setImageResource(list.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view ==((View)object));
    }
}
