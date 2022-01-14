package fancycinema.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private String id; 
    private String password;
    private String name;
    private Employee employeeOne;
    private Employee employeeTwo;

    @BeforeEach
    void init() {
        id = "123456789";
        password = "admin123";
        name = "Johno";

        employeeOne = new Staff(id, name, password);
        employeeTwo = new Staff(name);
    }

    @Test
    void testGetAndSetId(){
        assertEquals(employeeOne.getId(), "123456789");
        employeeOne.setId("420");
        assertEquals(employeeOne.getId(), "420");
        assertEquals(employeeTwo.getId(), null);
    }

    @Test
    void testGetPassword(){
        assertEquals(employeeOne.getPassword(), "admin123");
        assertEquals(employeeTwo.getPassword(), null);
    }
}