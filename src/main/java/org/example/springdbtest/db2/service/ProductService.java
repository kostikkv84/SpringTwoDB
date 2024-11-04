package org.example.springdbtest.db2.service;

import org.example.springdbtest.db1.entity.Customers;
import org.example.springdbtest.db2.entity.Products;

import java.util.List;

public interface ProductService {
    List<Products> getProductsById(Integer id);
    List<Products> getProductsByParam(String param);
    Customers saveCustomer(Products product);

    void deleteByName(String name);

    Iterable<Products> getAllProducts();
    Products updateProducts(Products product);
    void deleteProducts(Integer id);
}
