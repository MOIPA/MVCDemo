package com.dql.dao.domain;

/**
 * 用户类
 */
public class User {
    private String number = "";
    private String firstName = "";
    private String lastName = "";
    private String date = "";
    private String gender = "";
    private String address = "";
    private String phone = "";
    private String healthCondition = "";
    private String allergy = "";
    private String age = "";
    private String memberStartTime = "";
    private String memberEndTime = "";
    private String memberType = "";
    private String paidFee = "";

    public User(String number, String firstName, String lastName, String date, String gender, String address, String phone, String healthCondition, String allergy, String age, String memberStartTime, String memberEndTime, String memberType, String paidFee) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.healthCondition = healthCondition;
        this.allergy = allergy;
        this.age = age;
        this.memberStartTime = memberStartTime;
        this.memberEndTime = memberEndTime;
        this.memberType = memberType;
        this.paidFee = paidFee;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMemberStartTime() {
        return memberStartTime;
    }

    public void setMemberStartTime(String memberStartTime) {
        this.memberStartTime = memberStartTime;
    }

    public String getMemberEndTime() {
        return memberEndTime;
    }

    public void setMemberEndTime(String memberEndTime) {
        this.memberEndTime = memberEndTime;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(String paidFee) {
        this.paidFee = paidFee;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
