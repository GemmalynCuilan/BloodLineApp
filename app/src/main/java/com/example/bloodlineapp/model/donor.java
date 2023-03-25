package com.example.bloodlineapp.model;

public class donor {
    private String id, lastname, firstname, middlename, password, birthdate, age, sex, bloodGroup, identifyno,
            street, barangay, tm, city, code, mobileNumber, email, idno;
    public donor(){
    }
    public donor(String id, String lastname, String firstname, String middlename, String password, String birthdate, String age, String sex, String bloodGroup, String identifyno, String street, String barangay, String tm, String city, String code, String mobileNumber, String email, String idno) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.password = password;
        this.birthdate = birthdate;
        this.age = age;
        this.sex = sex;
        this.bloodGroup = bloodGroup;
        this.identifyno = identifyno;
        this.street = street;
        this.barangay = barangay;
        this.tm = tm;
        this.city = city;
        this.code = code;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.idno = idno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getIdentifyno() {
        return identifyno;
    }

    public void setIdentifyno(String identifyno) {
        this.identifyno = identifyno;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }


}
