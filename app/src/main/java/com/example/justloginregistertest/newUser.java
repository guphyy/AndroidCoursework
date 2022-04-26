package com.example.justloginregistertest;

public class newUser {
    private String name;
    private String password;
    private String position;
    public newUser(String name, String password, String position) {
        this.name = name;
        this.password = password;
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    @Override
    public String toString() {
        return "newUser{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
