package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    @Query
    List<Employee> retrieveEmployeesBySurname(@Param("SURNAME") String surname);

    @Query
    List<Employee> retrieveEmployeesByNameFragment(@Param("NAME_FRAGMENT") String nameFragment);

}
