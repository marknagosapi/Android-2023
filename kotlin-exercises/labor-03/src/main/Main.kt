package main

fun main(args: Array<String>) {

    // creating a company
    val company = Company();

    // creating employees
    val employee1 = Employee("Peter",  1000.0)
    val employee2 = Employee("Lajos",  2000.0)
    val employee3 = Employee("Andras",  3000.0)

    // creating managers
    val manager1 = Manager("IT", "Mark",  5000.0)
    val manager2 = Manager("HR", "Aurel",  10000.0)


    // adding emps
    company.addEmployees(employee1)
    company.addEmployees(employee2)
    company.addEmployees(employee3)

    // adding managers
    company.addEmployees(manager1)
    company.addEmployees(manager2)

    // display all the employees
    company.displayEmployees()

    // display managers
    company.displayManagers()

    // double the salary
    company.doubleSalaryOfManagers()

    // Ordering And Diplay
    println("-- Ordered By Name --")
    company.orderBy(OrderBy.NAME);
    company.displayEmployees()
    println("-- Ordered By Salary --")
    company.orderBy(OrderBy.SALARY);
    company.displayEmployees()

    // Firing an Employee
    company.fireEmployee(5);
    println("-- After firing Aurel  :( --")
    company.displayEmployees();
}