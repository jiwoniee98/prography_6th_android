package com.example.bottomnavigation;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.prography_6th_android.Menu1Fragment;
import com.example.prography_6th_android.Menu2Fragment;
import com.example.prography_6th_android.Menu3Fragment;
import com.example.prography_6th_android.R;
import com.example.prography_6th_android.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    Menu1Fragment fragment1;
    Menu2Fragment fragment2;
    Menu3Fragment fragment3;

    private ArrayList<Integer> list;
    private static final int DP = 24;

    Handler handler = new Handler();

    String str_url = "https://ghibliapi.herokuapp.com/films";
    StringBuffer buffer = new StringBuffer();

    private ArrayList<Movie> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Menu1Fragment();
        fragment2 = new Menu2Fragment();
        fragment3 = new Menu3Fragment();

        this.initializeData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);

        float density = getResources().getDisplayMetrics().density;
        int margin = (int)(DP*density);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        @SuppressLint("WrongViewCast") RecyclerView recyclerView = (RecyclerView)findViewById(R.id.Num);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new com.example.bottomnavigation.jsonAdapter(movieList));

        viewPager.setAdapter(new ViewPagerAdapter(this,list);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.navigation_menu1:
                        transaction.replace(R.id.frame_layout, fragment1).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_menu2:
                        transaction.replace(R.id.frame_layout, fragment2).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_menu3:
                        transaction.replace(R.id.frame_layout, fragment3).commitAllowingStateLoss();
                        break;
                }
            }

        });

        json_thread thread = new json_thread();
        thread.start();

    }

    private void initializeData() {

        list = new ArrayList();
        movieList = new ArrayList<>();

        list.add(R.layout.fragment_menu1);
        list.add(R.layout.fragment_menu2);
        list.add(R.layout.fragment_menu3);
    }

    class json_thread extends Thread{
        public void run(){
            try{
                URL url = new URL(str_url);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    int resCode = conn.getResponseCode();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str = null;
                    //StringBuffer buffer = new StringBuffer();

                    while(true){
                        //str = reader.readLine();
                        while((str = reader.readLine()) != null) {
                            buffer.append(str);
                        }
                        //println(str);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        private String getJsonString(StringBuffer buffer){
            String json = "";

            try{
                byte[] buffers = new byte[buffer.length() + 1];
                json = new String(buffers, "UTF-8");

            }catch(IOException e){
                e.printStackTrace();
            }

            return json;
        }

        private void JSONParsing(String json) {

            try{
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = new JSONArray("Movies");

                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject movieObject = jsonArray.getJSONObject(i);

                    Movie movie = new Movie();

                    movie.setTitle(movieObject.getString("tiltle"));
                    movie.setDirector(movieObject.getString("director"));
                    movie.setRelease_date(movieObject.getString("release_date"));

                    movieList.add(movie);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}