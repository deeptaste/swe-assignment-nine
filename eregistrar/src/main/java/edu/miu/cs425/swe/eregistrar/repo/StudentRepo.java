package edu.miu.cs425.swe.eregistrar.repo;

import edu.miu.cs425.swe.eregistrar.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByStudentNumber(String studentNumber);

    List<Student> findAllByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrStudentNumberContaining(String firstName, String middleName, String lastName, String studentNumber);
}
