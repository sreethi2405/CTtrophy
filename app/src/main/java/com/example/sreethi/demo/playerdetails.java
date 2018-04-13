package com.example.chandan.demo;

/**
 * Created by chandan on 31-Dec-17.
 */

public class playerdetails {
    private String Team_name,Player_name,Email,Skill,btype,bowltype;
    public playerdetails()
    {

    }

    public playerdetails(String team_name, String player_name, String email, String skill, String btype, String bowltype) {
        Team_name = team_name;
        Player_name = player_name;
        Email = email;
        Skill = skill;
        this.btype = btype;
        this.bowltype = bowltype;
    }

    public void setTeam_name(String team_name) {
        Team_name = team_name;
    }

    public void setPlayer_name(String player_name) {
        Player_name = player_name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public void setBowltype(String bowltype) {
        this.bowltype = bowltype;
    }

    public String getTeam_name() {
        return Team_name;
    }

    public String getPlayer_name() {
        return Player_name;
    }

    public String getEmail() {
        return Email;
    }

    public String getSkill() {
        return Skill;
    }

    public String getBtype() {
        return btype;
    }

    public String getBowltype() {
        return bowltype;
    }
}
