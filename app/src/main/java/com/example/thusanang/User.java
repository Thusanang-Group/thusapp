package com.example.thusanang;

public class User {

    private String Full_Name;
    private String Email;
    private String Phone;
    private String Work;
    private String Address;
    private String password;

    public User(){}

    public User(String first_Name, String email) {
        this.Full_Name = first_Name;
        this.Email = email;
    }
    public User(String fullName, String email, String home) {
        this.Full_Name = fullName;
        this.Email = email;
        this.Address = home;
    }
    public void setFull_Name(String fullName){
        this.Full_Name = fullName;
    }
    public void setEmail(String email){
        this.Email = email;
    }
    public void setPhone(String phone){
        this.Phone = phone;
    }
    public void setAddress(String address){
        this.Address = address;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getFull_Name(){
        return Full_Name;
    }


    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }
    public String getPassword(){
        return password;
    }
    public String getWork(){return Work;}

}
