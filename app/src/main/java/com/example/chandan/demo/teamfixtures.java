package com.example.chandan.demo;

/**
 * Created by chandan on 06-Jan-18.
 */

public class teamfixtures {
    String team1,team2,title;

    public teamfixtures(String team1, String team2, String title) {
        this.team1 = team1;
        this.team2 = team2;
        this.title = title;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTitle() {
        return title;
    }
}
