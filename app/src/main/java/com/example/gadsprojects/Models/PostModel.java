package com.example.gadsprojects.Models;

public class PostModel {
    private String email;
    private String firstName;
    private String lastName;
    private String link;

    public PostModel(){}

    public PostModel(String email, String firstName, String lastName, String link) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.link = link;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
