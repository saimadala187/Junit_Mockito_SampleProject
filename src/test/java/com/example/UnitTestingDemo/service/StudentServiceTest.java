package com.example.UnitTestingDemo.service;

import com.example.UnitTestingDemo.model.Student;
import com.example.UnitTestingDemo.model.StudentNotifier;
import com.example.UnitTestingDemo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentNotifier notifier;

    @InjectMocks
    private StudentService studentService;

    @BeforeAll
    public static  void beforeAllTests() {
        System.out.println(">>> Running once before all test methods");
        // Could load configuration or setup DB connection pool (example)
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("--- Running before each test");
        // This is often handled automatically with @Mock/@InjectMocks
        // But you can reset mocks or common test data here if needed
    }

    @Test // Test Case 1
    public void testGetStudentName(){

        when(studentRepository.findStudentById(1)).thenReturn(new Student(1, "Alice","Alice@gmail.com"));

        String name= studentService.getStudentName(1);
        assertEquals("Alice", name, "The student name should be Alice");
        System.out.println("Test Case 1 executed successfully");

    }

    @Test
    public void testGetStudentNameNotFound(){

        //System.out.println("Checking another Test Case");
        when(studentRepository.findStudentById(2)).thenReturn(null);

        String name= studentService.getStudentName(2);
        assertEquals("Student not found", name, "The student should not be found");
        System.out.println("Test Case 2 executed successfully");

    }

    //Testing void Methods.
    @Test
    public void testEnroll_studentEmailSent(){
        String testEmail = "test@example.com";

        studentService.enrollStudent(testEmail);

        verify(notifier).sendEmail(eq(testEmail), eq("Welcome to the course!"));
        System.out.println("Test Case for void method executed successfully");
    }




    //Testing  Exceptions
    @Test
    public void testFindStudentById_ThrowsExceptionForInvalidId() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            studentService.getStudentName(-1);
        });

        assertEquals("ID must be positive", exception.getMessage());
        System.out.println("Test Case for exception executed successfully");
    }

    //Testing for Private Methods
    // Note: Private methods are not directly testable.
    // Instead, you test the public methods that use them.
    // If you need to test private methods, consider refactoring them into a separate class or making them package-private.
    // For example, if you had a private method in StudentService:

    // You would write a test for getFormattedStudentName instead of trying to test formatStudentName directly.
    @Test
    public void testPrivateMethodIndirectly() {
        // Arrange
        Student mockStudent = new Student();
        mockStudent.setName("Alice");

        when(studentRepository.findStudentById(1)).thenReturn(mockStudent);

        // Act
        String formattedName = studentService.getFormattedStudentName(1);

        // Assert
        assertEquals("ALICE", formattedName);
        System.out.println("Test Case for private method executed successfully");
    }
}