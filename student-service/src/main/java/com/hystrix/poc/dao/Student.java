package com.hystrix.poc.dao;

public class Student {

    private String stdName;
    private String branch;

    public Student(String stdName, String branch) {
        super();
        this.stdName = stdName;
        this.branch = branch;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStdName() {
        return stdName;
    }

    public String getBranch() {
        return branch;
    }
}
