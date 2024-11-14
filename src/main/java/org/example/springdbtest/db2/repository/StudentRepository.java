package org.example.springdbtest.db2.repository;

import org.example.springdbtest.db2.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("DELETE FROM Student p WHERE p.firstname = :name")
    void deleteByName(@Param("name") String name);

    @Modifying
    @Query("SELECT a FROM Student a WHERE a.firstname = :name")
    List<Student> selectByName(@Param("name") String name);


}
