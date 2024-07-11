package com.krishnavamshi.konnect.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data              // combines all @Getter and @Setter [node need to put this annotation for each field], @ToString, and more... see in Docs
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    // With "lombok", We don't need to explicitly code all constructors and getters-setters such boilerplate code, lombok does it by iteself...

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    // @Column(name = "my_name")  // changes column name from "first_name" to "my_name"
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Integer> followers = new ArrayList<>();   // list of IDs so Integer;
    private List<Integer> following = new ArrayList<>();
    private String gender;
    private String bio;
    @Column(columnDefinition = "TEXT")
    private String image;
    // @JsonIgnore
    @ManyToMany
    private List<Post> savedPosts = new ArrayList<>();
    // private LocalDateTime createdAt;
}
