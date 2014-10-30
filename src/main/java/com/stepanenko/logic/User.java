package com.stepanenko.logic;

public class User {

    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String email;
    private String phone;

    public User() {
        username = null;
        password = null;
        lastname = null;
        firstname = null;
        email = null;
        phone = null;
    }


    public User(String username, String password, String lastname, String firstname, String email, String phone) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    public void setUsername(String s) {
        username = s;
    }

    public void setPassword(String s) {
        password = s;
    }

    public void setLastname(String s) {
        lastname = s;
    }

    public void setFirstname(String s) {
        firstname = s;
    }

    public void setEmail(String s) {
        email = s;
    }

    public void setPhone(String s) {
        phone = s;
    }

}

