
package com.university.yantra.models;


public class Campaigners {

    private Integer id;
    private University university;
    private Integer numLikes;
    private String first_name;
    private String last_name;
    private String contact;
    private Boolean userLiked;
    private String email;
    private String linkedInProfile;
    private String campaignerType;
    private String description;

    public Boolean getUserLIked() {
        return userLiked;
    }

    public void setUserLIked(Boolean userLIked) {
        this.userLiked = userLIked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Integer numLikes) {
        this.numLikes = numLikes;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    public String getCampaignerType() {
        return campaignerType;
    }

    public void setCampaignerType(String campaignerType) {
        this.campaignerType = campaignerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
