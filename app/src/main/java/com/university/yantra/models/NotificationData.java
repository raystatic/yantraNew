package com.university.yantra.models;

/**
 * Created by rahul on 4/3/18.
 */

public class NotificationData {

    String college;
    String message;
    String image;
    String time_numeric;
    String time_text;
    String time_ago;
    String apply_button_text;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime_numeric() {
        return time_numeric;
    }

    public void setTime_numeric(String time_numeric) {
        this.time_numeric = time_numeric;
    }

    public String getTime_text() {
        return time_text;
    }

    public void setTime_text(String time_text) {
        this.time_text = time_text;
    }

    public String getTime_ago() {
        return time_ago;
    }

    public void setTime_ago(String time_ago) {
        this.time_ago = time_ago;
    }

    public String getApply_button_text() {
        return apply_button_text;
    }

    public void setApply_button_text(String apply_button_text) {
        this.apply_button_text = apply_button_text;
    }
}
