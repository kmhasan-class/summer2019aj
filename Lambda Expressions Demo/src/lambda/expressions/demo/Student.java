/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambda.expressions.demo;

import java.time.LocalDate;

/**
 *
 * @author kmhasan
 */
public class Student {
    private long id;
    private String name;
    private LocalDate dob;
    private double gpa;

    public Student() {
    }

    public Student(long id, String name, LocalDate dob, double gpa) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gpa = gpa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", dob=" + dob + ", gpa=" + gpa + '}';
    }   
}
