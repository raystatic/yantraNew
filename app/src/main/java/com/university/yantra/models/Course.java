
package com.university.yantra.models;

import java.util.HashMap;
import java.util.Map;

public class Course {

    private Integer id;
    private String name;
    private String image;
    private Boolean userLiked;
    private String wikipediaUrl;
    private String contact;
    private Integer rating;
    private Integer duration;
    private String courseType;
    private String description;
    private Integer school;

    public Boolean getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(Boolean userLiked) {
        this.userLiked = userLiked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

}
