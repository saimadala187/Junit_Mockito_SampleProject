package com.example.UnitTestingDemo.service;

import com.example.UnitTestingDemo.model.Student;
import com.example.UnitTestingDemo.model.StudentNotifier;
import com.example.UnitTestingDemo.repository.StudentRepository;

public class StudentService {

    private StudentRepository studentRepository;

    private StudentNotifier studentNotifier;


    public StudentService(StudentRepository studentRepository,
                          StudentNotifier studentNotifier) {
        this.studentRepository = studentRepository;
        this.studentNotifier = studentNotifier;
    }

    public void enrollStudent(String email) {
        // enroll logic
        studentNotifier.sendEmail(email, "Welcome to the course!");
    }


    public String getStudentName(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        Student student = studentRepository.findStudentById(id);
        return (student != null) ? student.getName() : "Student not found";
    }

     private String formatStudentName(Student student) {
         return student.getName().toUpperCase();
     }

     public String getFormattedStudentName(int id) {
         Student student = studentRepository.findStudentById(id);
         return formatStudentName(student);
     }
}
