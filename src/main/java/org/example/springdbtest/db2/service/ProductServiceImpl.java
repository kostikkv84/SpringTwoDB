package org.example.springdbtest.db2.service;

import lombok.RequiredArgsConstructor;
import org.example.springdbtest.db1.entity.Customers;
import org.example.springdbtest.db2.entity.Products;
import org.example.springdbtest.db2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

// требуется реализовать методы

    @Override
    public List<Products> getProductsById(Integer id) {
        return List.of();
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
        return null;
    }

    @Override
    public void deleteProducts(Integer id) {

    }
}
