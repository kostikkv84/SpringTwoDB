package org.example.springdbtest;

import lombok.Data;
import org.example.springdbtest.db1.entity.Customers;
import org.example.springdbtest.db1.service.CustomerService;
import org.example.springdbtest.db2.entity.Student;
import org.example.springdbtest.db2.repository.StudentRepository;
import org.example.springdbtest.db2.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional("secondTransactionManager")
@Data
public class DB2Test {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private OrderAwarePluginRegistry relProviderPluginRegistry;

    @Test // Работает
    public void findById() throws IOException {
        List<Student> result = studentService.getById(1L);
        assertFalse(result.isEmpty(), "Student should be present");
        assertEquals("IVAN", result.get(0).getFirstname());
    }


    @Test // работает
    public void updateById() throws IOException {
        List<Student> result = studentService.getById(1L);
        result.get(0).setEmail("Измененный Email");
        Assertions.assertEquals(result.get(0).getEmail(), studentService.update(result.get(0)).getEmail());
    }

    @Test // Работает два обращения к разным БД
    public void findByIdMultipleBD() throws IOException {
        List<Student> resultP = studentService.getById(1L);
        assertFalse(resultP.isEmpty(), "Product should be present");
        assertEquals(29, resultP.get(0).getAge());

        List<Customers> result = customerService.getCustomerById(1);
        assertFalse(result.isEmpty(), "Customer should be present");
        assertEquals("Fsd", result.get(0).getName());
    }

}
