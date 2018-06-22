
package com.university.yantra.models;

import java.util.List;

public class University {

    private Integer id;
    private Boolean userLiked;
    private Location location;
    private List<School> schools;
    private List<UniversityTag> tags;
    private List<Course> courses ;
    private List<String> images;
    private Integer views;
    private Integer numLikes;
    private String name;
    private String contact;
    private String websiteUrl;
    private Integer rating;
    private String logo;
    private String featureImage;
    private String admissionProcedure;
    private String ownedType;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<UniversityTag> getTags() {
        return tags;
    }

    public void setTags(List<UniversityTag> tags) {
        this.tags = tags;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getAdmissionProcedure() {
        return admissionProcedure;
    }

    public void setAdmissionProcedure(String admissionProcedure) {
        this.admissionProcedure = admissionProcedure;
    }

    public String getOwnedType() {
        return ownedType;
    }

    public void setOwnedType(String ownedType) {
        this.ownedType = ownedType;
    }
}
