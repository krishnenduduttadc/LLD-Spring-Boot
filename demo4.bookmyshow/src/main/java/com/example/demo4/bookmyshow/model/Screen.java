package com.example.demo4.bookmyshow.model;

import java.util.List;

public class Screen {
    private String screenId;
    private List<Show> shows;

    public Screen(String screenId, List<Show> shows) {
        this.screenId = screenId;
        this.shows = shows;
    }

    public String getScreenId() { return screenId; }
    public List<Show> getShows() { return shows; }
}
