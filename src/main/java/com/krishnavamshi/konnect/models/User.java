package com.krishnavamshi.konnect.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    private @Getter @Setter Integer id;
    // @Column(name = "my_name")  // changes column name from "first_name" to "my_name"
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String email;
    private @Getter @Setter String password;
    private @Getter @Setter List<Integer> followers = new ArrayList<>();   // list of IDs so Integer;
    private @Getter @Setter List<Integer> following = new ArrayList<>();
    private @Getter @Setter String gender;
}
