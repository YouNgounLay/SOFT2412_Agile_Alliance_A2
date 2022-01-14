package fancycinema.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest{
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
}