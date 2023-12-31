package main

class Manager(val department: String, name: String, salary: Double) : Employee(name,  salary) {

    // did not call the super.displayInfo() cause wanted to the display the manager else.
    override fun displayInfo() {
        println("---- Manager Info  ----")
        println("Name: $name")
        println("ID: $employeeID")
        println("Salary: " + super.getSalary())
        println("Department: $department")
        println("-----------------------")
    }

}
