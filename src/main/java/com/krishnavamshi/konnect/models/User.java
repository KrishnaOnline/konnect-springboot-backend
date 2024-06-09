package com.krishnavamshi.konnect.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer id;
    // @Column(name = "my_name")  // changes column name from "first_name" to "my_name"
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Integer> followers = new ArrayList<>();   // list of IDs so Integer;
    private List<Integer> following = new ArrayList<>();
    private String gender;
    
    public User() {}
    public User(Integer id, String firstName, String lastName, String email, String password, List<Integer> followers, List<Integer> following, String gender) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.following = following;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Integer> getFollowers() {
        return followers;
    }
    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }
    public List<Integer> getFollowing() {
        return following;
    }
    public void setFollowing(List<Integer> following) {
        this.following = following;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }     
}
