package com.example.UnitTestingDemo.repository;

import com.example.UnitTestingDemo.model.Student;

public interface StudentRepository {

    Student findStudentById(int id);
}
