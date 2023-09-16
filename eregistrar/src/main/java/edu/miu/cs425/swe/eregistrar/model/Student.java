package edu.miu.cs425.swe.eregistrar.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @Column(nullable = false, unique = true)
    private String studentNumber;

    @Column(nullable = false)
    private String firstName;
    private String middleName;

    @Column(nullable = false)
    private String lastName;
    private double cgpa;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, columnDefinition = "DATE")
    private Date enrollmentDate;

    @Column(nullable = false)
    private String isInternational;

    public Student() {
        this.studentNumber = "";
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.cgpa = 0.0;
        this.enrollmentDate = new Date();
        this.isInternational = "";
    }
    public Student(String studentNumber, String firstName, String middleName, String lastName, double cgpa, Date enrollmentDate, String isInternational) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cgpa = cgpa;
        this.enrollmentDate = enrollmentDate;
        this.isInternational = isInternational;
    }

    public long getStudentId() {
        return studentId;
    }
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getCgpa() {
        return cgpa;
    }
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getIsInternational() {
        return isInternational;
    }
    public void setIsInternational(String isInternational) {
        this.isInternational = isInternational;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t" + " studentId : " + studentId +
                "\n\t" + " studentNumber : '" + studentNumber + '\'' +
                "\n\t" + " firstName : '" + firstName + '\'' +
                "\n\t" + " middleName : '" + middleName + '\'' +
                "\n\t" + " lastName : '" + lastName + '\'' +
                "\n\t" + " cgpa : " + cgpa +
                "\n\t" + " enrollmentDate : " + enrollmentDate +
                "\n\t" + " isInternational : '" + isInternational + '\'' +
                "\n" + "}";
    }
}
