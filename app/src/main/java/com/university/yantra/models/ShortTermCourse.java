
package com.university.yantra.models;

import java.util.HashMap;
import java.util.Map;

public class ShortTermCourse {

    private Integer id;
    private Integer numLikes;
    private String name;
    private String image;
    private Boolean userLiked;
    private String offerredBy;
    private String url;
    private String description;
    private String contact;
    private Integer rating;
    private Integer duration;
    private Integer boostPercent;

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

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Integer numLikes) {
        this.numLikes = numLikes;
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

    public String getOfferredBy() {
        return offerredBy;
    }

    public void setOfferredBy(String offerredBy) {
        this.offerredBy = offerredBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getBoostPercent() {
        return boostPercent;
    }

    public void setBoostPercent(Integer boostPercent) {
        this.boostPercent = boostPercent;
    }

}
