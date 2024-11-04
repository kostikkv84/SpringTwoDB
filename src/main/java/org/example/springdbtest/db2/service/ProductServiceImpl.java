package org.example.springdbtest.db2.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.springdbtest.db1.entity.Customers;
import org.example.springdbtest.db2.entity.Products;
import org.example.springdbtest.db2.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
//@Transactional("secondTransactionManager")
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

// требуется реализовать методы

    @Override
    public List<Products> getProductsById(Integer id) {
        Optional<Products> optional = productRepository.findById(id);
        return optional.map(Collections::singletonList).orElseGet(Collections::emptyList);
    }

    @Override
    public List<Products> getProductsByParam(String param) {
        return List.of();
    }

    @Override
    public Customers saveCustomer(Products product) {
        return null;
    }

    @Override
    public void deleteByName(String name) {
        productRepository.deleteByName(name);
    }

    @Override
    public Iterable<Products> getAllProducts() {
        return null;
    }

    @Override
    public Products updateProducts(Products product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProducts(Integer id) {

    }
}
