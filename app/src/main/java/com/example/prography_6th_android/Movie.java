package com.example.prography_6th_android;

public class Movie {

    private String title;
    private String director;
    private String release_date;

    public Movie()
    {
        this.title = title;
        this.director = director;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
