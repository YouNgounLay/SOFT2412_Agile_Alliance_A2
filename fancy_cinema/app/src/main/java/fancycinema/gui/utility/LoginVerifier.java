package fancycinema.gui.utility;

import java.util.List;

import fancycinema.user.Customer;
import fancycinema.user.Employee;
import fancycinema.user.Manager;

public class LoginVerifier {
    private List<Customer> customers; 
    private List<Employee> staffs; 
    private Manager manager; 

    public LoginVerifier(List<Customer> customers, List<Employee> staffs, Manager manager) { 
        this.customers = customers; 
        this.staffs = staffs;
        this.manager = manager;
    }
    
    public boolean hasCustomer(String name, String password) { 
        for (Customer customer : customers) { 
            if (customer.getName().equals(name) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    

    public boolean hasStaff(String name, String password) { 
        for (Employee staff : staffs) { 
            if (staff.getName().equals(name) && staff.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasManager(String name, String password) { 
        return manager.getName().equals(name) && manager.getPassword().equals(password);
    }
}
