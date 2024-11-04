package org.example.springdbtest.db1.service;

import org.example.springdbtest.db1.entity.Customers;

import java.util.List;

public interface CustomerService  {
    List<Customers> getCustomerById(Integer id);
    List<Customers> getCustomerByParam(String param);
    Customers saveCustomer(Customers customer);
    void deleteBySurname(String surname);
    Iterable<Customers> getAllCustomer();
    Customers updateCustomer(Customers customer);
    void deleteCustomer(Integer id);

}
