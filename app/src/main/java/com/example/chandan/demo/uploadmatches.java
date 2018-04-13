package com.example.chandan.demo;

/**
 * Created by chandan on 13-Jan-18.
 */

public class uploadmatches {
    private String t1,t2,t1score,t2score,t1overs,t2overs,t1wickets,t2wickets,result;

    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public String getT1score() {
        return t1score;
    }

    public String getT2score() {
        return t2score;
    }

    public String getT1overs() {
        return t1overs;
    }

    public String getT2overs() {
        return t2overs;
    }

    public String getT1wickets() {
        return t1wickets;
    }

    public String getT2wicets() {
        return t2wickets;
    }

    public String getResult() {
        return result;
    }

    public uploadmatches(String t1, String t2, String t1score, String t2score, String t1overs, String t2overs, String t1wickets, String t2wickets, String result) {
        this.t1 = t1;
        this.t2 = t2;
        this.t1score = t1score;
        this.t2score = t2score;

        this.t1overs = t1overs;
        this.t2overs = t2overs;
        this.t1wickets = t1wickets;
        this.t2wickets = t2wickets;
        this.result = result;
    }
}
