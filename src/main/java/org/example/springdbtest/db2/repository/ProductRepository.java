package org.example.springdbtest.db2.repository;

import org.example.springdbtest.db2.entity.Products;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Products, Integer> {

    @Query("DELETE FROM Products p WHERE p.name = :name")
    void deleteByName(@Param("name") String name);

    @Modifying
    @Query("SELECT a FROM Products a WHERE a.name = :name")
    List<Products> selectByName(@Param("name") String name);


}
