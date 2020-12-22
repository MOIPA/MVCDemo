package com.dql.dao.domain;

import java.util.UUID;

/**
 * 用户类
 */
public class User {
    private String number = "";
    private String firstName = "";
    private String lastName = "";
    private String birthDate = "";
    private String gender = "";
    private String address = "";
    private String phone = "";
    private String healthCondition = "";
    private String allergy = "";
    private String age = "";
    private String memberStartTime = "";
    private String memberEndTime = "";
    private String memberType = "";
    private String familyTag = "";
    private String feeType = "";

    public User(String number, String firstName, String lastName, String birthDate, String gender, String address, String phone, String healthCondition, String allergy, String age, String memberStartTime, String memberEndTime, String memberType, String feeType, String familyTag) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.healthCondition = healthCondition;
        this.allergy = allergy;
        this.age = age;
        this.memberStartTime = memberStartTime;
        this.memberEndTime = memberEndTime;
        this.memberType = memberType;
        this.feeType = feeType;
        this.familyTag = familyTag;
    }

    public User(String firstName, String lastName) {
        this.number = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User() {
        this.number = UUID.randomUUID().toString();
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

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getFamilyTag() {
        return familyTag;
    }

    public void setFamilyTag(String familyTag) {
        this.familyTag = familyTag;
    }

    @Override
    public String toString() {
        return number + ',' +
                firstName + ',' +
                lastName + ',' +
                birthDate + ',' +
                gender + ',' +
                address + ',' +
                phone + ',' +
                healthCondition + ',' +
                allergy + ',' +
                age + ',' +
                memberStartTime + ',' +
                memberEndTime + ',' +
                memberType + ',' +
                feeType + ',' +
                familyTag;
    }
}
