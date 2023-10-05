import main.Data.Date
import main.Data.isLeapYear

fun main(args: Array<String>){

    val today = Date()
    println(today)
    if(today.isLeapYear()){
        println("$today is leap year!");
    } else {
        println("$today is not a leap year!");
    }
}