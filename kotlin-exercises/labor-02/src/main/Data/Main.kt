import main.Data.Date
import main.Data.isValidDate

fun main(args: Array<String>){

    val validDates = mutableListOf<Date>()
    println("--- Invalid Dates ---")
    while(validDates.count() < 10) {

        val year = (-10..2030).random()
        val month = (1..15).random()
        val day = (0..40).random()

        val date = Date(year,month,day)

        if(date.isValidDate()) {
            validDates.add(date)
        } else {
            println("The $date is invalid!")
        }

    }

    println(" --- Valid Dates --- ")
    validDates.forEach {
        println(it)
    }

    // sorting the dates
    validDates.sort()

    println(" --- Sorted Valid Dates ---")
    validDates.forEach {
        println(it)
    }

    validDates.reverse()
    println(" --- Reversed Valid Dates ---")
    validDates.forEach {
        println(it)
    }

    validDates.sortWith { o1, o2 -> o1.month.compareTo(o2.month) }
    println(" --- Sorted Valid Dates By Month ---")
    validDates.forEach {
        println(it)
    }
}