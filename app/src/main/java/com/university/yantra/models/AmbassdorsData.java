package com.university.yantra.models;

import java.io.Serializable;

/**
 * Created by Rishabh on 2/23/2018.
 */

public class AmbassdorsData implements Serializable {
    String baca;
    String location;
    String university;
    String ambassdorName;
    String linkedIn;

    public String getBaca() {
        return baca;
    }

    public void setBaca(String baca) {
        this.baca = baca;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getAmbassdorName() {
        return ambassdorName;
    }

    public void setAmbassdorName(String ambassdorName) {
        this.ambassdorName = ambassdorName;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}
