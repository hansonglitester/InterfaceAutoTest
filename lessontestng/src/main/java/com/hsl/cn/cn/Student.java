package com.hsl.cn.cn;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(stuname, student.stuname) &&
                Objects.equals(stuno, student.stuno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stuname, stuno);
    }
}
