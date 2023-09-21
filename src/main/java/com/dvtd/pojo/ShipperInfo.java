/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.pojo;


import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class ShipperInfo {

    /**
     * @return the userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the shipperID
     */
    public Integer getShipperID() {
        return shipperID;
    }

    /**
     * @param shipperID the shipperID to set
     */
    public void setShipperID(Integer shipperID) {
        this.shipperID = shipperID;
    }

    /**
     * @return the cmnd
     */
    public String getCmnd() {
        return cmnd;
    }

    /**
     * @param cmnd the cmnd to set
     */
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    /**
     * @return the confirmed
     */
   

    /**
     * @param confirmed the confirmed to set
     */
    
    private Integer userID;
    private String username;
    private String password;
    private String role;
    private String avatar;
    private String email;
    private String phone;
    private Integer shipperID;
    private String cmnd;
    
    public ShipperInfo(int userID, String username, String role, String avatar, String email, String phone, int shipperID, String cmnd) {
        this.userID = userID;
        this.username = username;
//        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.shipperID = shipperID;
        this.cmnd = cmnd;
    }
}

