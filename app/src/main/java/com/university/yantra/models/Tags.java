package com.university.yantra.models;

import java.io.Serializable;

/**
 * Created by Rishabh on 2/21/2018.
 */

public class Tags implements Serializable{
    String entrance;
    String type;
    String rating;

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
