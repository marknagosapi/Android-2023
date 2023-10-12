package main

open class Employee(val name: String,  private var salary: Double) {

    var employeeID : Int = 0

    companion object{
        var idCounter = 1;
    }

    init{
        employeeID = idCounter;
        idCounter++;
    }

    fun getSalary(): Double {
        return salary
    }

    fun set(value: Double) {
        salary = value
    }

    open fun displayInfo() : Unit{
        println("---- Employee Info  ----")
        println("Name: $name")
        println("ID: $employeeID")
        println("Salary: $salary")
        println("----------------------")
    }
}