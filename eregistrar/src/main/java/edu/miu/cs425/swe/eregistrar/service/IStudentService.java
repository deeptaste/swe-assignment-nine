package edu.miu.cs425.swe.eregistrar.service;

import edu.miu.cs425.swe.eregistrar.model.Student;

import java.util.List;

public interface IStudentService {

    public Student saveStudent(Student student);

    public String deleteStudentById(Long studentId);

    public Student findStudentById(Long studentId);

    public List<Student> findAllStudents();

    public List<Student> searchStudents(String keyword);
}
