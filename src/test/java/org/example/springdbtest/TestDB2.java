package org.example.springdbtest;

import lombok.Data;
import org.example.springdbtest.db1.entity.Customers;
import org.example.springdbtest.db1.repository.CustomerRepository;
import org.example.springdbtest.db1.service.CustomerService;
import org.example.springdbtest.db2.entity.Products;
import org.example.springdbtest.db2.repository.ProductRepository;
import org.example.springdbtest.db2.service.ProductService;
import org.example.springdbtest.db2.service.ProductServiceImpl;
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
//@Transactional("secondTransactionManager")
@Data
public class TestDB2 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderAwarePluginRegistry relProviderPluginRegistry;

    @Test // Работает
    public void findById() throws IOException {
        List<Products> result = productService.getProductsById(15);
        assertFalse(result.isEmpty(), "Product should be present");
        assertEquals("device10", result.get(0).getName());
    }

    @Test // работает
    public void updateById() throws IOException {
        List<Products> result = productService.getProductsById(90);
        result.get(0).setDescription("Измененный1");

        Assertions.assertEquals("Измененный1", productService.updateProducts(result.get(0)).getDescription());
    }

    @Test // Работает два обращения к разным БД
    public void findByIdMultipleBD() throws IOException {
        List<Products> resultP = productService.getProductsById(15);
        assertFalse(resultP.isEmpty(), "Product should be present");
        assertEquals("device10", resultP.get(0).getName());

        List<Customers> resultC = customerService.getCustomerById(1);
        assertFalse(resultC.isEmpty(), "Customer should be present");
        assertEquals("Fsd", resultC.get(0).getName());
    }

}
