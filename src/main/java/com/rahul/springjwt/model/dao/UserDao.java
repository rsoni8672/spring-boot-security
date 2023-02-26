package com.rahul.springjwt.model.dao;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "public_user")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @OneToMany(mappedBy = "userDao", fetch = FetchType.EAGER)
    private List<RoleDao> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<RoleDao> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDao> roles) {
        this.roles = roles;
    }
}
