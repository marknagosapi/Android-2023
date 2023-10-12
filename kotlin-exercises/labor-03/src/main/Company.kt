package main

class Company {
    private val employees: MutableList<Employee> = mutableListOf()

    fun addEmployees(employee: Employee): Boolean {
        return employees.add(employee)
    }

    fun displayEmployees(){
        println("")
        println("--- Employees ---")
        employees.forEach{
            it.displayInfo();
        }
    }

    fun displayManagers(){
        println("")
        println("--- Managers ---")
         employees.forEach{
             if(it is Manager){
                it.displayInfo()
             }
         }
    }

    fun doubleSalaryOfManagers() {
        employees.forEach{
            if(it is Manager){
                it.set(it.getSalary() * 2)
            }
        }
    }

    fun fireEmployee(id: Int): Boolean {
        val employeeToFire = employees.find { it.employeeID == id } ?: return false
        return employees.remove(employeeToFire)
    }

    fun orderBy(criteria: OrderBy) {
        when(criteria){
            OrderBy.NAME -> employees.sortBy { it.name }
            OrderBy.SALARY -> employees.sortBy { it.getSalary() }
        }
    }

}