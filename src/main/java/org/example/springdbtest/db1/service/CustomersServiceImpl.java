package org.example.springdbtest.db1.service;

import jakarta.persistence.EntityManagerFactory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.springdbtest.db1.entity.Customers;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.example.springdbtest.db1.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class CustomersServiceImpl implements CustomerService {
    private final EntityManagerFactory entityManagerFactory;

    private final CustomerRepository customerRepository;

    @Override
    public List<Customers> getCustomerById(Integer id) {
        Optional<Customers> optional = customerRepository.findById(id);

        return optional.map(Collections::singletonList).orElseGet(Collections::emptyList);
    }

    @Override
    public List<Customers> getCustomerByParam(String param) {
       List<Customers> customers = customerRepository.selectBySurname(param);
        return customers;
    }

    @Override
    public Customers saveCustomer(Customers customer) {
        if (customer.getIdcustomer() != null) {
            Optional<Customers> savedCustomer = customerRepository.findById(customer.getIdcustomer());
            if (savedCustomer.isPresent() && !savedCustomer.get().getName().isEmpty()) {
                throw new ResourceNotFoundException("Customer already exists");
            }
        }
        return customerRepository.save(customer);
    }


    @Override
    public Customers updateCustomer(Customers customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }


    @Override
    public void deleteBySurname(String surname) {
        customerRepository.deleteBySurname(surname);
    }

    @Override
    public Iterable<Customers> getAllCustomer() {
        return customerRepository.findAll();
    }

}
