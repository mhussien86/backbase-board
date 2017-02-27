package com.backbase.boardmembers.models;

import org.parceler.Parcel;

/**
 * Created by mohamed on 27/02/17.
 */

@Parcel
public class MemberDetails {

    public MemberDetails(){}

    public MemberDetails(String header){
        this.header = header ;
    }

    public String name;

    public String surname;

    public String email;

    public String photo;

    public String role;

    public String header;

    String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


}
