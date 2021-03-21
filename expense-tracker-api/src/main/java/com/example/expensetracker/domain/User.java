package com.example.expensetracker.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Transaction> transactions;


    public User(  String firstName, String lastName, String email, String password) {

        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String first_name, String last_name, String email, String password) {
        Id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Integer getUserId() {
        return Id;
    }

    public void setUserId(Integer userId) {
        this.Id = userId;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
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
}
