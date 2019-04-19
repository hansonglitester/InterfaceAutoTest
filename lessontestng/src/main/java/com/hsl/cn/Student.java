package com.hsl.cn;

public class Student {
    private Integer id;
    private String stuname;
    private String stuno;

    public Student(Integer id, String stuname, String stuno) {
        this.id = id;
        this.stuname = stuname;
        this.stuno = stuno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuname='" + stuname + '\'' +
                ", stuno='" + stuno + '\'' +
                '}';
    }
}
