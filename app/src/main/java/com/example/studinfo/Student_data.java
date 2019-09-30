package com.example.studinfo;

public class Student_data {
    String name,college,depart;
    String sem,mobile,enroll,dob;

    public Student_data(){
        this.name = name;
        this.college = college;
        this.depart = depart;
        this.sem = sem;
        this.mobile = mobile;
        this.enroll = enroll;
        this.dob = dob;
    }

    public Student_data(String name, String college, String depart, String sem, String mobile, String enroll, String dob) {
        this.name = name;
        this.college = college;
        this.depart = depart;
        this.sem = sem;
        this.mobile = mobile;
        this.enroll = enroll;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
