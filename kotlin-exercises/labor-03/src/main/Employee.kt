package main

open class Employee(val name: String, val employeeID: Int, private var salary: Double) {

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