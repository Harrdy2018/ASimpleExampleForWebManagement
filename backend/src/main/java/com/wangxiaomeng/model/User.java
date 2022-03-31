package com.wangxiaomeng.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// @JsonIgnore注解需要单独写，后端不给前端传相应属性
/**
 * 使用jackson库进行序列化
 * ObjectMapper objectMapper = new ObjectMapper();
 * objectMapper.writeValueAsString(user);
 *
 * 使用jackson库进行反序列化
 * ObjectMapper objectMapper = new ObjectMapper();
 * objectMapper.readValue(str, User.class);
 */

public class User {
    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "country")
    private String country;

    public User(){}

    public User(int id, String name, String email, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public User(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

