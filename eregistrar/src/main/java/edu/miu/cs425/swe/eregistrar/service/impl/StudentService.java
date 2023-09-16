package edu.miu.cs425.swe.eregistrar.service.impl;

import edu.miu.cs425.swe.eregistrar.model.Student;
import edu.miu.cs425.swe.eregistrar.repo.StudentRepo;
import edu.miu.cs425.swe.eregistrar.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepo studentRepo;

    @Override
    public Student saveStudent(Student student) {
        if(student == null)
            throw new RuntimeException("Student cannot be null!");

        if(student.getStudentId() == 0) {
            String studentNumber = student.getStudentNumber();
            Student existingStudent = studentRepo.findByStudentNumber(studentNumber);

            if(existingStudent != null)
                throw new RuntimeException("Student with this student number (#" + studentNumber + ") already exists!");
        }

        return studentRepo.save(student);
    }

    @Override
    public String deleteStudentById(Long studentId) {
        if(studentId == 0)
            throw new RuntimeException("Student cannot be null or empty!");

        Student existingStudent = findStudentById(studentId);
        if(existingStudent == null)
            throw new RuntimeException("Student with this Id does not exist!");

        String studentNumber = existingStudent.getStudentNumber();
        studentRepo.deleteById(studentId);
        return studentNumber;
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepo.findById(studentId).orElse(null);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        if(keyword == null || keyword.isEmpty())
            throw new RuntimeException("Search string cannot be null or empty!");

        return studentRepo.findAllByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrStudentNumberContaining(keyword, keyword, keyword, keyword);
    }
}
