package org.example.springdbtest.db1.repository;

import org.example.springdbtest.db1.entity.Customers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customers, Integer> {

    @Modifying
    @Query("DELETE FROM Customers c WHERE c.surname = :surname")
    void deleteBySurname(@Param("surname") String surname);

    @Modifying
    @Query("SELECT a FROM Customers a WHERE a.surname = :surname")
    List<Customers> selectBySurname(@Param("surname") String surname);


}
