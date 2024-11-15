package org.example.springdbtest.db2.service;

import org.example.springdbtest.base.BaseService;
import org.example.springdbtest.db1.entity.Customers;
import org.example.springdbtest.db2.entity.Student;

import java.util.List;


public interface StudentService {
 List<Student> getStudentById(Long id);
 List<Student> getStudentByParam(String param);
 Student saveStudent(Student student);
 void deleteBySurname(String surname);
 Iterable<Student> getAllStudent();
 Student updateStudent(Student student);
 void deleteStudentById(Long id);
}
