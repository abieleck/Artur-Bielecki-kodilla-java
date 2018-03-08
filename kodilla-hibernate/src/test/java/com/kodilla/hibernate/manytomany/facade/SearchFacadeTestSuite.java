package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchFacadeTestSuite {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private SearchFacade searchFacade;

    private static List<Employee> employees = new ArrayList<>();
    private static List<Company> companies = new ArrayList<>();


    @Before
    public void initialize() {
        employees = Arrays.asList(new Employee("Jan", "Kowalski"),
                new Employee("Franciszek", "Nowak"),
                new Employee("Józef", "Wrocławski"));

        employeeDao.save(employees);

        companies = Arrays.asList(new Company("Mikrosoft"),
                new Company("Orakl"),
                new Company("Fejsbuk"));

        companyDao.save(companies);

    }

    @After
    public void cleanUp() {
        employeeDao.delete(employees);
        companyDao.delete(companies);
    }

    @Test
    public void testSearchEmployeesByNameFragment() {
        //Given

        // When
        List<Employee> resultEmployees = searchFacade.findEmployeesByNameFragment("owa");
        // Then
        Assert.assertTrue(resultEmployees.contains(employees.get(0)));
        Assert.assertTrue(resultEmployees.contains(employees.get(1)));
        Assert.assertFalse(resultEmployees.contains(employees.get(2)));
        Assert.assertTrue(resultEmployees.stream()
                .allMatch(e -> e.getLastname().contains("owa"))
        );
    }

    @Test
    public void testSearchEmployeesByNameFragmentNoMatch() {
        // Given

        // When
        List<Employee> resultEmployees = searchFacade.findEmployeesByNameFragment("!@#");
        // Then
        Assert.assertTrue(resultEmployees.isEmpty());
    }

    @Test
    public void testSearchEmployeesByNameEmptyFragment() {
        // Given

        // When
        List<Employee> resultEmployees = searchFacade.findEmployeesByNameFragment("");
        // Then
        Assert.assertTrue(resultEmployees.containsAll(employees));
    }

    @Test
    public void testSearchCompaniesByNameFragment() {
        //Given

        // When
        List<Company> resultCompanies = searchFacade.findCompaniesByNameFragment("r");
        // Then
        Assert.assertTrue(resultCompanies.contains(companies.get(0)));
        Assert.assertTrue(resultCompanies.contains(companies.get(1)));
        Assert.assertFalse(resultCompanies.contains(companies.get(2)));
        Assert.assertTrue(resultCompanies.stream()
                .allMatch(e -> e.getName().contains("r"))
        );
    }

    @Test
    public void testSearchCompaniesByNameFragmentNoMatch() {
        // Given

        // When
        List<Company> resultCompanies = searchFacade.findCompaniesByNameFragment("!@#");
        // Then
        Assert.assertTrue(resultCompanies.isEmpty());
    }

    @Test
    public void testSearchCompaniesByNameEmptyFragment() {
        // Given

        // When
        List<Company> resultCompanies = searchFacade.findCompaniesByNameFragment("");
        // Then
        Assert.assertTrue(resultCompanies.containsAll(companies));
    }




}
