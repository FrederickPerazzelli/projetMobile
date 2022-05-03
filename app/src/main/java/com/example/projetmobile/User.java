package com.example.projetmobile;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String email;
    private String[] roles;
    private String password;
    private String firstName;
    private String lastName;
    private String institution;
    private String field;
    private BigInteger phone;
    private String birthdate;
    private byte[] image;
    private int role;
    private Date registeredDate;
    private int masteredSubject;
    private boolean isVerified;

    public User() {
        super();
    }

    public User(int id, String email, String[] roles, String password, String firstName, String lastName, String institution, String field, BigInteger phone, String birthdate, byte[] image, int role, Date registeredDate, int masteredSubject, boolean isVerified) {
        super();
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
        this.field = field;
        this.phone = phone;
        this.birthdate = birthdate;
        this.image = image;
        this.role = role;
        this.registeredDate = registeredDate;
        this.masteredSubject = masteredSubject;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getMasteredSubject() {
        return masteredSubject;
    }

    public void setMasteredSubject(int masteredSubject) {
        this.masteredSubject = masteredSubject;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
