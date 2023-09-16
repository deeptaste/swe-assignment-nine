package edu.miu.cs425.swe.eregistrar.controller;

import edu.miu.cs425.swe.eregistrar.model.Student;
import edu.miu.cs425.swe.eregistrar.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/eregistrar")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping(value = {"/", "/home", "/home/index"})
    public String displayHomePage() {
        return "home/index";
    }

    @GetMapping(value = {"/students"})
    public String listAllStudents(Model model, @Param("keyword") String keyword) {
        try {
            List<Student> students = new ArrayList<>();

            if (keyword == null) {
                studentService.findAllStudents().forEach(students::add);
            } else {
                studentService.searchStudents(keyword).forEach(students::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("students", students);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        model.addAttribute("pageTitle", "Student List");

        return "student/list";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        Student student = new Student();

        model.addAttribute("student", student);
        model.addAttribute("pageTitle", "Register Student");

        return "student/detail";
    }

    @PostMapping("/students/save")
    public String saveStudent(Student student, RedirectAttributes redirectAttributes) {
        try {
            Student savedStudent = studentService.saveStudent(student);

            if (savedStudent == null)
                throw new RuntimeException("Error saving Student!");

            redirectAttributes.addFlashAttribute("message", "The Student (# " + savedStudent.getStudentNumber() + ") has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/eregistrar/students";
    }

    @GetMapping("/students/{studentId}")
    public String updateStudent(@PathVariable Long studentId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Student student = studentService.findStudentById(studentId);

            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Student Details");

            return "student/detail";
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/eregistrar/students";
        }
    }

    @GetMapping("/students/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId, RedirectAttributes redirectAttributes) {
        try {
            String studentNumber = studentService.deleteStudentById(studentId);

            if(studentNumber == null)
                throw new RuntimeException("Student with this ID does not exist");

            redirectAttributes.addFlashAttribute("message", "The Student (# " + studentNumber + ") has been deleted successfully!");
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/eregistrar/students";
    }

}
