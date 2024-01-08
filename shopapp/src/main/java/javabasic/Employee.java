package javabasic;

public class Employee {
    public String name;
    // salary variable is visible in Employee class only
    private double salary;

    // contructor
    public Employee(String empName) {
        name = empName;
    }

    // Method - The salary variable is assigned a value
    public void setSalary(double empSal) {
        salary = empSal;
    }

    // Method - print the employee details
    public void printEmp() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}
