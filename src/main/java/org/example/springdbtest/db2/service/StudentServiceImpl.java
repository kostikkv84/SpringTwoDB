package org.example.springdbtest.db2.service;

import jakarta.persistence.EntityManagerFactory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.springdbtest.db1.repository.CustomerRepository;
import org.example.springdbtest.db2.entity.Student;
import org.example.springdbtest.db2.repository.StudentRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
//@Transactional("secondTransactionManager")
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<Student> getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        return optional.map(Collections::singletonList).orElseGet(Collections::emptyList);
    }

    //Реализовать
    @Override
    public List<Student> getStudentByParam(String param) {
        return List.of();
    }

    @Override
    public Student saveStudent(Student student) {
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            throw new ResourceNotFoundException("Customer already exists");
        }
        return studentRepository.save(student);
    }

    //реализовать
    @Override
    public void deleteBySurname(String surname) {

    }

    @Override
    public Iterable<Student> getAllStudent() {
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        studentRepository.deleteById(id);

    }
// требуется реализовать методы



/*
    @Override
    public List<Student> getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        return optional.map(Collections::singletonList).orElseGet(Collections::emptyList);
    }


    @Override
    public List<Student> getByParam(String name) {
        return studentRepository.selectByName(name);
    }

    @Override
    public Student save(Student student) {
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            throw new ResourceNotFoundException("Customer already exists");
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteByParam(String firstName) {
        studentRepository.deleteByName(firstName);
    }

    @Override
    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student update(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return studentRepository.save(student);
    }
    */

}


