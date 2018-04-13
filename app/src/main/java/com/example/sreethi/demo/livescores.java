package com.example.chandan.demo;


/**
 * Created by chandan on 01-Jan-18.
 */

public class livescores {
    private String overs,battingteam,bowlingteam,wickets,score;
    public livescores(String overs, String battingteam, String bowlingteam, String wickets, String score) {
        this.overs = overs;
        this.battingteam = battingteam;
        this.bowlingteam = bowlingteam;
        this.wickets = wickets;
        this.score = score;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public String getBattingteam() {
        return battingteam;
    }

    public void setBattingteam(String battingteam) {
        this.battingteam = battingteam;
    }

    public String getBowlingteam() {
        return bowlingteam;
    }

    public void setBowlingteam(String bowlingteam) {
        this.bowlingteam = bowlingteam;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
