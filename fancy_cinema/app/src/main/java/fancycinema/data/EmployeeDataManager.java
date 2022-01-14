package fancycinema.data;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

import fancycinema.user.Employee;
import fancycinema.user.Staff;


//ID,username,password
public class EmployeeDataManager {
    private List<Employee> employees;
    private int employeeId; 
    private final String path = "src/main/java/fancycinema/data/";

    public EmployeeDataManager(){
        this.employees = readEmployees();
    }

    public List<Employee> readEmployees() {

        this.employees = new ArrayList<>();

        try {
            File f = new File(path + "Employees.txt");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] user = line.split(",");

                String ID = user[0];
                this.employeeId = Integer.valueOf(ID);
                String username = user[1];
                String password = user[2];
                // String cardName = user[3];
                // String cardNumber = user[4];


                Employee employee = new Staff(ID, username, password);
                this.employees.add(employee);

            }

            sc.close();

            return this.employees;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> writeEmployees() {
        try {
            FileWriter writer = new FileWriter(path + "Employees.txt", false);

            for(Employee user : this.employees){
                String ID = user.getId();
                String username = user.getName();
                String password = user.getPassword();
                writer.write(ID + "," + username + "," + password + "\n");

            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void update() { 
        this.writeEmployees();
    }
    
    public Employee getEmployee(String name, String password) {
        for (Employee staff : this.employees) {
            if (staff.getName().equals(name) && staff.getPassword().equals(password)) {
                return staff;
            }
        }
        return null;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public Employee addEmployee(Employee employee){
        if (employee == null) {
            return null;
        } else if (employees.contains(employee)) {
            return null; 
        }
        this.incrementID();
        employee.setId(Integer.toString(this.employeeId));
        this.employees.add(employee);
        this.writeEmployees();
        return employee;
    }

    public Employee removeEmployee(Employee employee){
        if (employee == null) {
            return null; 
        } else if (!this.employeeExists(employee)) {
            return null;
        }
        int index = employees.indexOf(employee);
        Employee removedStaff = employees.get(index);
        this.employees.remove(index);
        this.writeEmployees();
        return removedStaff;
    }
    public int getID() { 
        return this.employeeId; 
    }
    
    private void incrementID() {
        this.employeeId++;
    }
    
    private boolean employeeExists(Employee employee) { 
        for (Employee staff : employees) {
            if (employee.getName().equals(staff.getName())) {
                return true;
            }
        }
        return false;
    }
    
}