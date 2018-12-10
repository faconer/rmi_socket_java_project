/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author The
 */
public class Match implements Serializable{
    private int idMatch;
    private String user;
    private int time;
    private int point;
    private int wrong = 8;

    public Match() {
        
    }

    public Match(int idMatch, String user, int time, int point, int wrong) {
        this.idMatch = idMatch;
        this.user = user;
        this.time = time;
        this.point = point;
        this.wrong = wrong;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public String getUser() {
        return user;
    }

    public int getTime() {
        return time;
    }

    public int getPoint() {
        return point;
    }

    public int getWrong() {
        return wrong;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }
    
    
}
