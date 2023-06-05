package com.backend.store.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "admins")
public class Admin {

    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "username")
    private String username;
    @Field(name = "password")
    private String password;
    @Field(name = "oldpassword")
    private String oldpassword;

    public Admin() {
        // Default constructor
    }

    public Admin(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public class AdminLoginDTO {
        private String username;
        private String password;

        public Object getUsername() {
            return null;
        }

        public Object getPassword() {
            return null;
        }

        // Getters and setters
    }

    public class IUpdateAdmin {
        private String name;
        private String password;
        private String oldpassword;

        public Object getName() {
            return null;
        }

        public Object getPassword() {
            return null;
        }

        public Object getOldPassword() {
            return null;
        }

        // Getters and setters
    }

    public static Admin findById(String userId) {
        return null;
    }

    public void save() {
    }

}
