package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDaoTestSuite {
    @Autowired
    CompanyDao companyDao;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testSaveManyToMany(){
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        dataMaesters.getEmployees().add(stephanieClarckson);
        dataMaesters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(greyMatter);

        //When
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();

        //Then
        Assert.assertNotEquals(0, softwareMachineId);
        Assert.assertNotEquals(0, dataMaestersId);
        Assert.assertNotEquals(0, greyMatterId);

        //CleanUp
        try {
            companyDao.delete(softwareMachineId);
            companyDao.delete(dataMaestersId);
            companyDao.delete(greyMatterId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testRetrieveEmployeesBySurname() {
        //Given
        Employee employee1 = new Employee("Name1", "Surname1");
        Employee employee2 = new Employee("Name2", "Surname2");
        Employee employee3 = new Employee("Name3", "Surname1");
        employeeDao.save(Arrays.asList(employee1, employee2, employee3));
        //When
        List<Employee> employeesSurname1 = employeeDao.retrieveEmployeesBySurname("Surname1");
        List<Employee> employeesSurname2 = employeeDao.retrieveEmployeesBySurname("Surname2");
        //Then

        try {
            Assert.assertEquals(2, employeesSurname1.size());
            Assert.assertEquals("Surname1", employeesSurname1.get(0).getLastname());
            Assert.assertEquals(1, employeesSurname2.size());
            Assert.assertEquals("Surname2", employeesSurname2.get(0).getLastname());
        } finally {
        //Clean up
            try {
                employeeDao.delete(employee1.getId());
                employeeDao.delete(employee2.getId());
                employeeDao.delete(employee3.getId());
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void retrieveCompaniesByFirst3Chars() {
        //Given
        List<Company> companies = Arrays.asList(
                new Company("ABC company"),
                new Company("ABCD company"),
                new Company("XYZ company"),
                new Company("ABX company")
        );
        companyDao.save(companies);

        //When
        List<Company> companiesABC = companyDao.retrieveCompaniesByFirst3Chars("ABC");
        List<Company> companiesXYZ = companyDao.retrieveCompaniesByFirst3Chars("XYZ");

        //Then
        try {
            Assert.assertEquals(2, companiesABC.size());
            Assert.assertEquals(1, companiesXYZ.size());
            Assert.assertEquals("ABC", companiesABC.get(1).getName().substring(0, 3));
            Assert.assertEquals("XYZ", companiesXYZ.get(0).getName().substring(0, 3));
        } finally {
        //Clean up
            try {
                for (Company c : companies) {
                    companyDao.delete(c.getId());
                }
            } catch (Exception e) {
            }
        }
    }
}